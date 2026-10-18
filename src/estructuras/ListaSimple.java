package estructuras;

public class ListaSimple {
        private NodoSimple cabeza;
    private int tamano;

    public ListaSimple() {
        cabeza = null;
        tamano = 0;
    }

    // Agrega un dato al final de la lista
    public void insertarAlFinal(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        if (cabeza == null) {
            cabeza = nuevo;           // lista vacía: el nuevo es el primero
        } else {
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;   // avanzar hasta el último
            }
            actual.siguiente = nuevo;        // enganchar al final
        }
        tamano++;
    }

    // Imprime todos los datos
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
