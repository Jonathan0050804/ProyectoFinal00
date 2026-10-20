package hilos;

public class PruebaCarrera {
    static int contador = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable tarea = () -> {
            for (int i = 0; i < 100000; i++) {
                contador++;
            }
        };

        Thread h1 = new Thread(tarea);
        Thread h2 = new Thread(tarea);
        h1.start();
        h2.start();
        h1.join();   // esperar a que terminen
        h2.join();

        System.out.println("Esperado: 200000, real: " + contador);
    }
}