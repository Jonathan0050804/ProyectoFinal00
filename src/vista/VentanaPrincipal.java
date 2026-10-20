package vista;

import modelo.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(Simulacion sim) {
        setTitle("Parqueo Residencial");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Parqueo", new PanelParqueo(sim.getParqueo()));
        add(pestanas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Simulacion sim = new Simulacion(new GestorResidentes());
            sim.iniciar();
            new VentanaPrincipal(sim).setVisible(true);
        });
    }
}