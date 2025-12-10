package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

//SIN TRY CATCH PARA MANEJARLO EN EL GESTOR Y SEGUIR ARQUITECTURA

/**
 * DAO / capa de datos para la gestión de tickets en MySQL.
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class TicketsDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test_schema";
    private String user = "root";
    private String pass = "C0s1C0s1MySQL$$";

    /**
     * Inserta un ticket en la base de datos.
     *
     * @param ticket Ticket a insertar
     */
    public void addTicket(Ticket ticket) throws ClassNotFoundException, SQLException {
        // cargar driver
        Class.forName(driver);
        // construir consulta con los datos del ticket
        String query = "INSERT INTO tickets (id, asunto, descripcion, estado, prioridad, usuarioId, departamentoId, fechaCreacion, fechaActualizacion) VALUES (" +
                ticket.getId() + ", '" +
                ticket.getAsunto() + "', '" +
                ticket.getDescripcion() + "', '" +
                ticket.getEstado() + "', '" +
                ticket.getPrioridad() + "', '" +
                ticket.getUsuario().getId() + "', " +
                ticket.getDepartamento().getId() + ", '" +
                ticket.getFechaCreacion() + "', '" +
                ticket.getFechaActualizacion() + "')";
        // ejecutar consulta
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    /**
     * Obtiene todos los tickets desde la base de datos.
     *
     * @return Lista de tickets
     */
    public ArrayList<Ticket> getTickets() throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "SELECT id, asunto, descripcion, estado, prioridad, usuarioId, departamentoId, fechaCreacion, fechaActualizacion FROM tickets";
        //ejecutar consulta y capturar resultado
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        //procesar resultados
        while (rs.next()) {
            Ticket tmpTicket = resultSetParaTicket(rs);
            tickets.add(tmpTicket);
        }
        return tickets;
    }


    //helper para no repetir tanto código: asigna ResultSet a Ticket
    private Ticket resultSetParaTicket(ResultSet rs) throws SQLException {
        Ticket tmpTicket = new Ticket();
        tmpTicket.setId(rs.getInt("id"));
        tmpTicket.setAsunto(rs.getString("asunto"));
        tmpTicket.setDescripcion(rs.getString("descripcion"));
        tmpTicket.setEstado(rs.getString("estado"));
        tmpTicket.setPrioridad(rs.getString("prioridad"));

        Usuario tmpUsuario = new Usuario();
        tmpUsuario.setId(rs.getString("usuarioId"));
        tmpTicket.setUsuario(tmpUsuario);
        Departamento tmpDepartamento = new Departamento();
        tmpDepartamento.setId(rs.getInt("departamentoId"));
        tmpTicket.setDepartamento(tmpDepartamento);

        tmpTicket.setFechaCreacion(LocalDateTime.parse(rs.getString("fechaCreacion")));
        tmpTicket.setFechaActualizacion(LocalDateTime.parse(rs.getString("fechaActualizacion")));
        return tmpTicket;
    }

    public void updateTicket(Ticket ticket) throws ClassNotFoundException, SQLException {
        //cargar driver
        Class.forName(driver);
        //construir consulta de modificar
        String query = "UPDATE tickets SET asunto='" + ticket.getAsunto() +
                "', descripcion='" + ticket.getDescripcion() +
                "', estado='" + ticket.getEstado() +
                "', prioridad='" + ticket.getPrioridad() +
                "', usuarioId='" + ticket.getUsuario().getId() +
                "', departamentoId=" + ticket.getDepartamento().getId() +
                ", fechaActualizacion='" + ticket.getFechaActualizacion() +
                "' WHERE id=" + ticket.getId();
        //ejecutar consulta
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    public void deleteTicket(int id) throws ClassNotFoundException, SQLException {
        //cargar driver
        Class.forName(driver);
        //construir consulta de borrar
        String query = "DELETE FROM tickets WHERE id=" + id;
        //ejecutar consulta
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    public ArrayList<Ticket> getTicketsByUsuario(String usuarioId) throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta con filtro por usuario
        String query = "SELECT * FROM tickets WHERE usuarioId='" + usuarioId + "'";
        //ejecutar consulta
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        while (rs.next()) {
            tickets.add(resultSetParaTicket(rs));
        }
        return tickets;
    }

    public ArrayList<Ticket> getTicketsByDepartamento(int departamentoId) throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta con filtro por departamento
        String query = "SELECT * FROM tickets WHERE departamentoId=" + departamentoId;
        //ejecutar consulta
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        while (rs.next()) {
            tickets.add(resultSetParaTicket(rs));
        }
        return tickets;
    }

    public ArrayList<Ticket> getTicketsByEstado(String estado) throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta con filtro por estado
        String query = "SELECT * FROM tickets WHERE estado='" + estado + "'";
        //ejecutar consulta
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        while (rs.next()) {
            tickets.add(resultSetParaTicket(rs));
        }
        return tickets;
    }

    public ArrayList<Ticket> getTicketsByPrioridad(String prioridad) throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta con filtro por prioridad
        String query = "SELECT * FROM tickets WHERE prioridad='" + prioridad + "'";
        //ejecutar consulta
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        while (rs.next()) {
            tickets.add(resultSetParaTicket(rs));
        }
        return tickets;
    }

    public Ticket findTicketById(int id) throws ClassNotFoundException, SQLException {
        Class.forName(driver);  //driver
        //construir consulta con filtro por ID
        String query = "SELECT id, asunto, descripcion, estado, prioridad, usuarioId, departamentoId, fechaCreacion, fechaActualizacion FROM tickets WHERE id=" + id;
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        if (rs.next()) {
            return resultSetParaTicket(rs);
        }
        return null;
    }
}
