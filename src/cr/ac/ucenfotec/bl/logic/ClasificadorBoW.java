package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import cr.ac.ucenfotec.dl.DataDiccionarioEmocional;
import cr.ac.ucenfotec.dl.DataDiccionarioTecnico;

import java.util.HashMap;
import java.util.Map;

/**
 * Clasificador Bag of Words para análisis de texto en tickets
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class ClasificadorBoW {
    private DataDiccionarioEmocional diccionarioEmocional;
    private DataDiccionarioTecnico diccionarioTecnico;

    /**
     * Constructor por defecto
     */
    public ClasificadorBoW() {
        this.diccionarioEmocional = new DataDiccionarioEmocional();
        this.diccionarioTecnico = new DataDiccionarioTecnico();
    }

    /**
     * Clasifica el estado emocional del texto
     * @param texto Texto a analizar
     * @return Categoría emocional detectada
     */
    public String clasificarEmocional(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "Neutralidad";
        }

        String textoLower = texto.toLowerCase();
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Frustración", 0);
        scores.put("Urgencia", 0);
        scores.put("Neutralidad", 0);
        scores.put("Positivo", 0);

        // Analizar frustracion
        for (Tockenizer token : diccionarioEmocional.getFrustracion()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Frustración", scores.get("Frustración") + 1);
            }
        }

        // Analizar urgencia
        for (Tockenizer token : diccionarioEmocional.getUrgencia()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Urgencia", scores.get("Urgencia") + 1);
            }
        }

        // Analizar neutralidad
        for (Tockenizer token : diccionarioEmocional.getNeutralidad()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Neutralidad", scores.get("Neutralidad") + 1);
            }
        }

        // Analizar positivo
        for (Tockenizer token : diccionarioEmocional.getPositivo()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Positivo", scores.get("Positivo") + 1);
            }
        }

        // Retornar categoría con mayor score
        String categoriaMaxima = "Neutralidad";
        int scoreMaximo = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > scoreMaximo) {
                scoreMaximo = entry.getValue();
                categoriaMaxima = entry.getKey();
            }
        }

        return categoriaMaxima;
    }

    /**
     * Clasifica la categoría técnica del texto
     * @param texto Texto a analizar
     * @return Categoría técnica detectada
     */
    public String clasificarTecnico(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "General";
        }

        String textoLower = texto.toLowerCase();
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Redes", 0);
        scores.put("Impresoras", 0);
        scores.put("Cuentas", 0);
        scores.put("Hardware", 0);

        // Analizar redes
        for (Tockenizer token : diccionarioTecnico.getRedes()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Redes", scores.get("Redes") + 1);
            }
        }

        // Analizar impresoras
        for (Tockenizer token : diccionarioTecnico.getImpresoras()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Impresoras", scores.get("Impresoras") + 1);
            }
        }

        // Analizar cuentas
        for (Tockenizer token : diccionarioTecnico.getCuentas()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Cuentas", scores.get("Cuentas") + 1);
            }
        }

        // Analizar hardware
        for (Tockenizer token : diccionarioTecnico.getHardware()) {
            if (textoLower.contains(token.getPalabra().toLowerCase())) {
                scores.put("Hardware", scores.get("Hardware") + 1);
            }
        }

        // Retornar categoría con mayor score
        String categoriaMaxima = "General";
        int scoreMaximo = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > scoreMaximo) {
                scoreMaximo = entry.getValue();
                categoriaMaxima = entry.getKey();
            }
        }

        return categoriaMaxima;
    }

    /**
     * Sugiere la prioridad basándose en el análisis emocional
     * @param texto Texto a analizar
     * @return Prioridad sugerida
     */
    public String sugerirPrioridad(String texto) {
        String categoriaEmocional = clasificarEmocional(texto);
        
        switch (categoriaEmocional) {
            case "Urgencia":
            case "Frustración":
                return "Alta";
            case "Positivo":
                return "Baja";
            case "Neutralidad":
            default:
                return "Media";
        }
    }

    /**
     * Sugiere el departamento basándose en la categoría técnica
     * @param texto Texto a analizar
     * @return ID del departamento sugerido
     */
    public int sugerirDepartamento(String texto) {
        String categoriaTecnica = clasificarTecnico(texto);
        
        switch (categoriaTecnica) {
            case "Redes":
                return 3; // Sistemas
            case "Impresoras":
            case "Hardware":
                return 1; // Soporte Técnico
            case "Cuentas":
                return 2; // Recursos Humanos
            default:
                return 1; // Soporte Técnico por defecto
        }
    }

    /**
     * Análisis completo del ticket
     * @param asunto Asunto del ticket
     * @param descripcion Descripción del ticket
     * @return Objeto con el análisis completo
     */
    public AnalisisTicket analizarTicket(String asunto, String descripcion) {
        String textoCompleto = asunto + " " + descripcion;
        
        AnalisisTicket analisis = new AnalisisTicket();
        analisis.categoriaEmocional = clasificarEmocional(textoCompleto);
        analisis.categoriaTecnica = clasificarTecnico(textoCompleto);
        analisis.prioridadSugerida = sugerirPrioridad(textoCompleto);
        analisis.departamentoSugerido = sugerirDepartamento(textoCompleto);
        
        return analisis;
    }

    /**
     * Clase interna para encapsular el resultado del análisis
     */
    public static class AnalisisTicket {
        public String categoriaEmocional;
        public String categoriaTecnica;
        public String prioridadSugerida;
        public int departamentoSugerido;

        @Override
        public String toString() {
            return "AnalisisTicket{" +
                    "categoriaEmocional='" + categoriaEmocional + '\'' +
                    ", categoriaTecnica='" + categoriaTecnica + '\'' +
                    ", prioridadSugerida='" + prioridadSugerida + '\'' +
                    ", departamentoSugerido=" + departamentoSugerido +
                    '}';
        }
    }
}