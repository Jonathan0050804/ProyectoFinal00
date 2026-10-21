package vista;

import modelo.*;
import hilos.*;
import javax.swing.*;
import java.awt.*;

public class PanelEntrada extends JPanel {
    private final Simulacion sim;
    private final JTextArea areaCola = new JTextArea();
    private final JLabel[] garitas = new JLabel[3];

    public PanelEntrada(Simulacion sim) {
        this.sim = sim;
        setLayout(new BorderLayout(10, 10));

        areaCola.setEditable(false);
        JPanel izq = new JPanel(new BorderLayout());
        izq.add(new JLabel("  Cola de entrada (frente arriba)"), BorderLayout.NORTH);
        izq.add(new JScrollPane(areaCola), BorderLayout.CENTER);

        JPanel der = new JPanel(new GridLayout(3, 1, 5, 5));
        for (int i = 0; i < 3; i++) {
            garitas[i] = new JLabel("Entrada-" + (i + 1) + ": libre", SwingConstants.CENTER);
            garitas[i].setOpaque(true);
            garitas[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            der.add(garitas[i]);
        }

        add(izq, BorderLayout.CENTER);
        add(der, BorderLayout.EAST);
        der.setPreferredSize(new Dimension(350, 0));
    }

    public void actualizar() {
        StringBuilder sb = new StringBuilder();
        ColaSegura<Vehiculo> cola = sim.getColaEntrada();
        int total = cola.getTamano();
        for (int i = 0; i < total; i++) {
            Vehiculo v = cola.obtenerEn(i);
            if (v != null) sb.append(v).append("\n");
        }
        areaCola.setText(sb.toString());

        GaritaEntrada[] entradas = sim.getEntradas();
        for (int i = 0; i < 3; i++) {
            Vehiculo v = entradas[i].getEnAtencion();
            if (v == null) {
                garitas[i].setText("Entrada-" + (i + 1) + ": libre");
                garitas[i].setBackground(new Color(144, 238, 144));
            } else {
                garitas[i].setText("Entrada-" + (i + 1) + ": atendiendo " + v.getPlaca());
                garitas[i].setBackground(new Color(255, 215, 100));
            }
        }
    }
}