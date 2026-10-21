package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class PanelResidentes extends JPanel {
    private final GestorResidentes gestor;
    private final JTextField txtId = new JTextField(8);
    private final JTextField txtNombre = new JTextField(12);
    private final JTextField txtCasa = new JTextField(8);
    private final JCheckBox chkSocio = new JCheckBox("Es socio");
    private final JTextArea area = new JTextArea();

    public PanelResidentes(GestorResidentes gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout(5, 5));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("ID:"));      form.add(txtId);
        form.add(new JLabel("Nombre:"));  form.add(txtNombre);
        form.add(new JLabel("Casa:"));    form.add(txtCasa);
        form.add(chkSocio);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        form.add(btnRegistrar); form.add(btnBuscar);
        form.add(btnModificar); form.add(btnEliminar);

        area.setEditable(false);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());

        refrescar();
    }

    private void registrar() {
        String id = txtId.getText().trim();
        if (id.isEmpty() || txtNombre.getText().trim().isEmpty()) {
            aviso("ID y nombre son obligatorios");
            return;
        }
        Residente r = new Residente(id, txtNombre.getText().trim(),
                txtCasa.getText().trim(), chkSocio.isSelected());
        if (!gestor.registrar(r)) {
            aviso("Ya existe un residente con ese ID");
            return;
        }
        refrescar();
    }

    private void buscar() {
        Residente r = gestor.buscarPorId(txtId.getText().trim());
        if (r == null) {
            aviso("No encontrado");
            return;
        }
        txtNombre.setText(r.getNombre());
        txtCasa.setText(r.getCasa());
        chkSocio.setSelected(r.isSocio());
    }

    private void modificar() {
        boolean ok = gestor.modificar(txtId.getText().trim(), txtNombre.getText().trim(),
                txtCasa.getText().trim(), chkSocio.isSelected());
        if (!ok) aviso("No existe ese ID");
        refrescar();
    }

    private void eliminar() {
        if (!gestor.eliminar(txtId.getText().trim())) aviso("No existe ese ID");
        refrescar();
    }

    private void refrescar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gestor.getTotal(); i++) {
            Residente r = gestor.obtenerEn(i);
            sb.append(r).append("  |  ").append(r.getCasa())
              .append("  |  vehículos: ").append(r.getVehiculos().getTamano()).append("\n");
        }
        area.setText(sb.toString());
    }

    private void aviso(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}