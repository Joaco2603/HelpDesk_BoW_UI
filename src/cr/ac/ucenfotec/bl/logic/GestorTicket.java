package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DataTickets;

import java.time.LocalDateTime;

/**
 * Gestor que maneja la lógica de negocio para los Tickets
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorTicket {
    private DataTickets dataTickets;

    /**
     * Constructor por defecto
     */
    public GestorTicket() {
        dataTickets = new DataTickets();
    }

    /**
     * Crea un nuevo ticket en el sistema
     * @param id ID del ticket
     * @param asunto Asunto del ticket
     * @param descripcion Descripción detallada
     * @param prioridad Prioridad (Baja/Media/Alta)
     * @param usuario Usuario que crea el ticket
     * @param departamento Departamento asignado
     * @return String con la información del ticket creado
     */
    public String createTicket(int id, String asunto, String descripcion, String prioridad, Usuario usuario, Departamento departamento) {
        String estado = "Abierto";
        Ticket ticket = new Ticket(id, asunto, descripcion, estado, prioridad, usuario, departamento);
        dataTickets.addTicket(ticket);
        return ticket.toString();
    }

    /**
     * Busca un ticket por su ID
     * @param id ID del ticket
     * @return String con la información del ticket
     */
    public String findTicketById(int id) {
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getId() == id) {
                return ticket.toString();
            }
        }
        return "Ticket no encontrado";
    }

    /**
     * Obtiene todos los tickets de un usuario específico
     * @param usuarioId ID del usuario
     * @return String con la lista de tickets del usuario
     */
    public String getTicketsByUsuario(String usuarioId) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getUsuario().getId().equals(usuarioId)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets para este usuario";
    }

    /**
     * Obtiene todos los tickets de un departamento específico
     * @param departamentoId ID del departamento
     * @return String con la lista de tickets del departamento
     */
    public String getTicketsByDepartamento(String departamentoId) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getDepartamento().getId() == Integer.parseInt(departamentoId)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets para este departamento";
    }

    /**
     * Obtiene todos los tickets con un estado específico
     * @param estado Estado a filtrar
     * @return String con la lista de tickets del estado
     */
    public String getTicketsByEstado(String estado) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getEstado().equalsIgnoreCase(estado)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets con estado: " + estado;
    }

    /**
     * Obtiene todos los tickets con una prioridad específica
     * @param prioridad Prioridad a filtrar
     * @return String con la lista de tickets de la prioridad
     */
    public String getTicketsByPrioridad(String prioridad) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getPrioridad().equalsIgnoreCase(prioridad)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets con prioridad: " + prioridad;
    }

    /**
     * Obtiene todos los tickets del sistema
     * @return String con la lista completa de tickets
     */
    public String getAllTickets() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (count > 0) {
                result.append("\n");
            }
            result.append(ticket.toString());
            count++;
        }
        return count > 0 ? result.toString() : "No hay tickets registrados";
    }

    /**
     * Actualiza la información de un ticket
     * @param ticket Ticket con la información actualizada
     * @return Mensaje de confirmación
     */
    public String updateTicket(Ticket ticket) {
        ticket.setFechaActualizacion(LocalDateTime.now());
        for (int i = 0; i < dataTickets.getTickets().size(); i++) {
            if (dataTickets.getTickets().get(i).equals(ticket)) {
                dataTickets.getTickets().set(i, ticket);
                return "Ticket actualizado exitosamente";
            }
        }
        return "Error: Ticket no encontrado";
    }

    /**
     * Actualiza el estado de un ticket específico
     * @param ticketId ID del ticket
     * @param nuevoEstado Nuevo estado del ticket
     * @return Mensaje de confirmación
     */
    public String updateEstadoTicket(int ticketId, String nuevoEstado) {
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getId() == ticketId) {
                ticket.setEstado(nuevoEstado);
                ticket.setFechaActualizacion(LocalDateTime.now());
                return "Estado actualizado a: " + nuevoEstado;
            }
        }
        return "Error: Ticket no encontrado";
    }

    /**
     * Elimina un ticket del sistema
     * @param id ID del ticket a eliminar
     * @return Mensaje de confirmación
     */
    public String deleteTicket(int id) {
        for (int i = 0; i < dataTickets.getTickets().size(); i++) {
            if (dataTickets.getTickets().get(i).getId() == id) {
                dataTickets.getTickets().remove(i);
                return "Ticket eliminado exitosamente";
            }
        }
        return "Error: Ticket no encontrado";
    }
}