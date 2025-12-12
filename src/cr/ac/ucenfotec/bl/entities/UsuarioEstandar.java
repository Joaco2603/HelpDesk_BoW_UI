package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa un Usuario Estándar del sistema HelpDesk
 * Hereda de Persona con permisos básicos para crear y ver sus propios tickets
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class UsuarioEstandar extends Persona {
    private String password;
    private static final String ROL = "usuario";

    /**
     * Constructor por defecto
     */
    public UsuarioEstandar() {
        super();
    }

    /**
     * Constructor completo de UsuarioEstandar
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Número de teléfono
     */
    public UsuarioEstandar(String id, String nombre, String correo, String password, String telefono) {
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
     * Verifica si el usuario puede crear tickets
     * @return true siempre, ya que es su función principal
     */
    public boolean puedeCrearTickets() {
        return true;
    }

    /**
     * Verifica si el usuario puede ver sus propios tickets
     * @return true siempre
     */
    public boolean puedeVerSusTickets() {
        return true;
    }

    /**
     * Verifica si el usuario puede modificar tickets de otros
     * @return false siempre, solo puede modificar los suyos
     */
    public boolean puedeModificarTicketsDeOtros() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        UsuarioEstandar usuario = (UsuarioEstandar) obj;
        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UsuarioEstandar{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + ROL + '\'' +
                '}';
    }
}
