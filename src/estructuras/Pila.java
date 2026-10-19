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
}