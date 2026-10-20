package persistencia;

import modelo.*;
import estructuras.ListaSimple;
import java.io.*;

public class Persistencia {

    public static void guardar(GestorResidentes gestor,
                               String archivoRes, String archivoVeh) throws IOException {
        try (PrintWriter pwRes = new PrintWriter(new FileWriter(archivoRes));
             PrintWriter pwVeh = new PrintWriter(new FileWriter(archivoVeh))) {

            for (int i = 0; i < gestor.getTotal(); i++) {
                Residente r = gestor.obtenerEn(i);
                pwRes.println(r.getId() + "|" + r.getNombre() + "|"
                              + r.getCasa() + "|" + r.isSocio());

                ListaSimple<Vehiculo> lista = r.getVehiculos();
                for (int j = 0; j < lista.getTamano(); j++) {
                    Vehiculo v = lista.obtenerEn(j);
                    pwVeh.println(v.getPlaca() + "|" + v.getMarca() + "|"
                                  + v.getModelo() + "|" + v.getColor() + "|"
                                  + r.getId());
                }
            }
        }
    }

    public static GestorResidentes cargar(String archivoRes, String archivoVeh)
            throws IOException {
        GestorResidentes gestor = new GestorResidentes();

        File fRes = new File(archivoRes);
        File fVeh = new File(archivoVeh);

        // Fase 1: residentes
        if (fRes.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fRes))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] p = linea.split("\\|");
                    boolean socio = Boolean.parseBoolean(p[3]);
                    gestor.registrar(new Residente(p[0], p[1], p[2], socio));
                }
            }
        }

        // Fase 2: vehículos
        if (fVeh.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fVeh))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] p = linea.split("\\|");
                    Residente dueno = gestor.buscarPorId(p[4]);
                    if (dueno != null) {
                        dueno.agregarVehiculo(new Vehiculo(p[0], p[1], p[2], p[3], "carro"));
                    }
                }
            }
        }
        return gestor;
    }
}