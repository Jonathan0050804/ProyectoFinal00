package modelo;

public class Evento {
    private final String descripcion;
    private final long hora = System.currentTimeMillis();

    public Evento(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(hora))
               + "  " + descripcion;
    }
}