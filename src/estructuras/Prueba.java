package estructuras;

public class Prueba {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();
        lista.insertarAlFinal("Hola");
        lista.insertarAlFinal("Mundo");
        lista.insertarAlFinal("Lista");
        lista.recorrer();
        System.out.println("Tamaño: " + lista.getTamano());
    }
}