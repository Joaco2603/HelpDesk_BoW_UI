package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Diccionario de palabras para análisis emocional
 * SIEMPRE carga los datos desde la base de datos y los mantiene en memoria para procesamiento rápido
 * Simula el comportamiento de un modelo de Machine Learning:
 * - Los datos (diccionarios) se cargan desde la BD (como entrenar un modelo)
 * - El procesamiento se hace en memoria (como hacer predicciones con un modelo entrenado)
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class DataDiccionarioEmocional {
    // Listas en memoria para procesamiento rápido (como un modelo ML entrenado)
    private ArrayList<Tockenizer> frustracion;
    private ArrayList<Tockenizer> urgencia;
    private ArrayList<Tockenizer> neutralidad;
    private ArrayList<Tockenizer> positivo;

    private DiccionarioEmocionalDAO dao;

    /**
     * Constructor que carga los diccionarios desde la base de datos
     * Los datos se cargan UNA VEZ y se mantienen en memoria
     * @param driver Driver JDBC (ej: "com.mysql.cj.jdbc.Driver")
     * @param url URL de conexión (ej: "jdbc:mysql://localhost:3306/mydatabase")
     * @param user Usuario de BD
     * @param pass Contraseña de BD
     * @throws SQLException Si hay error de conexión o consulta
     * @throws ClassNotFoundException Si no encuentra el driver JDBC
     */
    public DataDiccionarioEmocional(String driver, String url, String user, String pass) 
            throws SQLException, ClassNotFoundException {
        this.dao = new DiccionarioEmocionalDAO(driver, url, user, pass);
        
        // Inicializar listas vacías
        frustracion = new ArrayList<>();
        urgencia = new ArrayList<>();
        neutralidad = new ArrayList<>();
        positivo = new ArrayList<>();

        // Cargar TODO desde la base de datos
        cargarDesdeBaseDatos();
    }

    /**
     * Carga todos los diccionarios desde la base de datos
     * Este método se ejecuta UNA VEZ al crear el objeto
     * Los datos quedan en memoria para procesamiento rápido (simula ML)
     */
    private void cargarDesdeBaseDatos() throws SQLException, ClassNotFoundException {
        // Cargar cada categoría desde la base de datos
        frustracion = dao.obtenerPorCategoria("Frustración");
        urgencia = dao.obtenerPorCategoria("Urgencia");
        neutralidad = dao.obtenerPorCategoria("Neutralidad");
        positivo = dao.obtenerPorCategoria("Positivo");
        
        System.out.println("✓ Diccionario Emocional cargado desde BD:");
        System.out.println("  - Frustración: " + frustracion.size() + " palabras");
        System.out.println("  - Urgencia: " + urgencia.size() + " palabras");
        System.out.println("  - Neutralidad: " + neutralidad.size() + " palabras");
        System.out.println("  - Positivo: " + positivo.size() + " palabras");
    }

    /**
     * Recarga los datos desde la base de datos
     * Útil para actualizar el diccionario sin reiniciar la aplicación
     * (Por ejemplo, si se agregaron nuevas palabras a la BD)
     */
    public void recargar() throws SQLException, ClassNotFoundException {
        cargarDesdeBaseDatos();
        System.out.println("✓ Diccionario Emocional recargado desde BD");
    }

    // Getters - Retornan las listas en memoria (procesamiento rápido)
    public ArrayList<Tockenizer> getFrustracion() { return frustracion; }
    public ArrayList<Tockenizer> getUrgencia() { return urgencia; }
    public ArrayList<Tockenizer> getNeutralidad() { return neutralidad; }
    public ArrayList<Tockenizer> getPositivo() { return positivo; }
}