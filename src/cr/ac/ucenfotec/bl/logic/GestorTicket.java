package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DataTickets;

import java.time.LocalDateTime;

public class GestorTicket {
    private DataTickets dataTickets;

    public GestorTicket() {
        dataTickets = new DataTickets();
    }

    public String createTicket(int id, String asunto, String descripcion, String prioridad, Usuario usuario, Departamento departamento) {
        String estado = "Abierto";
        Ticket ticket = new Ticket(id, asunto, descripcion, estado, prioridad, usuario, departamento);
        dataTickets.addTicket(ticket);
        return ticket.toString();
    }

    public String findTicketById(int id) {
        Ticket buscado = new Ticket();
        buscado.setId(id);
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.equals(buscado)) {
                return ticket.toString();
            }
        }
        return "Ticket no encontrado";
    }

    public String getTicketsByUsuario(String usuarioId) {
        Usuario buscado = new Usuario();
        buscado.setId(usuarioId);
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getUsuario().equals(buscado)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets para este usuario";
    }

    public String getTicketsByDepartamento(String departamentoId) {
        Departamento buscado = new Departamento();
        buscado.setId(Integer.parseInt(departamentoId));
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.getDepartamento().equals(buscado)) {
                if (count > 0) {
                    result.append("\n");
                }
                result.append(ticket.toString());
                count++;
            }
        }
        return count > 0 ? result.toString() : "No se encontraron tickets para este departamento";
    }

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

    public String updateEstadoTicket(int ticketId, String nuevoEstado) {
        Ticket buscado = new Ticket();
        buscado.setId(ticketId);
        for (Ticket ticket : dataTickets.getTickets()) {
            if (ticket.equals(buscado)) {
                ticket.setEstado(nuevoEstado);
                ticket.setFechaActualizacion(LocalDateTime.now());
                return "Estado actualizado a: " + nuevoEstado;
            }
        }
        return "Error: Ticket no encontrado";
    }

    public String deleteTicket(int id) {
        Ticket buscado = new Ticket();
        buscado.setId(id);
        for (int i = 0; i < dataTickets.getTickets().size(); i++) {
            if (dataTickets.getTickets().get(i).equals(buscado)) {
                dataTickets.getTickets().remove(i);
                return "Ticket eliminado exitosamente";
            }
        }
        return "Error: Ticket no encontrado";
    }
}
