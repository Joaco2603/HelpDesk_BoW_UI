package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DataDepartamentos;

public class GestorDepartamento {
    private DataDepartamentos dataDepartamentos;

    public GestorDepartamento() {
        dataDepartamentos = new DataDepartamentos();
    }

    public String addDepartamento(int id, String nombre, String descripcion, String contacto) {
        Departamento departamento = new Departamento(id, nombre, descripcion, contacto);
        dataDepartamentos.addDepartamento(departamento);
        return departamento.toString();
    }

    public String findDepartamentoById(int id) {
        Departamento buscado = new Departamento();
        buscado.setId(id);
        for (Departamento depto : dataDepartamentos.getDepartamentos()) {
            if (depto.equals(buscado)) {
                return depto.toString();
            }
        }
        return "Departamento no encontrado";
    }

    public String findDepartamentoByNombre(String nombre) {
        for (Departamento depto : dataDepartamentos.getDepartamentos()) {
            if (depto.getNombre().equalsIgnoreCase(nombre)) {
                return depto.toString();
            }
        }
        return "Departamento no encontrado";
    }

    public String getAllDepartamentos() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Departamento departamento : dataDepartamentos.getDepartamentos()) {
            if (count > 0) {
                result.append("\n");
            }
            result.append(departamento.toString());
            count++;
        }
        return count > 0 ? result.toString() : "No hay departamentos registrados";
    }

    public String updateDepartamento(Departamento departamento) {
        for (int i = 0; i < dataDepartamentos.getDepartamentos().size(); i++) {
            if (dataDepartamentos.getDepartamentos().get(i).equals(departamento)) {
                dataDepartamentos.getDepartamentos().set(i, departamento);
                return "Departamento actualizado exitosamente";
            }
        }
        return "Error: Departamento no encontrado";
    }

    public String deleteDepartamento(String id) {
        Departamento buscado = new Departamento();
        buscado.setId(Integer.parseInt(id));
        for (int i = 0; i < dataDepartamentos.getDepartamentos().size(); i++) {
            if (dataDepartamentos.getDepartamentos().get(i).equals(buscado)) {
                dataDepartamentos.getDepartamentos().remove(i);
                return "Departamento eliminado exitosamente";
            }
        }
        return "Error: Departamento no encontrado";
    }
}
