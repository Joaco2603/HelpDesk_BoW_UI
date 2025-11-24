package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import java.util.ArrayList;

/**
 * Diccionario de palabras para análisis emocional
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataDiccionarioEmocional {
    private ArrayList<Tockenizer> frustracion;
    private ArrayList<Tockenizer> urgencia;
    private ArrayList<Tockenizer> neutralidad;
    private ArrayList<Tockenizer> positivo;

    /**
     * Constructor por defecto que carga el diccionario
     */
    public DataDiccionarioEmocional() {
        frustracion = new ArrayList<>();
        urgencia = new ArrayList<>();
        neutralidad = new ArrayList<>();
        positivo = new ArrayList<>();

        cargarFrustracion();
        cargarUrgencia();
        cargarNeutralidad();
        cargarPositivo();
    }

    private void cargarFrustracion() {
        frustracion.add(new Tockenizer("enojado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("molesto", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("estresado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("preocupado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("irritado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("frustrado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("agobiado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("desanimado", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("insatisfecho", "Emocional", "Frustración"));
        frustracion.add(new Tockenizer("molestia", "Emocional", "Frustración"));
    }

    private void cargarUrgencia() {
        urgencia.add(new Tockenizer("urgente", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("ahora", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("rápido", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("inmediato", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("prioridad", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("apresurado", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("pronto", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("imperativo", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("crítico", "Emocional", "Urgencia"));
        urgencia.add(new Tockenizer("inminente", "Emocional", "Urgencia"));
    }

    private void cargarNeutralidad() {
        neutralidad.add(new Tockenizer("neutral", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("tranquilo", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("moderado", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("equilibrado", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("estable", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("normal", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("indiferente", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("regular", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("medio", "Emocional", "Neutralidad"));
        neutralidad.add(new Tockenizer("calmado", "Emocional", "Neutralidad"));
    }

    private void cargarPositivo() {
        positivo.add(new Tockenizer("feliz", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("contento", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("motivado", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("entusiasmado", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("satisfecho", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("alegre", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("animado", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("optimista", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("agradable", "Emocional", "Positivo"));
        positivo.add(new Tockenizer("positivo", "Emocional", "Positivo"));
    }

    // Getters
    public ArrayList<Tockenizer> getFrustracion() { return frustracion; }
    public ArrayList<Tockenizer> getUrgencia() { return urgencia; }
    public ArrayList<Tockenizer> getNeutralidad() { return neutralidad; }
    public ArrayList<Tockenizer> getPositivo() { return positivo; }
}