package estructuras;

public class NodoDoble {
    Object dato;
    NodoDoble siguiente;
    NodoDoble anterior;

    public NodoDoble(Object dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}