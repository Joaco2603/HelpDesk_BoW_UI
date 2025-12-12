package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa un agente de Soporte Técnico del sistema HelpDesk
 * Hereda de Persona con permisos para gestionar tickets
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Soporte extends Persona {
    private String password;
    private static final String ROL = "soporte";
    private String especialidad;

    /**
     * Constructor por defecto
     */
    public Soporte() {
        super();
    }

    /**
     * Constructor completo de Soporte
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Número de teléfono
     */
    public Soporte(String id, String nombre, String correo, String password, String telefono) {
        super(id, nombre, correo, telefono);
        this.password = password;
    }

    /**
     * Constructor con especialidad
     * @param id Identificación única
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Número de teléfono
     * @param especialidad Área de especialización del soporte
     */
    public Soporte(String id, String nombre, String correo, String password, String telefono, String especialidad) {
        super(id, nombre, correo, telefono);
        this.password = password;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String getRol() {
        return ROL;
    }

    /**
     * Verifica si el soporte puede atender tickets
     * @return true siempre, ya que es su función principal
     */
    public boolean puedeAtenderTickets() {
        return true;
    }

    /**
     * Verifica si el soporte puede cerrar tickets
     * @return true siempre
     */
    public boolean puedeCerrarTickets() {
        return true;
    }

    /**
     * Verifica si el soporte puede reasignar tickets
     * @return true siempre
     */
    public boolean puedeReasignarTickets() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Soporte soporte = (Soporte) obj;
        return id != null ? id.equals(soporte.id) : soporte.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Soporte{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + ROL + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
