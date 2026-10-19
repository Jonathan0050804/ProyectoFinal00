package modelo;

public class Parqueo {
    private Espacio inicioFilaA;   // primer espacio de la fila A

    public Parqueo() {
        construirFila('A', 5, TipoEspacio.SOCIO);   // 5 para probar
    }

    private void construirFila(char letra, int cantidad, TipoEspacio tipo) {
        Espacio anterior = null;

        for (int i = 1; i <= cantidad; i++) {
            Espacio nuevo = new Espacio("" + letra + i, tipo);

            if (anterior == null) {
                inicioFilaA = nuevo;          // el primero de la fila
            } else {
                anterior.derecha = nuevo;     // enlazar ida
                nuevo.izquierda = anterior;   // enlazar vuelta
            }
            anterior = nuevo;                 // avanzar
        }
    }

    public void imprimirFila() {
        Espacio actual = inicioFilaA;
        while (actual != null) {
            System.out.print(actual.getId() + " ");
            actual = actual.derecha;
        }
        System.out.println();
    }
}