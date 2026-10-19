package estructuras;

public class ListaSimple {
    private NodoSimple cabeza;
    private int tamano;

    public ListaSimple() {
        cabeza = null;
        tamano = 0;
    }

    public void insertarAlFinal(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public void insertarAlInicio(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamano++;
    }

    public boolean buscar(Object dato) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void eliminar(Object dato) {
        if (cabeza == null) {
            return;
        }

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamano--;
            return;
        }

        NodoSimple anterior = cabeza;
        NodoSimple actual = cabeza.siguiente;

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
        NodoSimple actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public int getTamano() {
        return tamano;
    }
}