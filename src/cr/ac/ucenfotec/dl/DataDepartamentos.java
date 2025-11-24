package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Departamento;

import java.util.ArrayList;

/**
 * Capa de datos para la gestión de departamentos
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataDepartamentos {
    private ArrayList<Departamento> departamentos;

    /**
     * Constructor por defecto que inicializa con datos de prueba
     */
    public DataDepartamentos() {
        departamentos = new ArrayList<>();
        
        // Datos de prueba
        departamentos.add(new Departamento(1, "Soporte Técnico", "Departamento encargado de resolver problemas técnicos", "soporte@helpdesk.com"));
        departamentos.add(new Departamento(2, "Recursos Humanos", "Departamento de gestión de personal", "rrhh@helpdesk.com"));
        departamentos.add(new Departamento(3, "Sistemas", "Departamento de infraestructura IT", "sistemas@helpdesk.com"));
        departamentos.add(new Departamento(4, "Administración", "Departamento administrativo", "admin@helpdesk.com"));
    }

    /**
     * Agrega un departamento a la lista
     * @param departamento Departamento a agregar
     */
    public void addDepartamento(Departamento departamento) {
        if (departamentos == null) {
            departamentos = new ArrayList<>();
        }
        departamentos.add(departamento);
    }

    /**
     * Obtiene todos los departamentos
     * @return Lista de departamentos
     */
    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }
}