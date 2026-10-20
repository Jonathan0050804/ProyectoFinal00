package hilos;

public class PruebaCola {
    public static void main(String[] args) throws InterruptedException {
        ColaSegura<String> cola = new ColaSegura<>();

        for (int g = 1; g <= 3; g++) {
            final String nombre = "Garita-" + g;
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String auto = cola.desencolar();
                        System.out.println(nombre + " atiende " + auto);
                        Thread.sleep(800);
                    }
                } catch (InterruptedException e) {
                    // terminar
                }
            }, nombre);
            t.start();
        }

        for (int i = 1; i <= 8; i++) {
            Thread.sleep(300);
            cola.encolar("Auto " + i);
        }
    }
}