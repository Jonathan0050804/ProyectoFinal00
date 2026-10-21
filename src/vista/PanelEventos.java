package vista;

import modelo.*;
import hilos.PilaSegura;
import javax.swing.*;
import java.awt.*;

public class PanelEventos extends JPanel {
    private final PilaSegura<Evento> eventos;
    private final JTextArea area = new JTextArea();

    public PanelEventos(PilaSegura<Evento> eventos) {
        this.eventos = eventos;
        setLayout(new BorderLayout());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JLabel("  Historial de eventos (el más reciente arriba)"), BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    public void actualizar() {
        StringBuilder sb = new StringBuilder();
        int total = Math.min(eventos.getTamano(), 50);   // últimos 50
        for (int i = 0; i < total; i++) {
            sb.append(eventos.obtenerDesdeTope(i)).append("\n");
        }
        area.setText(sb.toString());
    }
}