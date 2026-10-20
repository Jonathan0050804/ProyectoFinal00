package modelo;

public class PruebaParqueo {
    public static void main(String[] args) {
        Parqueo p = new Parqueo();
        System.out.println("Espacios: " + p.contarEspacios());   // 150

        Espacio a1 = p.getInicioSocios();
        System.out.println(a1.getId());                      // A1
        System.out.println(a1.abajo.getId());                // B1
        System.out.println(a1.abajo.abajo.arriba.getId());   // B1
        System.out.println(a1.derecha.abajo.getId());        // B2
        System.out.println(a1.arriba);                       // null

Espacio e1 = p.asignar(new Vehiculo("P001", "Toyota", "Yaris", "Rojo", "carro"), TipoEspacio.SOCIO);
Espacio e2 = p.asignar(new Vehiculo("P002", "Honda", "Civic", "Azul", "carro"), TipoEspacio.SOCIO);
System.out.println(e1.getId());                      // A1
System.out.println(e2.getId());                      // A2
p.liberar("P001");
Espacio e3 = p.asignar(new Vehiculo("P003", "Kia", "Rio", "Negro", "carro"), TipoEspacio.SOCIO);
        System.out.println(e3.getId());                      // A1
        System.out.println(p.liberar("NOEXISTE"));           // null
    }
}