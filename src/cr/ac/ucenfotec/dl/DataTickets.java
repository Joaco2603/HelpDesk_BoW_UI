package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Ticket;

import java.util.ArrayList;

/**
 * Capa de datos para la gestión de tickets
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataTickets {
    private ArrayList<Ticket> tickets;

    /**
     * Constructor por defecto
     */
    public DataTickets() {
        tickets = new ArrayList<>();
    }

    /**
     * Agrega un ticket a la lista
     * @param ticket Ticket a agregar
     */
    public void addTicket(Ticket ticket) {
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
    }

    /**
     * Obtiene todos los tickets
     * @return Lista de tickets
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}