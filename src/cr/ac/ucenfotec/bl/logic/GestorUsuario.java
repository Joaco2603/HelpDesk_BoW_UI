package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.DataUsuarios;

/**
 * Gestor que maneja la lógica de negocio para los Usuarios
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorUsuario {
    private DataUsuarios dataUsuarios;

    /**
     * Constructor por defecto
     */
    public GestorUsuario() {
        dataUsuarios = new DataUsuarios();
    }

    /**
     * Agrega un nuevo usuario al sistema
     * @param cedula Cédula del usuario
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param password Contraseña
     * @param telefono Teléfono
     * @param rol Rol del usuario
     * @return String con la información del usuario creado
     */
    public String addUsuario(String cedula, String nombre, String correo, String password, String telefono, String rol) {
        Usuario usuario = new Usuario(cedula, nombre, correo, password, telefono, rol);
        dataUsuarios.addUsuario(usuario);
        return usuario.toString();
    }

    /**
     * Autentica un usuario en el sistema
     * @param correo Correo electrónico
     * @param password Contraseña
     * @return String con la información del usuario si es válido, null si no
     */
    public String loginUsuario(String correo, String password) {
        Usuario usuarioEncontrado = dataUsuarios.findUsuarioByCorreo(correo);
        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(password)) {
            return usuarioEncontrado.toString();
        }
        return "Usuario no encontrado";
    }

    /**
     * Busca un usuario por su ID
     * @param id ID del usuario
     * @return String con la información del usuario
     */
    public String findUsuarioById(String id) {
        for (Usuario usuario : dataUsuarios.getUsuarios()) {
            if (usuario.getId().equals(id)) {
                return usuario.toString();
            }
        }
        return "Usuario no encontrado";
    }

    /**
     * Busca un usuario por su correo electrónico
     * @param correo Correo del usuario
     * @return String con la información del usuario
     */
    public String findUsuarioByCorreo(String correo) {
        Usuario usuario = dataUsuarios.findUsuarioByCorreo(correo);
        return usuario != null ? usuario.toString() : "Usuario no encontrado";
    }

    /**
     * Obtiene todos los usuarios del sistema
     * @return String con la lista de usuarios
     */
    public String getAllUsuarios() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Usuario usuario : dataUsuarios.getUsuarios()) {
            if (count > 0) {
                result.append("\n");
            }
            result.append(usuario.toString());
            count++;
        }
        return count > 0 ? result.toString() : "No hay usuarios registrados";
    }

    /**
     * Actualiza la información de un usuario
     * @param usuario Usuario con la información actualizada
     * @return Mensaje de confirmación
     */
    public String updateUsuario(Usuario usuario) {
        for (int i = 0; i < dataUsuarios.getUsuarios().size(); i++) {
            if (dataUsuarios.getUsuarios().get(i).equals(usuario)) {
                dataUsuarios.getUsuarios().set(i, usuario);
                return "Usuario actualizado exitosamente";
            }
        }
        return "Error: Usuario no encontrado";
    }

    /**
     * Elimina un usuario del sistema
     * @param id ID del usuario a eliminar
     * @return Mensaje de confirmación
     */
    public String deleteUsuario(String id) {
        for (int i = 0; i < dataUsuarios.getUsuarios().size(); i++) {
            if (dataUsuarios.getUsuarios().get(i).getId().equals(id)) {
                dataUsuarios.getUsuarios().remove(i);
                return "Usuario eliminado exitosamente";
            }
        }
        return "Error: Usuario no encontrado";
    }
}