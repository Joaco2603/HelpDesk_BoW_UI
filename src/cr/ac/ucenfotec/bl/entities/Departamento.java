package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa un Departamento en el sistema HelpDesk
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Departamento {
    private int id;
    private String nombre;
    private String descripcion;
    private String contacto;

    /**
     * Constructor por defecto
     */
    public Departamento() {
    }

    /**
     * Constructor completo de Departamento
     * @param id Identificador único del departamento
     * @param nombre Nombre del departamento
     * @param descripcion Descripción de las funciones
     * @param contacto Información de contacto
     */
    public Departamento(int id, String nombre, String descripcion, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contacto = contacto;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Departamento that = (Departamento) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}