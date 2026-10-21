package estructuras;

public class Pila<T> {
    private NodoSimple<T> tope;
    private int tamano;

    public Pila() {
        tope = null;
        tamano = 0;
    }

    public void apilar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamano++;
    }

    public T desapilar() {
        if (tope == null) {
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    public T verTope() {
        if (tope == null) {
            return null;
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }

    // Devuelve el dato en la posición dada, contando desde el tope (0 = el más reciente)
public T obtenerDesdeTope(int posicion) {
    NodoSimple<T> actual = tope;
    int i = 0;
    while (actual != null) {
        if (i == posicion) {
            return actual.dato;
        }
        actual = actual.siguiente;
        i++;
    }
    return null;
}
}