package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO para acceder a los datos del diccionario técnico
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DiccionarioTecnicoDAO {
    private String driver;
    private String url;
    private String user;
    private String pass;

    /**
     * Constructor con parámetros de conexión
     */
    public DiccionarioTecnicoDAO(String driver, String url, String user, String pass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    /**
     * Obtiene todas las palabras de una categoría específica
     * @param categoria Categoría del diccionario técnico (Redes, Impresoras, Cuentas, Hardware)
     * @return Lista de tokens de la categoría
     */
    public ArrayList<Tockenizer> obtenerPorCategoria(String categoria) throws SQLException, ClassNotFoundException {
        ArrayList<Tockenizer> palabras = new ArrayList<>();
        String query = "SELECT palabra, tipo, categoria FROM diccionario_tecnico WHERE categoria = '" + categoria + "'";

        try {
            ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
            while (rs.next()) {
                String palabra = rs.getString("palabra");
                String tipo = rs.getString("tipo");
                String cat = rs.getString("categoria");
                palabras.add(new Tockenizer(palabra, tipo, cat));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

        return palabras;
    }

    /**
     * Obtiene todas las palabras del diccionario técnico
     * @return Lista completa de tokens
     */
    public ArrayList<Tockenizer> obtenerTodos() throws SQLException, ClassNotFoundException {
        ArrayList<Tockenizer> palabras = new ArrayList<>();
        String query = "SELECT palabra, tipo, categoria FROM diccionario_tecnico";

        try {
            ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
            while (rs.next()) {
                String palabra = rs.getString("palabra");
                String tipo = rs.getString("tipo");
                String categoria = rs.getString("categoria");
                palabras.add(new Tockenizer(palabra, tipo, categoria));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

        return palabras;
    }

    /**
     * Agrega una nueva palabra al diccionario
     * @param palabra Palabra a agregar
     * @param tipo Tipo de palabra
     * @param categoria Categoría técnica
     */
    public void agregar(String palabra, String tipo, String categoria) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO diccionario_tecnico (palabra, tipo, categoria) VALUES ('" +
                palabra + "', '" + tipo + "', '" + categoria + "')";
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    /**
     * Elimina una palabra del diccionario
     * @param palabra Palabra a eliminar
     * @param categoria Categoría de la palabra
     */
    public void eliminar(String palabra, String categoria) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM diccionario_tecnico WHERE palabra = '" + palabra +
                "' AND categoria = '" + categoria + "'";
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }
}
