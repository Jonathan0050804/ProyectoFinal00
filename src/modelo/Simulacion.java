package modelo;

import hilos.*;

public class Simulacion {
    private final ColaSegura<Vehiculo> colaEntrada = new ColaSegura<>();
    private final ColaSegura<Vehiculo> colaSalida = new ColaSegura<>();
    private final Parqueo parqueo = new Parqueo();
    private final PilaSegura<Evento> eventos = new PilaSegura<>();
    private final GestorResidentes gestor;
    private final Thread[] hilos = new Thread[5];
    private final GaritaEntrada[] entradas = new GaritaEntrada[3];
    private final GaritaSalida[] salidas = new GaritaSalida[2];

    public Simulacion(GestorResidentes gestor) {
        this.gestor = gestor;
    }

    public void iniciar() {
        for (int g = 0; g < 3; g++) {
            entradas[g] = new GaritaEntrada("Entrada-" + (g + 1),
                    colaEntrada, parqueo, eventos, gestor);
            hilos[g] = new Thread(entradas[g]);
            hilos[g].start();
        }
        for (int g = 0; g < 2; g++) {
            salidas[g] = new GaritaSalida("Salida-" + (g + 1),
                    colaSalida, parqueo, eventos);
            hilos[g + 3] = new Thread(salidas[g]);
            hilos[g + 3].start();
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
    public ColaSegura<Vehiculo> getColaEntrada() { return colaEntrada; }
    public ColaSegura<Vehiculo> getColaSalida() { return colaSalida; }
    public GaritaEntrada[] getEntradas() { return entradas; }
    public GaritaSalida[] getSalidas() { return salidas; }
}