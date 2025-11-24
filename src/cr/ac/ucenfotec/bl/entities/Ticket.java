package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDateTime;

/**
 * Clase que representa un Ticket de soporte en el sistema HelpDesk
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Ticket {
    private int id;
    private String asunto;
    private String descripcion;
    private String estado;
    private String prioridad;
    private Usuario usuario;
    private Departamento departamento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    /**
     * Constructor por defecto
     */
    public Ticket() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Constructor completo de Ticket
     * @param id Identificador único del ticket
     * @param asunto Asunto del ticket
     * @param descripcion Descripción detallada
     * @param estado Estado actual (Abierto/En Proceso/Cerrado)
     * @param prioridad Prioridad (Baja/Media/Alta)
     * @param usuario Usuario que creó el ticket
     * @param departamento Departamento asignado
     */
    public Ticket(int id, String asunto, String descripcion, String estado, String prioridad, Usuario usuario, Departamento departamento) {
        this.id = id;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuario = usuario;
        this.departamento = departamento;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket ticket = (Ticket) obj;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "N/A") +
                ", departamento=" + (departamento != null ? departamento.getNombre() : "N/A") +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}