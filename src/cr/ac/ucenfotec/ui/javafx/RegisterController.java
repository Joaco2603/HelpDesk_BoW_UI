package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorUsuarioMySQL;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;

import java.util.UUID;

/**
 * Controlador para la vista de Registro de nuevos usuarios
 * @author Equipo HelpDesk
 * @version 1.0
 */
public class RegisterController {
    @FXML
    private TextField nombreField;
    
    @FXML
    private TextField correoField;
    
    @FXML
    private TextField telefonoField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private ComboBox<String> rolCombo;
    
    @FXML
    private Button registerButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Hyperlink loginLink;
    
    private GestorUsuarioMySQL gestorUsuario;
    
    @FXML
    public void initialize() {
        gestorUsuario = new GestorUsuarioMySQL();
        
        // Configurar ComboBox de roles
        rolCombo.setItems(FXCollections.observableArrayList("usuario", "soporte", "admin"));
        rolCombo.setValue("usuario"); // Valor por defecto
        
        // Permitir registro con Enter en el último campo
        confirmPasswordField.setOnKeyPressed(this::handleKeyPressed);
    }
    
    @FXML
    private void handleRegister() {
        String nombre = nombreField.getText().trim();
        String correo = correoField.getText().trim();
        String telefono = telefonoField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String rol = rolCombo.getValue();
        
        // Validaciones
        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || password.isEmpty()) {
            showError("Por favor complete todos los campos");
            return;
        }
        
        if (!correo.contains("@") || !correo.contains(".")) {
            showError("Por favor ingrese un correo electrónico válido");
            return;
        }
        
        if (password.length() < 4) {
            showError("La contraseña debe tener al menos 4 caracteres");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden");
            return;
        }
        
        if (rol == null || rol.isEmpty()) {
            showError("Por favor seleccione un rol");
            return;
        }
        
        try {
            // Crear nuevo usuario
            String nuevoId = UUID.randomUUID().toString();
            
            // Usar el método addUsuario del gestor
            String resultado = gestorUsuario.addUsuario(nuevoId, nombre, correo, password, telefono, rol);
            
            if (resultado != null && !resultado.isEmpty()) {
                showSuccess("¡Cuenta creada exitosamente! Ahora puede iniciar sesión.");
                
                // Esperar 2 segundos y volver al login
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        javafx.application.Platform.runLater(() -> {
                            try {
                                handleBackToLogin();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                showError("Error al crear la cuenta");
            }
        } catch (Exception e) {
            showError("Error al crear la cuenta: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCancel() {
        limpiarFormulario();
    }
    
    @FXML
    private void handleBackToLogin() {
        try {
            MainApp.showLoginView();
        } catch (Exception e) {
            showError("Error al volver al login: " + e.getMessage());
        }
    }
    
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleRegister();
        }
    }
    
    private void limpiarFormulario() {
        nombreField.clear();
        correoField.clear();
        telefonoField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        rolCombo.setValue("usuario");
        messageLabel.setText("");
    }
    
    private void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #d32f2f;");
    }
    
    private void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #388e3c;");
    }
}
