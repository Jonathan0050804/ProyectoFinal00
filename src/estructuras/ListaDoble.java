package estructuras;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamano;

public T obtenerPrimero() {
    if (cabeza == null) {
        return null;
    }
    return cabeza.dato;
}    

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    public void insertarAlInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamano++;
    }

    public void insertarAlFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamano++;
    }

    public boolean buscar(T dato) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void eliminar(T dato) {
        NodoDoble<T> actual = cabeza;

        while (actual != null && !actual.dato.equals(dato)) {
            actual = actual.siguiente;
        }

        if (actual == null) return;

        if (actual.anterior != null) {
            actual.anterior.siguiente = actual.siguiente;
        } else {
            cabeza = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente.anterior = actual.anterior;
        } else {
            cola = actual.anterior;
        }

        tamano--;
    }

    public void recorrer() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + ", ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public void recorrerAlReves() {
        NodoDoble<T> actual = cola;
        while (actual != null) {
            System.out.print(actual.dato + ", ");
            actual = actual.anterior;
        }
        System.out.println();
    }

    public int getTamano() {
        return tamano;
    }
}