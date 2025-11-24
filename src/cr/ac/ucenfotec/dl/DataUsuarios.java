package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Usuario;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Capa de datos para la gestión de usuarios
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataUsuarios {
    private ArrayList<Usuario> usuarios;

    /**
     * Constructor por defecto que inicializa con datos de prueba
     */
    public DataUsuarios() {
        usuarios = new ArrayList<>();
        
        // Datos de prueba
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Admin", "admin@helpdesk.com", "admin123", "8888-8888", "admin"));
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Juan Perez", "juan@example.com", "1234", "8765-4321", "usuario"));
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Maria Lopez", "maria@example.com", "1234", "8765-1234", "soporte"));
    }

    /**
     * Agrega un usuario a la lista
     * @param usuario Usuario a agregar
     */
    public void addUsuario(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
    }

    /**
     * Obtiene todos los usuarios
     * @return Lista de usuarios
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Busca un usuario por correo electrónico
     * @param correo Correo a buscar
     * @return Usuario encontrado o null
     */
    public Usuario findUsuarioByCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }
}