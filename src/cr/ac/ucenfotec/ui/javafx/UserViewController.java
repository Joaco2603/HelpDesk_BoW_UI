package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.DataUsuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Controlador para la vista de gestión de Usuarios
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class UserViewController {
    @FXML
    private TableView<Usuario> usersTable;
    
    @FXML
    private TableColumn<Usuario, String> idColumn;
    
    @FXML
    private TableColumn<Usuario, String> nombreColumn;
    
    @FXML
    private TableColumn<Usuario, String> correoColumn;
    
    @FXML
    private TableColumn<Usuario, String> telefonoColumn;
    
    @FXML
    private TableColumn<Usuario, String> rolColumn;
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private TextField correoField;
    
    @FXML
    private TextField telefonoField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ComboBox<String> rolCombo;
    
    @FXML
    private Button createButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button clearButton;
    
    private String usuarioId;
    private String rol;
    
    private GestorUsuario gestorUsuario;
    private DataUsuarios dataUsuarios;
    
    private Usuario usuarioSeleccionado;
    
    @FXML
    public void initialize() {
        gestorUsuario = new GestorUsuario();
        dataUsuarios = new DataUsuarios();
        
        // Configurar columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        correoColumn.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol"));
        
        // Configurar ComboBox de roles
        rolCombo.setItems(FXCollections.observableArrayList("admin", "soporte", "usuario"));
        
        // Listener para selección en tabla
        usersTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    cargarUsuarioEnFormulario(newSelection);
                }
            });
    }
    
    public void setUsuario(String usuarioId, String rol) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        
        cargarUsuarios();
    }
    
    private void cargarUsuarios() {
        ArrayList<Usuario> usuarios = dataUsuarios.getUsuarios();
        ObservableList<Usuario> userList = FXCollections.observableArrayList(usuarios);
        usersTable.setItems(userList);
    }
    
    @FXML
    private void handleCreate() {
        if (!validarFormulario()) {
            return;
        }
        
        try {
            // Generar ID único
            String nuevoId = UUID.randomUUID().toString();
            
            // Crear usuario
            Usuario nuevoUsuario = new Usuario(
                nuevoId,
                nombreField.getText(),
                correoField.getText(),
                passwordField.getText(),
                telefonoField.getText(),
                rolCombo.getValue()
            );
            
            dataUsuarios.addUsuario(nuevoUsuario);
            
            cargarUsuarios();
            limpiarFormulario();
            showInfo("Usuario creado exitosamente");
            
        } catch (Exception e) {
            showError("Error al crear usuario: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleUpdate() {
        if (usuarioSeleccionado == null) {
            showError("Por favor seleccione un usuario");
            return;
        }
        
        if (!validarFormulario()) {
            return;
        }
        
        try {
            usuarioSeleccionado.setNombre(nombreField.getText());
            usuarioSeleccionado.setCorreo(correoField.getText());
            usuarioSeleccionado.setTelefono(telefonoField.getText());
            usuarioSeleccionado.setRol(rolCombo.getValue());
            
            // Solo actualizar password si no está vacío
            if (!passwordField.getText().isEmpty()) {
                usuarioSeleccionado.setPassword(passwordField.getText());
            }
            
            usersTable.refresh();
            showInfo("Usuario actualizado exitosamente");
            
        } catch (Exception e) {
            showError("Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleDelete() {
        if (usuarioSeleccionado == null) {
            showError("Por favor seleccione un usuario");
            return;
        }
        
        // No permitir eliminar el usuario actual
        if (usuarioSeleccionado.getId().equals(usuarioId)) {
            showError("No puede eliminar su propio usuario");
            return;
        }
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar este usuario?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");
        
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dataUsuarios.getUsuarios().remove(usuarioSeleccionado);
                cargarUsuarios();
                limpiarFormulario();
                showInfo("Usuario eliminado exitosamente");
            }
        });
    }
    
    @FXML
    private void handleClear() {
        limpiarFormulario();
    }
    
    private void cargarUsuarioEnFormulario(Usuario usuario) {
        usuarioSeleccionado = usuario;
        
        nombreField.setText(usuario.getNombre());
        correoField.setText(usuario.getCorreo());
        telefonoField.setText(usuario.getTelefono());
        rolCombo.setValue(usuario.getRol());
        passwordField.clear(); // No mostrar password por seguridad
    }
    
    private void limpiarFormulario() {
        usuarioSeleccionado = null;
        
        nombreField.clear();
        correoField.clear();
        telefonoField.clear();
        passwordField.clear();
        rolCombo.getSelectionModel().clearSelection();
        
        usersTable.getSelectionModel().clearSelection();
    }
    
    private boolean validarFormulario() {
        if (nombreField.getText().isEmpty()) {
            showError("Por favor ingrese un nombre");
            return false;
        }
        
        if (correoField.getText().isEmpty()) {
            showError("Por favor ingrese un correo");
            return false;
        }
        
        if (!correoField.getText().contains("@")) {
            showError("Por favor ingrese un correo válido");
            return false;
        }
        
        if (telefonoField.getText().isEmpty()) {
            showError("Por favor ingrese un teléfono");
            return false;
        }
        
        if (rolCombo.getValue() == null) {
            showError("Por favor seleccione un rol");
            return false;
        }
        
        // Solo validar password si es un nuevo usuario
        if (usuarioSeleccionado == null && passwordField.getText().isEmpty()) {
            showError("Por favor ingrese una contraseña");
            return false;
        }
        
        return true;
    }
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
