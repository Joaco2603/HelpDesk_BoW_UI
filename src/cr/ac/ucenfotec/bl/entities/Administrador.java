package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa un Administrador del sistema HelpDesk
 * Hereda de Persona con permisos completos
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Administrador extends Persona {
    private String password;
    private static final String ROL = "admin";

    /**
     * Constructor por defecto
     */
    public Administrador() {
        super();
    }

    /**
     * Constructor completo de Administrador
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Número de teléfono
     */
    public Administrador(String id, String nombre, String correo, String password, String telefono) {
        super(id, nombre, correo, telefono);
        this.password = password;
    }

    // Getters y Setters específicos
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRol() {
        return ROL;
    }

    /**
     * Verifica si el administrador puede gestionar usuarios
     * @return true siempre, ya que el admin tiene permisos completos
     */
    public boolean puedeGestionarUsuarios() {
        return true;
    }

    /**
     * Verifica si el administrador puede gestionar departamentos
     * @return true siempre, ya que el admin tiene permisos completos
     */
    public boolean puedeGestionarDepartamentos() {
        return true;
    }

    /**
     * Verifica si el administrador puede gestionar todos los tickets
     * @return true siempre, ya que el admin tiene permisos completos
     */
    public boolean puedeGestionarTodosLosTickets() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Administrador admin = (Administrador) obj;
        return id != null ? id.equals(admin.id) : admin.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + ROL + '\'' +
                '}';
    }
}
