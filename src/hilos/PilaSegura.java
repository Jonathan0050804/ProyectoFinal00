package hilos;

import estructuras.Pila;

public class PilaSegura<T> {
    private final Pila<T> pila = new Pila<>();

    public synchronized void apilar(T dato) {
        pila.apilar(dato);
    }

    public synchronized T verTope() {
        return pila.verTope();
    }

    public synchronized int getTamano() {
        return pila.getTamano();
    }
}