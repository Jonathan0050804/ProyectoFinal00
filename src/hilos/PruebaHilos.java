package hilos;

public class PruebaHilos {
    public static void main(String[] args) {
        Runnable tarea = () -> {
            String nombre = Thread.currentThread().getName();
            for (int i = 1; i <= 3; i++) {
                System.out.println(nombre + " paso " + i);
                try {
                    Thread.sleep(500);   // pausa de medio segundo
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        Thread h1 = new Thread(tarea, "Garita-1");
        Thread h2 = new Thread(tarea, "Garita-2");
        h1.start();
        h2.start();
    }
}