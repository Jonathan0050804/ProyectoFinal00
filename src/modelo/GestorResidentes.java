package modelo;

import estructuras.ListaDoble;

public class GestorResidentes {
    private ListaDoble<Residente> residentes = new ListaDoble<>();

    public boolean registrar(Residente r) {
        if (residentes.buscar(r)) {
            return false;
        }
        residentes.insertarAlFinal(r);
        return true;
    }

    public Residente buscarPorId(String id) {
        for (int i = 0; i < residentes.getTamano(); i++) {
            Residente r = residentes.obtenerEn(i);
            if (r != null && r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    // Devuelve el residente dueño de la placa, o null si es visitante
    public Residente buscarDuenoPorPlaca(String placa) {
        for (int i = 0; i < residentes.getTamano(); i++) {
            Residente r = residentes.obtenerEn(i);
            for (int j = 0; j < r.getVehiculos().getTamano(); j++) {
                if (r.getVehiculos().obtenerEn(j).getPlaca().equals(placa)) {
                    return r;
                }
            }
        }
        return null;
    }

    public boolean modificar(String id, String nombre, String casa, boolean socio) {
        Residente r = buscarPorId(id);
        if (r == null) {
            return false;
        }
        r.setNombre(nombre);
        r.setCasa(casa);
        r.setSocio(socio);
        return true;
    }

    public boolean eliminar(String id) {
        Residente r = buscarPorId(id);
        if (r == null) {
            return false;
        }
        residentes.eliminar(r);
        return true;
    }

    public Residente obtenerEn(int posicion) {
        return residentes.obtenerEn(posicion);
    }

    public int getTotal() {
        return residentes.getTamano();
    }
}