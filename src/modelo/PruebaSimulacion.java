package modelo;

public class PruebaSimulacion {
    public static void main(String[] args) throws InterruptedException {
        Simulacion sim = new Simulacion(new GestorResidentes());
        sim.iniciar();

        sim.llegaVehiculo(new Vehiculo("P1", "Toyota", "Yaris", "Rojo", "carro"));
        sim.llegaVehiculo(new Vehiculo("P2", "Honda", "Civic", "Azul", "carro"));
        sim.llegaVehiculo(new Vehiculo("P3", "Kia", "Rio", "Negro", "carro"));

        Thread.sleep(3000);
        System.out.println("Eventos: " + sim.getEventos().getTamano());   // 3
        System.out.println("Ultimo: " + sim.getEventos().verTope());

        sim.detener();
    }
}