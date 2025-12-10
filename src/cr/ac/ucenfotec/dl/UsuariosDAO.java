package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//SIN TRY CATCH PARA MANEJARLO EN EL GESTOR Y SEGUIR ARQUITECTURA

/**
 * DAO / capa de datos parala gestión de usuarios en MySQL.
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class UsuariosDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test_schema";
    private String user = "root";
    private String pass = "C0s1C0s1MySQL$$";

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param usuario Usuario a insertar
     */
    public void addUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "INSERT INTO usuarios (id, nombre, correo, telefono, password, rol) VALUES ('" +
                usuario.getId() + "', '" +
                usuario.getNombre() + "', '" +
                usuario.getCorreo() + "', '" +
                usuario.getTelefono() + "', '" +
                usuario.getPassword() + "', '" +
                usuario.getRol() + "')";
        //ejecutar consulta
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    /**
     * Obtiene todos los usuarios desde la base de datos.
     *
     * @return Lista de usuarios
     */
    public ArrayList<Usuario> getUsuarios() throws ClassNotFoundException, SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //cargar driver
        Class.forName(driver);
        //construir consulta
        String query = "SELECT id, nombre, correo, telefono, password, rol FROM usuarios";
        //ejecutar consulta y capturar resultado
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        //procesar resultados
        while (rs.next()) {
            Usuario tmpUsuario = new Usuario();
            tmpUsuario.setId(rs.getString("id"));
            tmpUsuario.setNombre(rs.getString("nombre"));
            tmpUsuario.setCorreo(rs.getString("correo"));
            tmpUsuario.setTelefono(rs.getString("telefono"));
            tmpUsuario.setPassword(rs.getString("password"));
            tmpUsuario.setRol(rs.getString("rol"));
            usuarios.add(tmpUsuario);
        }
        return usuarios;
    }

    public Usuario findUsuarioById(String id) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "SELECT id, nombre, correo, telefono, password, rol FROM usuarios WHERE id='" + id + "'";
        ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getString("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setPassword(rs.getString("password"));
            usuario.setRol(rs.getString("rol"));
            return usuario;
        }
        return null;
    }

    public void updateUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "UPDATE usuarios SET nombre='" + usuario.getNombre() +
                "', correo='" + usuario.getCorreo() +
                "', telefono='" + usuario.getTelefono() +
                "', password='" + usuario.getPassword() +
                "', rol='" + usuario.getRol() +
                "' WHERE id='" + usuario.getId() + "'";
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    public void deleteUsuario(String id) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        String query = "DELETE FROM usuarios WHERE id='" + id + "'";
        Conector.getDataAccess(driver, url, user, pass).ejecutarQuery(query);
    }

    //se cambió param correo para hacer el login por id entonces ya no es necesario
/*
    public Usuario findUsuarioByCorreo(String correo) {
        Usuario usuarioEncontrado = null;
        try {
            Class.forName(driver);
            String query = "SELECT id, nombre, correo, telefono, password, rol FROM usuarios WHERE correo = '" + correo + "'";
            ResultSet rs = Conector.getDataAccess(driver, url, user, pass).ejecutarSQL(query);
            if (rs.next()) {
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getString("id"));
                usuarioEncontrado.setNombre(rs.getString("nombre"));
                usuarioEncontrado.setCorreo(rs.getString("correo"));
                usuarioEncontrado.setTelefono(rs.getString("telefono"));
                usuarioEncontrado.setPassword(rs.getString("password"));
                usuarioEncontrado.setRol(rs.getString("rol"));
            }
        } catch (Exception e) {
            System.out.println("Error ocurrido al buscar usuario por correo: " + e.getMessage());
        }
        return usuarioEncontrado;
    }
 */
}
