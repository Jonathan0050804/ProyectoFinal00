package modelo;

import estructuras.ListaDoble;

public class Parqueo {
    private ListaDoble<Espacio> filasSocios = new ListaDoble<>();
    private ListaDoble<Espacio> filasGeneral = new ListaDoble<>();

    public Parqueo() {
        construirArea("ABC", 25, TipoEspacio.SOCIO, filasSocios);
        construirArea("EFGHI", 15, TipoEspacio.GENERAL, filasGeneral);
    }

    // ---------- Construcción ----------

    private void construirArea(String letras, int cantidad,
                               TipoEspacio tipo, ListaDoble<Espacio> filas) {
        Espacio inicioFilaAnterior = null;

        for (int f = 0; f < letras.length(); f++) {
            Espacio inicioFila = construirFila(letras.charAt(f), cantidad, tipo);
            filas.insertarAlFinal(inicioFila);

            if (inicioFilaAnterior != null) {
                enlazarFilas(inicioFilaAnterior, inicioFila);
            }
            inicioFilaAnterior = inicioFila;
        }
    }

    private Espacio construirFila(char letra, int cantidad, TipoEspacio tipo) {
        Espacio primero = null;
        Espacio anterior = null;

        for (int i = 1; i <= cantidad; i++) {
            Espacio nuevo = new Espacio("" + letra + i, tipo);
            if (anterior == null) {
                primero = nuevo;
            } else {
                anterior.derecha = nuevo;
                nuevo.izquierda = anterior;
            }
            anterior = nuevo;
        }
        return primero;
    }

    private void enlazarFilas(Espacio filaArriba, Espacio filaAbajo) {
        Espacio a = filaArriba;
        Espacio b = filaAbajo;
        while (a != null && b != null) {
            a.abajo = b;
            b.arriba = a;
            a = a.derecha;
            b = b.derecha;
        }
    }

    // ---------- Conteo ----------

    private int contarArea(ListaDoble<Espacio> filas) {
        int total = 0;
        Espacio inicioFila = filas.obtenerPrimero();
        while (inicioFila != null) {
            Espacio actual = inicioFila;
            while (actual != null) {
                total++;
                actual = actual.derecha;
            }
            inicioFila = inicioFila.abajo;
        }
        return total;
    }

    public int contarEspacios() {
        return contarArea(filasSocios) + contarArea(filasGeneral);
    }

    public Espacio getInicioSocios() {
        return filasSocios.obtenerPrimero();
    }

    // ---------- Buscar, asignar, liberar ----------

    public Espacio buscarLibre(TipoEspacio tipo) {
        ListaDoble<Espacio> filas = (tipo == TipoEspacio.SOCIO) ? filasSocios : filasGeneral;

        Espacio inicioFila = filas.obtenerPrimero();
        while (inicioFila != null) {
            Espacio actual = inicioFila;
            while (actual != null) {
                if (!actual.isOcupado()) {
                    return actual;
                }
                actual = actual.derecha;
            }
            inicioFila = inicioFila.abajo;
        }
        return null;
    }

public Espacio asignar(Vehiculo v, TipoEspacio tipo) {
    Espacio libre = buscarLibre(tipo);
    if (libre == null) {
        return null;
    }
    libre.ocupar(v);
    return libre;
    } 

    private Espacio buscarPorPlaca(String placa, ListaDoble<Espacio> filas) {
        Espacio inicioFila = filas.obtenerPrimero();
        while (inicioFila != null) {
            Espacio actual = inicioFila;
            while (actual != null) {
if (actual.isOcupado() && placa.equals(actual.getVehiculo().getPlaca())) {
    return actual;

                }
                actual = actual.derecha;
            }
            inicioFila = inicioFila.abajo;
        }
        return null;
    }

    public Espacio liberar(String placa) {
        Espacio e = buscarPorPlaca(placa, filasSocios);
        if (e == null) {
            e = buscarPorPlaca(placa, filasGeneral);
        }
        if (e != null) {
            e.liberar();
        }
        return e;
    }
}