package cr.ac.ucenfotec.bl.entities;

public class Tockenizer {
    private String palabra;
    private String clasificacion; // emocional / técnico
    private String categoria;     // frustración, urgencia, neutralidad, positivo / redes, impresoras, cuentas, hardware

    public Tockenizer() {}

    public Tockenizer(String palabra, String clasificacion, String categoria) {
        this.palabra = palabra;
        this.clasificacion = clasificacion;
        this.categoria = categoria;
    }

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
    public String toString() {
        return "Tockenizer{" +
                "palabra='" + palabra + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}