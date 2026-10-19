package modelo;

public class Espacio {
    private String id;               // "A12"
    private TipoEspacio tipo;
    private boolean ocupado;
    private String placaVehiculo;    // por ahora guardamos solo la placa

    // Los cuatro enlaces (tu dibujo de E-I hecho código)
    public Espacio izquierda;
    public Espacio derecha;
    public Espacio arriba;
    public Espacio abajo;

    public Espacio(String id, TipoEspacio tipo) {
        this.id = id;
        this.tipo = tipo;
        this.ocupado = false;
        this.placaVehiculo = null;
    }

    public void ocupar(String placa) {
        this.ocupado = true;
        this.placaVehiculo = placa;
    }

    public void liberar() {
        this.ocupado = false;
        this.placaVehiculo = null;
    }

    public String getId() { return id; }
    public TipoEspacio getTipo() { return tipo; }
    public boolean isOcupado() { return ocupado; }
    public String getPlacaVehiculo() { return placaVehiculo; }
}