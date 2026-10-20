package modelo;

import hilos.*;

public class Simulacion {
    private final ColaSegura<Vehiculo> colaEntrada = new ColaSegura<>();
    private final ColaSegura<Vehiculo> colaSalida = new ColaSegura<>();
    private final Parqueo parqueo = new Parqueo();
    private final PilaSegura<Evento> eventos = new PilaSegura<>();
    private final GestorResidentes gestor;
    private final Thread[] hilos = new Thread[5];

    public Simulacion(GestorResidentes gestor) {
        this.gestor = gestor;
    }

    public void iniciar() {
        for (int g = 1; g <= 3; g++) {
            hilos[g - 1] = new Thread(new GaritaEntrada("Entrada-" + g,
        colaEntrada, parqueo, eventos, gestor));
            hilos[g - 1].start();
        }
        for (int g = 1; g <= 2; g++) {
            hilos[g + 2] = new Thread(new GaritaSalida("Salida-" + g,
                    colaSalida, parqueo, eventos));
            hilos[g + 2].start();
        }
    }

    public void detener() {
        for (Thread t : hilos) {
            if (t != null) t.interrupt();
        }
    }

    public void llegaVehiculo(Vehiculo v) { colaEntrada.encolar(v); }
    public void pideSalida(Vehiculo v)    { colaSalida.encolar(v); }

    public Parqueo getParqueo() { return parqueo; }
    public PilaSegura<Evento> getEventos() { return eventos; }
    public GestorResidentes getGestor() { return gestor; }
}