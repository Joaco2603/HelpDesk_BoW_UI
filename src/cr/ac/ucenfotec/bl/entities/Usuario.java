package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa un Usuario del sistema HelpDesk
 * Hereda de Persona y añade funcionalidades específicas de usuario
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Usuario extends Persona {
    private String password;
    private String rol;

    /**
     * Constructor por defecto
     */
    public Usuario() {
        super();
    }

    /**
     * Constructor completo de Usuario
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Número de teléfono
     * @param rol Rol del usuario (admin, soporte, usuario)
     */
    public Usuario(String id, String nombre, String correo, String password, String telefono, String rol) {
        super(id, nombre, correo, telefono);
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters específicos
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Usuario usuario = (Usuario) obj;
        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}