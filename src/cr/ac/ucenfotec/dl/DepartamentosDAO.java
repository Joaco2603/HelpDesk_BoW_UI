package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Departamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//SIN TRY CATCH PARA MANEJARLO EN EL GESTOR Y SEGUIR ARQUITECTURA

/**
 * DAO / capa de datos para la gestión de departamentos en MySQL.
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DepartamentosDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test_schema";
    private String user = "root";
    private String pass = "C0s1C0s1MySQL$$";

    /**
     * Inserta un departamento en la base de datos.
     *
     * @param departamento Departamento a insertar
     */
    public void addDepartamento(Departamento departamento) throws ClassNotFoundException, SQLException {
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "INSERT INTO departamentos (id, nombre, descripcion, contacto) VALUES (" +
                departamento.getId() + ", '" +
                departamento.getNombre() + "', '" +
                departamento.getDescripcion() + "', '" +
                departamento.getContacto() + "')";
        //cjecutar consulta (usando el conector)
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    /**
     * Obtiene todos los departamentos desde la base de datos.
     *
     * @return Lista de departamentos
     */
    public ArrayList<Departamento> getDepartamentos() throws ClassNotFoundException, SQLException {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "SELECT id, nombre, descripcion, contacto FROM departamentos";
        //ejecutar consulta y capturar rs
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        //procesar rs con setters
        while (rs.next()) {
            Departamento tmpDepartamento = new Departamento();
            tmpDepartamento.setId(rs.getInt("id"));
            tmpDepartamento.setNombre(rs.getString("nombre"));
            tmpDepartamento.setDescripcion(rs.getString("descripcion"));
            tmpDepartamento.setContacto(rs.getString("contacto"));
            departamentos.add(tmpDepartamento);
        }
        return departamentos;
    }

    public Departamento findDepartamentoById(int id) throws ClassNotFoundException, SQLException {
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "SELECT id, nombre, descripcion, contacto FROM departamentos WHERE id=" + id;
        //ejecutar consulta
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        //procesar
        if (rs.next()) {
            Departamento departamento = new Departamento();
            departamento.setId(rs.getInt("id"));              // si querés consistencia con Usuario, podés usar getString y luego parsear
            departamento.setNombre(rs.getString("nombre"));
            departamento.setDescripcion(rs.getString("descripcion"));
            departamento.setContacto(rs.getString("contacto"));
            return departamento;
        }
        return null;
    }

    public void updateDepartamento(Departamento departamento) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "UPDATE departamentos SET nombre='" + departamento.getNombre() +
                "', descripcion='" + departamento.getDescripcion() +
                "', contacto='" + departamento.getContacto() +
                "' WHERE id=" + departamento.getId();
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    public void deleteDepartamento(int id) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "DELETE FROM departamentos WHERE id=" + id;
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    //en ves de nombre se usa solo id
    /*
    public Departamento findDepartamentoByNombre(String nombre) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "SELECT id, nombre, descripcion, contacto FROM departamentos WHERE nombre = '" + nombre + "'";
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        if (rs.next()) {
            return new Departamento(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("contacto")
            );
        }
        return null;
    }
     */
}


