package cr.ac.ucenfotec.bl.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para la clase Usuario
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class UsuarioTest {

    @Test
    public void testUsuarioEquals() {
        Usuario u1 = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        Usuario u2 = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        
        assertTrue(u1.equals(u2), "Dos usuarios con mismo ID deben ser iguales");
    }

    @Test
    public void testUsuarioNotEquals() {
        Usuario u1 = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        Usuario u2 = new Usuario("2", "Maria Lopez", "maria@test.com", "pass456", "7777-7777", "soporte");
        
        assertFalse(u1.equals(u2), "Usuarios con diferentes IDs no deben ser iguales");
    }

    @Test
    public void testUsuarioHashCode() {
        Usuario u1 = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        Usuario u2 = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        
        assertEquals(u1.hashCode(), u2.hashCode(), "HashCodes deben ser iguales para objetos iguales");
    }

    @Test
    public void testUsuarioEsPersona() {
        Usuario usuario = new Usuario("1", "Juan Perez", "juan@test.com", "pass123", "8888-8888", "usuario");
        
        assertTrue(usuario instanceof Persona, "Usuario debe ser instancia de Persona");
        assertEquals("usuario", usuario.getRol(), "El rol debe ser 'usuario'");
    }
}