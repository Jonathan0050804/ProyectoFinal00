package hilos;

import estructuras.Cola;

public class ColaSegura<T> {
    private final Cola<T> cola = new Cola<>();

    // Lo llama quien produce (llegan vehículos)
    public synchronized void encolar(T dato) {
        cola.encolar(dato);
        notifyAll();            // despierta a las garitas dormidas
    }

    public synchronized T obtenerEn(int posicion) {
    return cola.obtenerEn(posicion);
}

    // Lo llaman las garitas (consumen)
    public synchronized T desencolar() throws InterruptedException {
        while (cola.estaVacia()) {     // while, NO if
            wait();                    // duerme y suelta la "llave"
        }
        return cola.desencolar();
    }

    public synchronized int getTamano() {
        return cola.getTamano();
    }
}
