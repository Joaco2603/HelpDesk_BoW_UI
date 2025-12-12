package cr.ac.ucenfotec.bl.entities;

/**
 * Clase abstracta que representa una Persona en el sistema
 * @author Equipo HelpDesk
 * @version 1.0
 */
public abstract class Persona {
    
    protected String id;
    protected String nombre;
    protected String correo;
    protected String telefono;

    /**
     * Constructor por defecto
     */
    public Persona() {
    }

    /**
     * Constructor completo de Persona
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param telefono Número de teléfono
     */
    public Persona(String id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método abstracto para obtener el rol
     * @return String con el rol
     */
    public abstract String getRol();
    
    /**
     * Método abstracto para obtener la contraseña
     * @return String con la contraseña
     */
    public abstract String getPassword();
    
    /**
     * Método abstracto para establecer la contraseña
     * @param password La contraseña a establecer
     */
    public abstract void setPassword(String password);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return id != null ? id.equals(persona.id) : persona.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}