package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.UsuariosDAO;
import java.util.ArrayList;

/**
 * Gestor que maneja la lógica de negocio para los Usuarios.
 * Se comunica con el DAO para realizar operaciones de persistencia
 * y devuelve mensajes o resultados listos para la capa UI.
 *
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorUsuarioMySQL {
    private UsuariosDAO usuariosDAO;

    /**
     * Constructor por defecto.
     * Inicializa el DAO que se encargará de la persistencia en MySQL.
     */
    public GestorUsuarioMySQL() {
        usuariosDAO = new UsuariosDAO();
    }

    /**
     * Agrega un nuevo usuario al sistema
     * @param id ID del usuario
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Teléfono
     * @param rol Rol del usuario
     * @return String con la información del usuario creado
     */
    //se ajusta cedula a id porq es el atributo declarado en el entity
    public String addUsuario(String id, String nombre, String correo, String password, String telefono, String rol) {
        try {
            Usuario usuario = new Usuario(id, nombre, correo, password, telefono, rol);
            usuariosDAO.addUsuario(usuario);
            return "Usuario agregado exitosamente: " + usuario.toString();
        } catch (Exception e) {
            return "Error ocurrido al agregar usuario: " + e;
        }
    }

    public String findUsuarioById(String id) {
        try {
            Usuario usuario = usuariosDAO.findUsuarioById(id);
            return (usuario != null) ? usuario.toString() : "Usuario no encontrado";
        } catch (Exception e) {
            return "Error ocurrido al buscar usuario por ID: " + e;
        }
    }

    public String loginUsuario(String correo, String password) {
        try {
            Usuario usuarioEncontrado = usuariosDAO.findUsuarioByCorreo(correo);
            if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(password)) {
                return usuarioEncontrado.toString();
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error ocurrido al autenticar usuario: " + e;
        }
    }

    public String getAllUsuarios() {
        try {
            ArrayList<Usuario> lista = usuariosDAO.getUsuarios();
            if (lista.isEmpty()) return "No hay usuarios registrados";
            StringBuilder result = new StringBuilder();
            for (Usuario usuario : lista) {
                result.append(usuario.toString()).append("\n");
            }
            return result.toString().trim();
        } catch (Exception e) {
            return "Error ocurrido al listar usuarios: " + e;
        }
    }

    public String updateUsuario(Usuario usuario) {
        try {
            Usuario existente = usuariosDAO.findUsuarioById(usuario.getId());
            if (existente == null) return "Error: Usuario no encontrado";
            usuariosDAO.updateUsuario(usuario);
            return "Usuario actualizado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al actualizar usuario: " + e;
        }
    }

    public String deleteUsuario(String id) {
        try {
            Usuario existente = usuariosDAO.findUsuarioById(id);
            if (existente == null) return "Error: Usuario no encontrado";
            usuariosDAO.deleteUsuario(id);
            return "Usuario eliminado exitosamente";
        } catch (Exception e) {
            return "Error ocurrido al eliminar usuario: " + e;
        }
    }
}