package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.DataUsuarios;

public class GestorUsuario {
    private DataUsuarios dataUsuarios;

    public GestorUsuario() {
        dataUsuarios = new DataUsuarios();
    }

    public String addUsuario(String cedula, String nombre, String correo, String password, String telefono, String rol) {
        Usuario usuario = new Usuario(cedula, nombre, correo, password, telefono, rol);
        dataUsuarios.addUsuario(usuario);
        return usuario.toString();
    }

    public String loginUsuario(String correo, String password) {
        Usuario usuarioEncontrado = dataUsuarios.findUsuarioByCorreo(correo);
        if (usuarioEncontrado != null) {
            Usuario usuarioValidar = new Usuario();
            usuarioValidar.setId(usuarioEncontrado.getId());
            usuarioValidar.setPassword(password);
            
            // Validar que el usuario coincida (por id) y el password sea correcto
            if (usuarioEncontrado.equals(usuarioValidar) && usuarioEncontrado.getPassword().equals(password)) {
                return usuarioEncontrado.toString();
            }
        }
        return "Usuario no encontrado";
    }

    public String findUsuarioById(String id) {
        Usuario buscado = new Usuario();
        buscado.setId(id);
        for (Usuario usuario : dataUsuarios.getUsuarios()) {
            if (usuario.equals(buscado)) {
                return usuario.toString();
            }
        }
        return "Usuario no encontrado";
    }

    public String findUsuarioByCorreo(String correo) {
        for (Usuario usuario : dataUsuarios.getUsuarios()) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario.toString();
            }
        }
        return "Usuario no encontrado";
    }

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

    public String updateUsuario(Usuario usuario) {
        for (int i = 0; i < dataUsuarios.getUsuarios().size(); i++) {
            if (dataUsuarios.getUsuarios().get(i).equals(usuario)) {
                dataUsuarios.getUsuarios().set(i, usuario);
                return "Usuario actualizado exitosamente";
            }
        }
        return "Error: Usuario no encontrado";
    }

    public String deleteUsuario(String id) {
        Usuario buscado = new Usuario();
        buscado.setId(id);
        for (int i = 0; i < dataUsuarios.getUsuarios().size(); i++) {
            if (dataUsuarios.getUsuarios().get(i).equals(buscado)) {
                dataUsuarios.getUsuarios().remove(i);
                return "Usuario eliminado exitosamente";
            }
        }
        return "Error: Usuario no encontrado";
    }
}
