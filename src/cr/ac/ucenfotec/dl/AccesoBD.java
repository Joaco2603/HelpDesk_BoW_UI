package cr.ac.ucenfotec.dl;

import java.sql.*;

//clase q se encarga de realizar las operaciones con la base de datos y establecer la conexión con el servidor

public class AccesoBD {
    private Connection conn = null;
    private Statement stmt = null;

    public AccesoBD(String driver, String url, String user, String pass) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (Exception e) {
            throw e;
        }
    }

    public void ejecutarQuery(String query) throws SQLException {
        stmt.execute(query);
    }

    public ResultSet ejecutarSQL(String query) throws SQLException {
        ResultSet rs;
        rs = stmt.executeQuery(query);
        return rs;
    }
}

