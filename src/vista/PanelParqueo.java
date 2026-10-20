package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class PanelParqueo extends JPanel {
    private final Parqueo parqueo;

    public PanelParqueo(Parqueo parqueo) {
        this.parqueo = parqueo;
        setLayout(new GridLayout(2, 1, 5, 5));   // área socios arriba, general abajo
        add(crearArea("Área de socios (A-C)", parqueo.getInicioSocios(), 25));
        add(crearArea("Área general (E-I)", parqueo.getInicioGeneral(), 15));
    }

    private JPanel crearArea(String titulo, Espacio inicio, int columnas) {
        JPanel area = new JPanel(new GridLayout(0, columnas, 2, 2));
        area.setBorder(BorderFactory.createTitledBorder(titulo));

        Espacio inicioFila = inicio;
        while (inicioFila != null) {
            Espacio actual = inicioFila;
            while (actual != null) {
                JLabel celda = new JLabel(actual.getId(), SwingConstants.CENTER);
                celda.setOpaque(true);
                celda.setBackground(Color.GREEN);
                celda.setFont(new Font("SansSerif", Font.PLAIN, 9));
                area.add(celda);
                actual = actual.derecha;
            }
            inicioFila = inicioFila.abajo;
        }
        return area;
    }
}