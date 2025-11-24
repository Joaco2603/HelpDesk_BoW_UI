package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DataDepartamentos;

/**
 * Gestor que maneja la lógica de negocio para los Departamentos
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorDepartamento {
    private DataDepartamentos dataDepartamentos;

    /**
     * Constructor por defecto
     */
    public GestorDepartamento() {
        dataDepartamentos = new DataDepartamentos();
    }

    /**
     * Agrega un nuevo departamento al sistema
     * @param id ID del departamento
     * @param nombre Nombre del departamento
     * @param descripcion Descripción
     * @param contacto Información de contacto
     * @return String con la información del departamento creado
     */
    public String addDepartamento(int id, String nombre, String descripcion, String contacto) {
        Departamento departamento = new Departamento(id, nombre, descripcion, contacto);
        dataDepartamentos.addDepartamento(departamento);
        return departamento.toString();
    }

    /**
     * Busca un departamento por su ID
     * @param id ID del departamento
     * @return String con la información del departamento
     */
    public String findDepartamentoById(int id) {
        for (Departamento depto : dataDepartamentos.getDepartamentos()) {
            if (depto.getId() == id) {
                return depto.toString();
            }
        }
        return "Departamento no encontrado";
    }

    /**
     * Busca un departamento por su nombre
     * @param nombre Nombre del departamento
     * @return String con la información del departamento
     */
    public String findDepartamentoByNombre(String nombre) {
        for (Departamento depto : dataDepartamentos.getDepartamentos()) {
            if (depto.getNombre().equalsIgnoreCase(nombre)) {
                return depto.toString();
            }
        }
        return "Departamento no encontrado";
    }

    /**
     * Obtiene todos los departamentos del sistema
     * @return String con la lista de departamentos
     */
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

    /**
     * Actualiza la información de un departamento
     * @param departamento Departamento con la información actualizada
     * @return Mensaje de confirmación
     */
    public String updateDepartamento(Departamento departamento) {
        for (int i = 0; i < dataDepartamentos.getDepartamentos().size(); i++) {
            if (dataDepartamentos.getDepartamentos().get(i).equals(departamento)) {
                dataDepartamentos.getDepartamentos().set(i, departamento);
                return "Departamento actualizado exitosamente";
            }
        }
        return "Error: Departamento no encontrado";
    }

    /**
     * Elimina un departamento del sistema
     * @param id ID del departamento a eliminar
     * @return Mensaje de confirmación
     */
    public String deleteDepartamento(String id) {
        for (int i = 0; i < dataDepartamentos.getDepartamentos().size(); i++) {
            if (dataDepartamentos.getDepartamentos().get(i).getId() == Integer.parseInt(id)) {
                dataDepartamentos.getDepartamentos().remove(i);
                return "Departamento eliminado exitosamente";
            }
        }
        return "Error: Departamento no encontrado";
    }
}