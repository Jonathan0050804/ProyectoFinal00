package estructuras;

import modelo.Parqueo;

public class Prueba {
    public static void main(String[] args) {
        // ListaSimple
        ListaSimple<Integer> ls = new ListaSimple<>();
        ls.insertarAlFinal(10);
        ls.insertarAlFinal(20);
        ls.insertarAlFinal(30);
        ls.eliminar(20);
        ls.insertarAlInicio(5);
        System.out.print("ListaSimple (5, 10, 30): ");
        ls.recorrer();

        // ListaDoble
        ListaDoble<String> ld = new ListaDoble<>();
        ld.insertarAlFinal("A");
        ld.insertarAlFinal("B");
        ld.insertarAlFinal("C");
        ld.eliminar("B");
        System.out.print("ListaDoble adelante (A, C): ");
        ld.recorrer();
        System.out.print("ListaDoble atrás (C, A): ");
        ld.recorrerAlReves();
        System.out.println("Tamaño: " + ld.getTamano());

        // Cola
        Cola<String> cola = new Cola<>();
        cola.encolar("Auto 1");
        cola.encolar("Auto 2");
        System.out.println("Cola: " + cola.desencolar());   // Auto 1
        System.out.println("Cola: " + cola.desencolar());   // Auto 2
        System.out.println("Cola vacía: " + cola.desencolar());   // null

        // Pila
        Pila<String> pila = new Pila<>();
        pila.apilar("Evento 1");
        pila.apilar("Evento 2");
        System.out.println("Pila tope: " + pila.verTope());       // Evento 2
        System.out.println("Pila desapilar: " + pila.desapilar()); // Evento 2
        System.out.println("Pila tamaño: " + pila.getTamano());   // 1

        Parqueo p = new Parqueo();
p.imprimirFila();    // A1 A2 A3 A4 A5
    }
}