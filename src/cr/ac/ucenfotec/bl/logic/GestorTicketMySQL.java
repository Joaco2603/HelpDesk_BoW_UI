package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.TicketsDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Gestor que maneja la lógica de negocio para los Tickets.
 * Se comunica con el DAO para realizar operaciones de persistencia
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorTicketMySQL {
    private TicketsDAO ticketsDAO;

    /**
     * Constructor por defecto.
     * Inicializa el DAO que se encargará de la persistencia en MySQL.
     */
    public GestorTicketMySQL() {
        ticketsDAO = new TicketsDAO();
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
        try {
            String estado = "Abierto";
            Ticket ticket = new Ticket(id, asunto, descripcion, estado, prioridad, usuario, departamento);
            ticketsDAO.addTicket(ticket);
            return "Ticket creado exitosamente: " + ticket.toString();
        } catch (Exception e) {
            return "Error ocurrido al crear ticket: " + e;
        }
    }

    public String getAllTickets() {
        try {
            ArrayList<Ticket> lista = ticketsDAO.getTickets();
            if (lista.isEmpty()) return "No hay tickets registrados";
            StringBuilder result = new StringBuilder();
            for (Ticket ticket : lista) {
                result.append(ticket.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar tickets: " + e;
        }
    }

    public String findTicketById(int id) {
        try {
            Ticket ticket = ticketsDAO.findTicketById(id);
            return (ticket != null) ? ticket.toString() : "Ticket no encontrado";
        } catch (Exception e) {
            return "Error ocurrido al buscar ticket por ID: " + e;
        }
    }

    public String updateTicket(Ticket ticket) {
        try {
            Ticket existente = ticketsDAO.findTicketById(ticket.getId());
            if (existente == null) return "Error: Ticket no encontrado";
            ticket.setFechaActualizacion(LocalDateTime.now());
            ticketsDAO.updateTicket(ticket);
            return "Ticket actualizado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al actualizar ticket: " + e;
        }
    }

    public String deleteTicket(int id) {
        try {
            Ticket existente = ticketsDAO.findTicketById(id);
            if (existente == null) return "Error: Ticket no encontrado";
            ticketsDAO.deleteTicket(id);
            return "Ticket eliminado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al eliminar ticket: " + e;
        }
    }

    public String getTicketsByUsuario(String usuarioId) {
        try {
            ArrayList<Ticket> lista = ticketsDAO.getTicketsByUsuario(usuarioId);
            if (lista.isEmpty()) return "No se encontraron tickets para este usuario";
            StringBuilder result = new StringBuilder();
            for (Ticket ticket : lista) {
                result.append(ticket.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar tickets por usuario: " + e;
        }
    }

    public String getTicketsByDepartamento(int departamentoId) {
        try {
            ArrayList<Ticket> lista = ticketsDAO.getTicketsByDepartamento(departamentoId);
            if (lista.isEmpty()) return "No se encontraron tickets para este departamento";
            StringBuilder result = new StringBuilder();
            for (Ticket ticket : lista) {
                result.append(ticket.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar tickets por departamento: " + e;
        }
    }

    public String getTicketsByEstado(String estado) {
        try {
            ArrayList<Ticket> lista = ticketsDAO.getTicketsByEstado(estado);
            if (lista.isEmpty()) return "No se encontraron tickets con estado: " + estado;
            StringBuilder result = new StringBuilder();
            for (Ticket ticket : lista) {
                result.append(ticket.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar tickets por estado: " + e;
        }
    }

    public String getTicketsByPrioridad(String prioridad) {
        try {
            ArrayList<Ticket> lista = ticketsDAO.getTicketsByPrioridad(prioridad);
            if (lista.isEmpty()) return "No se encontraron tickets con prioridad: " + prioridad;
            StringBuilder result = new StringBuilder();
            for (Ticket ticket : lista) {
                result.append(ticket.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar tickets por prioridad: " + e;
        }
    }
    //se tiene update (todo) pero no updateEstado
}


