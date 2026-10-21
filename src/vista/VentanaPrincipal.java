package vista;

import modelo.*;
import persistencia.Persistencia;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(Simulacion sim) {
        setTitle("Sistema de Control de Parqueo - Condominio");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        PanelParqueo panelParqueo = new PanelParqueo(sim.getParqueo());
        PanelEntrada panelEntrada = new PanelEntrada(sim);
        PanelSalida panelSalida = new PanelSalida(sim);
        PanelEventos panelEventos = new PanelEventos(sim.getEventos());

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Parqueo", panelParqueo);
        pestanas.addTab("Residentes", new PanelResidentes(sim.getGestor()));
        pestanas.addTab("Vehículos", new PanelVehiculos(sim));
        pestanas.addTab("Entrada", panelEntrada);
        pestanas.addTab("Salida", panelSalida);
        pestanas.addTab("Eventos", panelEventos);
        add(pestanas, BorderLayout.CENTER);

        javax.swing.Timer timer = new javax.swing.Timer(500, e -> {
            panelParqueo.actualizar();
            panelEntrada.actualizar();
            panelSalida.actualizar();
            panelEventos.actualizar();
        });
        timer.start();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    Persistencia.guardar(sim.getGestor(), "residentes.txt", "vehiculos.txt");
                } catch (java.io.IOException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this,
                            "No se pudo guardar: " + ex.getMessage());
                }
                sim.detener();
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorResidentes gestor;
            try {
                gestor = Persistencia.cargar("residentes.txt", "vehiculos.txt");
            } catch (java.io.IOException ex) {
                gestor = new GestorResidentes();
            }
            Simulacion sim = new Simulacion(gestor);
            sim.iniciar();
            new VentanaPrincipal(sim).setVisible(true);
        });
    }
}