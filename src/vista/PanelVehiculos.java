package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class PanelVehiculos extends JPanel {
    private final Simulacion sim;
    private final JTextField txtIdRes = new JTextField(6);
    private final JTextField txtPlaca = new JTextField(8);
    private final JTextField txtMarca = new JTextField(8);
    private final JTextField txtModelo = new JTextField(8);
    private final JTextField txtColor = new JTextField(8);
    private final JTextField txtTipo = new JTextField("carro", 6);
    private final JTextArea area = new JTextArea();

    public PanelVehiculos(Simulacion sim) {
        this.sim = sim;
        setLayout(new BorderLayout(5, 5));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("ID residente:")); form.add(txtIdRes);
        form.add(new JLabel("Placa:"));   form.add(txtPlaca);
        form.add(new JLabel("Marca:"));   form.add(txtMarca);
        form.add(new JLabel("Modelo:"));  form.add(txtModelo);
        form.add(new JLabel("Color:"));   form.add(txtColor);
        form.add(new JLabel("Tipo:"));    form.add(txtTipo);

        JButton btnAsociar = new JButton("Asociar a residente");
        JButton btnMostrar = new JButton("Mostrar vehículos");
        JButton btnLlega = new JButton("Llega vehículo");
        JButton btnSale = new JButton("Pedir salida");
        form.add(btnAsociar); form.add(btnMostrar);
        form.add(btnLlega); form.add(btnSale);

        area.setEditable(false);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnAsociar.addActionListener(e -> asociar());
        btnMostrar.addActionListener(e -> mostrar());
        btnLlega.addActionListener(e -> llega());
        btnSale.addActionListener(e -> sale());
    }

    private Vehiculo leerVehiculo() {
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            aviso("La placa es obligatoria");
            return null;
        }
        return new Vehiculo(placa, txtMarca.getText().trim(), txtModelo.getText().trim(),
                txtColor.getText().trim(), txtTipo.getText().trim());
    }

    private void asociar() {
        Residente r = sim.getGestor().buscarPorId(txtIdRes.getText().trim());
        if (r == null) { aviso("No existe ese residente"); return; }
        Vehiculo v = leerVehiculo();
        if (v == null) return;
        if (!r.agregarVehiculo(v)) {
            aviso("No se pudo: máximo 3 vehículos o placa repetida");
            return;
        }
        mostrar();
    }

    private void mostrar() {
        Residente r = sim.getGestor().buscarPorId(txtIdRes.getText().trim());
        if (r == null) { aviso("No existe ese residente"); return; }
        StringBuilder sb = new StringBuilder(r + (r.isSocio() ? "  [SOCIO]" : "  [no socio]") + "\n");
        for (int i = 0; i < r.getVehiculos().getTamano(); i++) {
            sb.append("  ").append(r.getVehiculos().obtenerEn(i)).append("\n");
        }
        area.setText(sb.toString());
    }

    // Si la placa pertenece a un residente, usa ese vehículo; si no, es visitante
    private void llega() {
        Vehiculo v = leerVehiculo();
        if (v == null) return;
        Residente dueno = sim.getGestor().buscarDuenoPorPlaca(v.getPlaca());
        if (dueno == null) {
            sim.getEventos().apilar(new Evento("Visitante registrado: " + v.getPlaca()));
        }
        v.setEstado("EN_COLA");
        sim.llegaVehiculo(v);
    }

    private void sale() {
        Vehiculo v = leerVehiculo();
        if (v == null) return;
        sim.pideSalida(v);
    }

    private void aviso(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}