package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorUsuarioMySQL;
import cr.ac.ucenfotec.bl.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controlador para la vista de Login
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class LoginController {
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Hyperlink registerLink;
    
    private GestorUsuarioMySQL gestorUsuario;
    
    @FXML
    public void initialize() {
        gestorUsuario = new GestorUsuarioMySQL();
        
        // Permitir login con Enter
        passwordField.setOnKeyPressed(this::handleKeyPressed);
    }
    
    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        
        if (email.isEmpty() || password.isEmpty()) {
            showError("Por favor ingrese email y contraseña");
            return;
        }
        
        String resultado = gestorUsuario.loginUsuario(email, password);
        
        if (resultado.equals("Usuario no encontrado")) {
            showError("Credenciales inválidas");
        } else {
            // Extraer ID y rol del resultado
            // Formato: Usuario{id='...', nombre='...', correo='...', telefono='...', rol='...'}
            String usuarioId = extraerValor(resultado, "id");
            String rol = extraerValor(resultado, "rol");
            
            try {
                MainApp.showDashboardView(usuarioId, rol);
            } catch (Exception e) {
                showError("Error al cargar dashboard: " + e.getMessage());
            }
        }
    }
    
    @FXML
    private void handleRegister() {
        try {
            MainApp.showRegisterView();
        } catch (Exception e) {
            showError("Error al cargar pantalla de registro: " + e.getMessage());
        }
    }
    
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLogin();
        }
    }
    
    private void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }
    
    /**
     * Extrae un valor de un string toString() de un objeto
     */
    private String extraerValor(String texto, String campo) {
        int startIndex = texto.indexOf(campo + "='");
        if (startIndex == -1) return "";
        startIndex += campo.length() + 2;
        int endIndex = texto.indexOf("'", startIndex);
        if (endIndex == -1) return "";
        return texto.substring(startIndex, endIndex);
    }
}
