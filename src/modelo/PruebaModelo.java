package modelo;

public class PruebaModelo {
    public static void main(String[] args) {
        Residente r = new Residente("R1", "Ana", "Casa 5", true);
        System.out.println(r.agregarVehiculo(new Vehiculo("P1", "Toyota", "Yaris", "Rojo", "carro"))); // true
        System.out.println(r.agregarVehiculo(new Vehiculo("P2", "Honda", "Civic", "Azul", "carro")));  // true
        System.out.println(r.agregarVehiculo(new Vehiculo("P3", "Kia", "Rio", "Negro", "carro")));     // true
        System.out.println(r.agregarVehiculo(new Vehiculo("P4", "Mazda", "3", "Gris", "carro")));      // false (cuarto)
        System.out.println(r.agregarVehiculo(new Vehiculo("P1", "Otro", "X", "Rojo", "moto")));        // false (placa repetida)
        System.out.println(r.getVehiculos().getTamano());                                             // 3

        Parqueo p = new Parqueo();
        Vehiculo v = new Vehiculo("P9", "Ford", "Fiesta", "Verde", "carro");
        Espacio e = p.asignar(v, TipoEspacio.SOCIO);
        System.out.println(e.getId() + " " + e.getVehiculo());   // A1 P9 (Ford Fiesta)
        p.liberar("P9");
        System.out.println(e.isOcupado());                       // false
    }
}