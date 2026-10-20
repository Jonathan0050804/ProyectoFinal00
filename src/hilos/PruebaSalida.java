package hilos;

import modelo.*;

public class PruebaSalida {
    public static void main(String[] args) throws InterruptedException {
        ColaSegura<Vehiculo> colaEntrada = new ColaSegura<>();
        ColaSegura<Vehiculo> colaSalida = new ColaSegura<>();
        Parqueo parqueo = new Parqueo();
        PilaSegura<Evento> eventos = new PilaSegura<>();
        GestorResidentes gestor = new GestorResidentes();

        for (int g = 1; g <= 3; g++) {
            new Thread(new GaritaEntrada("Entrada-" + g, colaEntrada, parqueo, eventos, gestor)).start();
        }
        for (int g = 1; g <= 2; g++) {
            new Thread(new GaritaSalida("Salida-" + g, colaSalida, parqueo, eventos)).start();
        }

        Vehiculo[] autos = new Vehiculo[6];
        for (int i = 0; i < 6; i++) {
            autos[i] = new Vehiculo("P" + (i + 1), "Marca", "Modelo", "Rojo", "carro");
            colaEntrada.encolar(autos[i]);
        }

        Thread.sleep(4000);
        for (Vehiculo v : autos) {
            colaSalida.encolar(v);
        }
        Thread.sleep(4000);

        System.out.println("Eventos: " + eventos.getTamano());   // 12
        System.out.println("Ultimo: " + eventos.verTope());
        System.exit(0);
    }
}