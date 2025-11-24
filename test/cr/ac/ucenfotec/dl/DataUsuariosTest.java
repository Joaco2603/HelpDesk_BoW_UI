package test.cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para DataUsuarios
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataUsuariosTest {

    private DataUsuarios dataUsuarios;

    @BeforeEach
    public void setUp() {
        dataUsuarios = new DataUsuarios();
    }

    @Test
    public void testAddUsuario() {
        Usuario usuario = new Usuario("TEST-001", "Test User", "test@test.com", "password", "1234-5678", "usuario");
        dataUsuarios.addUsuario(usuario);
        
        assertTrue(dataUsuarios.getUsuarios().contains(usuario), "El usuario debe estar en la lista");
    }

    @Test
    public void testFindUsuarioByCorreo() {
        Usuario usuario = dataUsuarios.findUsuarioByCorreo("admin@helpdesk.com");
        
        assertNotNull(usuario, "Debe encontrar el usuario admin");
        assertEquals("Admin", usuario.getNombre(), "El nombre debe ser 'Admin'");
    }

    @Test
    public void testFindUsuarioByCorreoNotFound() {
        Usuario usuario = dataUsuarios.findUsuarioByCorreo("noexiste@test.com");
        
        assertNull(usuario, "Debe retornar null para correo inexistente");
    }
}