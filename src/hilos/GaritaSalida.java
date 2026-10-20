package hilos;

import modelo.*;

public class GaritaSalida implements Runnable {
    private final String nombre;
    private final ColaSegura<Vehiculo> colaSalida;
    private final Parqueo parqueo;
    private final PilaSegura<Evento> eventos;

    public GaritaSalida(String nombre, ColaSegura<Vehiculo> colaSalida,
                        Parqueo parqueo, PilaSegura<Evento> eventos) {
        this.nombre = nombre;
        this.colaSalida = colaSalida;
        this.parqueo = parqueo;
        this.eventos = eventos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Vehiculo v = colaSalida.desencolar();        // 1. Se duerme si la cola está vacía
                Thread.sleep(800);
                Espacio e = parqueo.liberar(v.getPlaca());  // 2. Libera el espacio buscando por placa
                
                if (e == null) {
                    eventos.apilar(new Evento(nombre + ": " + v.getPlaca() + " no estaba estacionado"));
                } else {
                    v.setEstado("FUERA");
                    eventos.apilar(new Evento(nombre + ": " + v.getPlaca() + " salió, espacio " + e.getId() + " liberado"));
                }
            }
        } catch (InterruptedException ex) {
            // Hilo detenido limpiamente
        }
    }
}