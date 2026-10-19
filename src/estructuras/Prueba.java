package estructuras;

public class Prueba {
    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();
        lista.insertarAlFinal("A");
        lista.insertarAlFinal("B");
        lista.insertarAlFinal("C");

        System.out.println("--- Hacia adelante ---");
        lista.recorrer();

        System.out.println("--- Hacia atrás ---");
        lista.recorrerAlReves();

        System.out.println("Tamaño: " + lista.getTamano());
    }
}