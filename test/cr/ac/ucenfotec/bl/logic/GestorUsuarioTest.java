package cr.ac.ucenfotec.bl.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para GestorUsuario
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class GestorUsuarioTest {

    private GestorUsuario gestorUsuario;

    @BeforeEach
    public void setUp() {
        gestorUsuario = new GestorUsuario();
    }

    @Test
    public void testAddUsuario() {
        String resultado = gestorUsuario.addUsuario("TEST-001", "Test User", "test@test.com", "password", "1234-5678", "usuario");
        
        assertTrue(resultado.contains("TEST-001"), "El resultado debe contener el ID del usuario");
        assertTrue(resultado.contains("Test User"), "El resultado debe contener el nombre del usuario");
    }

    @Test
    public void testLoginUsuarioValido() {
        String resultado = gestorUsuario.loginUsuario("admin@helpdesk.com", "admin123");
        
        assertTrue(resultado.contains("Admin"), "El login debe ser exitoso para credenciales válidas");
    }

    @Test
    public void testLoginUsuarioInvalido() {
        String resultado = gestorUsuario.loginUsuario("noexiste@test.com", "password");
        
        assertEquals("Usuario no encontrado", resultado, "Debe retornar mensaje de usuario no encontrado");
    }
}