package estructuras;

public class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamano;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    public void insertarAlFinal(Object dato) {
        NodoDoble nuevo = new NodoDoble(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;   // el último actual apunta al nuevo
            nuevo.anterior = cola;    // el nuevo apunta de vuelta
            cola = nuevo;             // el nuevo es ahora el último
        }
        tamano++;
    }

    public void recorrer() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public void recorrerAlReves() {
        NodoDoble actual = cola;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.anterior;
        }
    }

    public int getTamano() {
        return tamano;
    }
}