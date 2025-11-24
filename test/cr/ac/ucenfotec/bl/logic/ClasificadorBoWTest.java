package cr.ac.ucenfotec.bl.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para ClasificadorBoW
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class ClasificadorBoWTest {

    private ClasificadorBoW clasificador;

    @BeforeEach
    public void setUp() {
        clasificador = new ClasificadorBoW();
    }

    @Test
    public void testClasificarEmocionalUrgencia() {
        String texto = "Es urgente que resuelvan este problema ahora mismo";
        String resultado = clasificador.clasificarEmocional(texto);
        
        assertEquals("Urgencia", resultado, "Debe detectar urgencia en el texto");
    }

    @Test
    public void testClasificarTecnicoRedes() {
        String texto = "No puedo conectarme al wifi del router";
        String resultado = clasificador.clasificarTecnico(texto);
        
        assertEquals("Redes", resultado, "Debe detectar problema de redes");
    }

    @Test
    public void testSugerirPrioridadAlta() {
        String texto = "Estoy muy frustrado con este error crítico";
        String resultado = clasificador.sugerirPrioridad(texto);
        
        assertEquals("Alta", resultado, "Debe sugerir prioridad alta para texto con frustración");
    }
}