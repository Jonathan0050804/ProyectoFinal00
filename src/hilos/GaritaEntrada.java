package hilos;

import modelo.*;

public class GaritaEntrada implements Runnable {
    private final String nombre;
    private final ColaSegura<Vehiculo> colaEntrada;
    private final Parqueo parqueo;
    private final PilaSegura<Evento> eventos;
    private final GestorResidentes gestor;
    private volatile Vehiculo enAtencion;

    public GaritaEntrada(String nombre, ColaSegura<Vehiculo> colaEntrada,
                         Parqueo parqueo, PilaSegura<Evento> eventos,
                         GestorResidentes gestor) {
        this.nombre = nombre;
        this.colaEntrada = colaEntrada;
        this.parqueo = parqueo;
        this.eventos = eventos;
        this.gestor = gestor;
    }

    public Vehiculo getEnAtencion() { return enAtencion; }

    @Override
    public void run() {
        try {
            while (true) {
                Vehiculo v = colaEntrada.desencolar();
                enAtencion = v;
                Thread.sleep(800);

                Residente dueno = gestor.buscarDuenoPorPlaca(v.getPlaca());
                boolean esSocio = (dueno != null && dueno.isSocio());

                Espacio e = parqueo.asignarSegunTipo(v, esSocio);
                if (e == null) {
                    eventos.apilar(new Evento(nombre + ": vehículo " + v.getPlaca()
                            + " rechazado (parqueo lleno)"));
                } else {
                    v.setEstado("ESTACIONADO");
                    eventos.apilar(new Evento(nombre + ": " + v.getPlaca()
                            + " asignado al espacio " + e.getId()));
                }
                enAtencion = null;
            }
        } catch (InterruptedException ex) {
            // hilo detenido
        }
    }
}