package modelo;

public class Espacio {
    private String id;
    private TipoEspacio tipo;
    private boolean ocupado;
    private Vehiculo vehiculo;

    public Espacio izquierda;
    public Espacio derecha;
    public Espacio arriba;
    public Espacio abajo;

    public Espacio(String id, TipoEspacio tipo) {
        this.id = id;
        this.tipo = tipo;
        this.ocupado = false;
        this.vehiculo = null;
    }

    public void ocupar(Vehiculo v) {
        this.ocupado = true;
        this.vehiculo = v;
    }

    public void liberar() {
        this.ocupado = false;
        this.vehiculo = null;
    }

    public String getId() { return id; }
    public TipoEspacio getTipo() { return tipo; }
    public boolean isOcupado() { return ocupado; }
    public Vehiculo getVehiculo() { return vehiculo; }
}