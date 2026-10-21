package hilos;

import modelo.*;

public class GaritaSalida implements Runnable {
    private final String nombre;
    private final ColaSegura<Vehiculo> colaSalida;
    private final Parqueo parqueo;
    private final PilaSegura<Evento> eventos;
    private volatile Vehiculo enAtencion;

    public GaritaSalida(String nombre, ColaSegura<Vehiculo> colaSalida,
                        Parqueo parqueo, PilaSegura<Evento> eventos) {
        this.nombre = nombre;
        this.colaSalida = colaSalida;
        this.parqueo = parqueo;
        this.eventos = eventos;
    }

    public Vehiculo getEnAtencion() { return enAtencion; }

    @Override
    public void run() {
        try {
            while (true) {
                Vehiculo v = colaSalida.desencolar();
                enAtencion = v;
                Thread.sleep(800);
                Espacio e = parqueo.liberar(v.getPlaca());
                if (e == null) {
                    eventos.apilar(new Evento(nombre + ": " + v.getPlaca() + " no estaba estacionado"));
                } else {
                    v.setEstado("FUERA");
                    eventos.apilar(new Evento(nombre + ": " + v.getPlaca()
                            + " salió, espacio " + e.getId() + " liberado"));
                }
                enAtencion = null;
            }
        } catch (InterruptedException ex) {
            // hilo detenido
        }
    }
}