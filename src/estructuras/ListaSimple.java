package estructuras;

public class ListaSimple<T> {
    private NodoSimple<T> cabeza;
    private int tamano;

    public ListaSimple() {
        cabeza = null;
        tamano = 0;
    }

    public void insertarAlInicio(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamano++;
    }

    public void insertarAlFinal(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public boolean buscar(T dato) {
        NodoSimple<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void eliminar(T dato) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamano--;
            return;
        }
        NodoSimple<T> anterior = cabeza;
        NodoSimple<T> actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                anterior.siguiente = actual.siguiente;
                tamano--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    public void recorrer() {
        NodoSimple<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + ", ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public int getTamano() {
        return tamano;
    }

    public T obtenerEn(int posicion) {
    NodoSimple<T> actual = cabeza;
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