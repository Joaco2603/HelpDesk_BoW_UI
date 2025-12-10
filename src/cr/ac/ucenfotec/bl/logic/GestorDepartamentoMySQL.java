package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DepartamentosDAO;

import java.util.ArrayList;

/**
 * Gestor que maneja la lógica de negocio para los Departamentos.
 * Se comunica con el DAO para realizar operaciones de persistencia
 * y devuelve mensajes o resultados listos para la capa UI.
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorDepartamentoMySQL {
    private DepartamentosDAO departamentosDAO;

    /**
     * Constructor por defecto
     */
    public GestorDepartamentoMySQL() {
        departamentosDAO = new DepartamentosDAO();
    }

    /**
     * Agrega un nuevo departamento al sistema
     * @param id ID del departamento
     * @param nombre Nombre del departamento
     * @param descripcion Descripción
     * @param contacto Información de contacto
     * @return Mensaje de confirmación o error
     */
    public String addDepartamento(int id, String nombre, String descripcion, String contacto) {
        try {
            Departamento departamento = new Departamento(id, nombre, descripcion, contacto);
            departamentosDAO.addDepartamento(departamento);
            return "Departamento agregado exitosamente: " + departamento.toString();
        } catch (Exception e) {
            return "Error ocurrido al agregar departamento: " + e;
        }
    }

    /**
     * Obtiene todos los departamentos del sistema
     * @return String con la lista de departamentos o mensaje de error
     */
    public String getAllDepartamentos() {
        try {
            ArrayList<Departamento> lista = departamentosDAO.getDepartamentos();
            if (lista.isEmpty()) {
                return "No hay departamentos registrados";
            }
            StringBuilder result = new StringBuilder();
            for (Departamento departamento : lista) {
                result.append(departamento.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar departamentos: " + e;
        }
    }

    public String findDepartamentoById(int id) {
        try {
            Departamento departamento = departamentosDAO.findDepartamentoById(id);
            return (departamento != null) ? departamento.toString() : "Departamento no encontrado";
        } catch (Exception e) {
            return "Error ocurrido al buscar departamento por ID: " + e;
        }
    }

    public String updateDepartamento(Departamento departamento) {
        try {
            Departamento existente = departamentosDAO.findDepartamentoById(departamento.getId());
            if (existente == null) {
                return "Departamento no encontrado";
            }
            departamentosDAO.updateDepartamento(departamento);
            return "Departamento actualizado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al actualizar departamento: " + e;
        }
    }

    public String deleteDepartamento(int id) {
        try {
            Departamento existente = departamentosDAO.findDepartamentoById(id);
            if (existente == null) {
                return "Departamento no encontrado";
            }
            departamentosDAO.deleteDepartamento(id);
            return "Departamento eliminado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al eliminar departamento: " + e;
        }
    }

    /*
    public String findDepartamentoByNombre(String nombre) {
        try {
            Departamento departamento = departamentosDAO.findDepartamentoByNombre(nombre);
            return (departamento != null) ? departamento.toString() : "Departamento no encontrado";
        } catch (Exception e) {
            return "Error ocurrido al buscar departamento por nombre: " + e;
        }
    }
     */
}
