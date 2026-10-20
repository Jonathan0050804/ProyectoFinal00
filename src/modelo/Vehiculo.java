package modelo;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;      // "carro", "moto", etc.
    private String estado;    // "FUERA", "EN_COLA", "ESTACIONADO"

    public Vehiculo(String placa, String marca, String modelo,
                    String color, String tipo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.estado = "FUERA";
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Dos vehículos son "el mismo" si tienen la misma placa
    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Vehiculo)) return false;
        Vehiculo v = (Vehiculo) otro;
        return placa.equals(v.placa);
    }

    @Override
    public String toString() {
        return placa + " (" + marca + " " + modelo + ")";
    }
}