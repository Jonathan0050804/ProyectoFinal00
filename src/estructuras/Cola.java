package estructuras;

public class Cola<T> {
    private NodoSimple<T> frente;
    private NodoSimple<T> fin;
    private int tamano;

    public Cola() {
        frente = null;
        fin = null;
        tamano = 0;
    }

    public void encolar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    public T desencolar() {
        if (frente == null) {
            return null;
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamano--;
        return dato;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }
}