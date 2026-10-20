package persistencia;

import modelo.*;

public class PruebaPersistencia {
    public static void main(String[] args) throws Exception {
        // 1. Crear datos y guardar
        GestorResidentes g = new GestorResidentes();
        Residente ana = new Residente("R1", "Ana", "Casa 5", true);
        ana.agregarVehiculo(new Vehiculo("P1", "Toyota", "Yaris", "Rojo", "carro"));
        ana.agregarVehiculo(new Vehiculo("P2", "Honda", "Civic", "Azul", "carro"));
        g.registrar(ana);
        g.registrar(new Residente("R2", "Luis", "Casa 8", false));
        Persistencia.guardar(g, "residentes.txt", "vehiculos.txt");

        // 2. Cargar en un gestor NUEVO
        GestorResidentes g2 = Persistencia.cargar("residentes.txt", "vehiculos.txt");
        System.out.println(g2.getTotal());                                  // 2
        System.out.println(g2.buscarPorId("R1"));                           // R1 - Ana (socio)
        System.out.println(g2.buscarPorId("R1").getVehiculos().getTamano()); // 2
        System.out.println(g2.buscarPorId("R2").getVehiculos().getTamano()); // 0
    }
}