package cr.ac.ucenfotec.bl.entities;

/**
 * Clase que representa una palabra tokenizada para el análisis Bag of Words
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class Tockenizer {
    private String palabra;
    private String clasificacion; // emocional / técnico
    private String categoria;     // frustración, urgencia, neutralidad, positivo / redes, impresoras, cuentas, hardware

    /**
     * Constructor por defecto
     */
    public Tockenizer() {}

    /**
     * Constructor completo de Tockenizer
     * @param palabra Palabra a tokenizar
     * @param clasificacion Clasificación (Emocional/Técnico)
     * @param categoria Categoría específica
     */
    public Tockenizer(String palabra, String clasificacion, String categoria) {
        this.palabra = palabra;
        this.clasificacion = clasificacion;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tockenizer that = (Tockenizer) obj;
        return palabra != null ? palabra.equals(that.palabra) : that.palabra == null;
    }

    @Override
    public int hashCode() {
        return palabra != null ? palabra.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tockenizer{" +
                "palabra='" + palabra + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}