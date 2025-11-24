package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.Tockenizer;
import java.util.ArrayList;

/**
 * Diccionario de palabras para análisis técnico
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class DataDiccionarioTecnico {
    private ArrayList<Tockenizer> redes;
    private ArrayList<Tockenizer> impresoras;
    private ArrayList<Tockenizer> cuentas;
    private ArrayList<Tockenizer> hardware;

    /**
     * Constructor por defecto que carga el diccionario
     */
    public DataDiccionarioTecnico() {
        redes = new ArrayList<>();
        impresoras = new ArrayList<>();
        cuentas = new ArrayList<>();
        hardware = new ArrayList<>();

        cargarRedes();
        cargarImpresoras();
        cargarCuentas();
        cargarHardware();
    }

    private void cargarRedes() {
        redes.add(new Tockenizer("red", "Técnico", "Redes"));
        redes.add(new Tockenizer("wifi", "Técnico", "Redes"));
        redes.add(new Tockenizer("router", "Técnico", "Redes"));
        redes.add(new Tockenizer("cable", "Técnico", "Redes"));
        redes.add(new Tockenizer("lan", "Técnico", "Redes"));
        redes.add(new Tockenizer("ethernet", "Técnico", "Redes"));
        redes.add(new Tockenizer("switch", "Técnico", "Redes"));
        redes.add(new Tockenizer("conexión", "Técnico", "Redes"));
        redes.add(new Tockenizer("servidor", "Técnico", "Redes"));
        redes.add(new Tockenizer("ping", "Técnico", "Redes"));
    }

    private void cargarImpresoras() {
        impresoras.add(new Tockenizer("impresora", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("imprimir", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("papel", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("toner", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("cartucho", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("cola", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("error", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("driver", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("escáner", "Técnico", "Impresoras"));
        impresoras.add(new Tockenizer("configuración", "Técnico", "Impresoras"));
    }

    private void cargarCuentas() {
        cuentas.add(new Tockenizer("login", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("usuario", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("contraseña", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("perfil", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("acceso", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("permiso", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("seguridad", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("auth", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("email", "Técnico", "Cuentas"));
        cuentas.add(new Tockenizer("sesión", "Técnico", "Cuentas"));
    }

    private void cargarHardware() {
        hardware.add(new Tockenizer("cpu", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("ram", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("disco", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("placa", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("tarjeta", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("monitor", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("mouse", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("teclado", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("fuente", "Técnico", "Hardware"));
        hardware.add(new Tockenizer("ventilador", "Técnico", "Hardware"));
    }

    // Getters
    public ArrayList<Tockenizer> getRedes() { return redes; }
    public ArrayList<Tockenizer> getImpresoras() { return impresoras; }
    public ArrayList<Tockenizer> getCuentas() { return cuentas; }
    public ArrayList<Tockenizer> getHardware() { return hardware; }
}