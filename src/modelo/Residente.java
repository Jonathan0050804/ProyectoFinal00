package modelo;

import estructuras.ListaSimple;

public class Residente {
    private String id;
    private String nombre;
    private String casa;
    private boolean socio;
    private ListaSimple<Vehiculo> vehiculos = new ListaSimple<>();

    public static final int MAX_VEHICULOS = 3;

    public Residente(String id, String nombre, String casa, boolean socio) {
        this.id = id;
        this.nombre = nombre;
        this.casa = casa;
        this.socio = socio;
    }

    // Devuelve true si lo agregó, false si ya tiene 3 o la placa se repite
    public boolean agregarVehiculo(Vehiculo v) {
        if (vehiculos.getTamano() >= MAX_VEHICULOS) {  // Uso de la constante
            return false;
        }
        if (vehiculos.buscar(v)) {
            return false;
        }
        vehiculos.insertarAlFinal(v);
        return true;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCasa() { return casa; }
    public boolean isSocio() { return socio; }
    public ListaSimple<Vehiculo> getVehiculos() { return vehiculos; }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Residente)) return false;
        return id.equals(((Residente) otro).id);
    }

    @Override
    public String toString() {
        return id + " - " + nombre + (socio ? " (socio)" : "");
    }
}