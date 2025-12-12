package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Diccionario de palabras para análisis técnico
 * SIEMPRE carga los datos desde la base de datos y los mantiene en memoria para procesamiento rápido
 * Simula el comportamiento de un modelo de Machine Learning:
 * - Los datos (diccionarios) se cargan desde la BD (como entrenar un modelo)
 * - El procesamiento se hace en memoria (como hacer predicciones con un modelo entrenado)
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class DataDiccionarioTecnico {
    // Listas en memoria para procesamiento rápido (como un modelo ML entrenado)
    private ArrayList<Tockenizer> redes;
    private ArrayList<Tockenizer> impresoras;
    private ArrayList<Tockenizer> cuentas;
    private ArrayList<Tockenizer> hardware;

    private DiccionarioTecnicoDAO dao;

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
    public DataDiccionarioTecnico(String driver, String url, String user, String pass) 
            throws SQLException, ClassNotFoundException {
        this.dao = new DiccionarioTecnicoDAO(driver, url, user, pass);
        
        // Inicializar listas vacías
        redes = new ArrayList<>();
        impresoras = new ArrayList<>();
        cuentas = new ArrayList<>();
        hardware = new ArrayList<>();

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
        redes = dao.obtenerPorCategoria("Redes");
        impresoras = dao.obtenerPorCategoria("Impresoras");
        cuentas = dao.obtenerPorCategoria("Cuentas");
        hardware = dao.obtenerPorCategoria("Hardware");
        
        System.out.println("✓ Diccionario Técnico cargado desde BD:");
        System.out.println("  - Redes: " + redes.size() + " palabras");
        System.out.println("  - Impresoras: " + impresoras.size() + " palabras");
        System.out.println("  - Cuentas: " + cuentas.size() + " palabras");
        System.out.println("  - Hardware: " + hardware.size() + " palabras");
    }

    /**
     * Recarga los datos desde la base de datos
     * Útil para actualizar el diccionario sin reiniciar la aplicación
     * (Por ejemplo, si se agregaron nuevas palabras a la BD)
     */
    public void recargar() throws SQLException, ClassNotFoundException {
        cargarDesdeBaseDatos();
        System.out.println("✓ Diccionario Técnico recargado desde BD");
    }

    // Getters - Retornan las listas en memoria (procesamiento rápido)
    public ArrayList<Tockenizer> getRedes() { return redes; }
    public ArrayList<Tockenizer> getImpresoras() { return impresoras; }
    public ArrayList<Tockenizer> getCuentas() { return cuentas; }
    public ArrayList<Tockenizer> getHardware() { return hardware; }
}