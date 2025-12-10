package cr.ac.ucenfotec.dl;

import java.sql.SQLException;

//clase q actúa como punto de acceso central a la base de datos

public class Conector {
    private static AccesoBD dataAccess;

    public static AccesoBD getDataAccess(String driver, String url, String user, String pass) throws SQLException, ClassNotFoundException {
        if (dataAccess == null) {
            dataAccess = new AccesoBD(driver, url, user, pass);
        }
        return dataAccess;
    }
}


