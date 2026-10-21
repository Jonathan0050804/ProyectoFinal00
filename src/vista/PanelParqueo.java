package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class PanelParqueo extends JPanel {
    private final Parqueo parqueo;
    private final JLabel[][] celdasSocios = new JLabel[3][25];
    private final JLabel[][] celdasGeneral = new JLabel[5][15];

    public PanelParqueo(Parqueo parqueo) {
        this.parqueo = parqueo;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearSeccion("Área de Socios (A - C)", 3, 25, parqueo.getInicioSocios(), celdasSocios));
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(crearSeccion("Área General (E - I)", 5, 15, parqueo.getInicioGeneral(), celdasGeneral));
    }

    private JPanel crearSeccion(String titulo, int filas, int columnas,
                                Espacio inicio, JLabel[][] matrizCeldas) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JPanel grid = new JPanel(new GridLayout(filas, columnas, 2, 2));

        Espacio filaActual = inicio;
        for (int f = 0; f < filas; f++) {
            Espacio colActual = filaActual;
            for (int c = 0; c < columnas; c++) {
                JLabel celda = new JLabel(colActual != null ? colActual.getId() : "",
                                          SwingConstants.CENTER);
                celda.setOpaque(true);
                celda.setBackground(new Color(144, 238, 144));
                celda.setForeground(Color.BLACK);
                celda.setFont(new Font("SansSerif", Font.BOLD, 9));
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                celda.setPreferredSize(new Dimension(35, 25));

                matrizCeldas[f][c] = celda;
                grid.add(celda);

                if (colActual != null) colActual = colActual.derecha;
            }
            if (filaActual != null) filaActual = filaActual.abajo;
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    public void actualizar() {
        actualizarMatriz(3, 25, parqueo.getInicioSocios(), celdasSocios);
        actualizarMatriz(5, 15, parqueo.getInicioGeneral(), celdasGeneral);
    }

    private void actualizarMatriz(int filas, int columnas,
                                  Espacio inicio, JLabel[][] matrizCeldas) {
        Espacio filaActual = inicio;
        for (int f = 0; f < filas; f++) {
            Espacio colActual = filaActual;
            for (int c = 0; c < columnas; c++) {
                if (colActual != null) {
                    JLabel celda = matrizCeldas[f][c];
                    Vehiculo v = colActual.getVehiculo();
                    if (colActual.isOcupado() && v != null) {
                        celda.setBackground(new Color(255, 102, 102));
                        celda.setText(v.getPlaca());
                    } else {
                        celda.setBackground(new Color(144, 238, 144));
                        celda.setText(colActual.getId());
                    }
                    colActual = colActual.derecha;
                }
            }
            if (filaActual != null) filaActual = filaActual.abajo;
        }
    }
}