package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorTicketMySQL;
import cr.ac.ucenfotec.bl.logic.GestorUsuarioMySQL;
import cr.ac.ucenfotec.bl.logic.GestorDepartamentoMySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;

/**
 * Controlador principal del Dashboard
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class DashboardController {
    
    /**
     * Obtiene la URL del recurso, buscando en varias ubicaciones posibles
     */
    private URL getResourceURL(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            url = getClass().getResource("/resources" + path);
        }
        if (url == null) {
            url = getClass().getClassLoader().getResource(path.substring(1));
        }
        if (url == null) {
            url = getClass().getClassLoader().getResource("resources" + path);
        }
        if (url == null) {
            try {
                java.io.File file = new java.io.File("src/resources" + path);
                if (file.exists()) {
                    url = file.toURI().toURL();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }
    @FXML
    private BorderPane mainContainer;
    
    @FXML
    private Label userNameLabel;
    
    @FXML
    private Label userRoleLabel;
    
    @FXML
    private Button ticketsButton;
    
    @FXML
    private Button usersButton;
    
    @FXML
    private Button departmentsButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private VBox contentArea;
    
    private String usuarioId;
    private String rol;
    
    private GestorTicketMySQL gestorTicket;
    private GestorUsuarioMySQL gestorUsuario;
    private GestorDepartamentoMySQL gestorDepartamento;
    
    @FXML
    public void initialize() {
        gestorTicket = new GestorTicketMySQL();
        gestorUsuario = new GestorUsuarioMySQL();
        gestorDepartamento = new GestorDepartamentoMySQL();
    }
    
    public void setUsuario(String usuarioId, String rol) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        
        // Obtener información del usuario
        String usuarioInfo = gestorUsuario.findUsuarioById(usuarioId);
        String nombre = extraerValor(usuarioInfo, "nombre");
        
        userNameLabel.setText(nombre);
        userRoleLabel.setText(rol.toUpperCase());
        
        // Configurar permisos según rol
        configurarPermisos();
        
        // Cargar vista de tickets por defecto
        handleTickets();
    }
    
    private void configurarPermisos() {
        if (!rol.equals("admin")) {
            usersButton.setVisible(false);
            usersButton.setManaged(false);
        }
    }
    
    @FXML
    private void handleTickets() {
        try {
            URL fxmlUrl = getResourceURL("/fxml/TicketView.fxml");
            if (fxmlUrl == null) {
                showError("No se encontró TicketView.fxml");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent ticketView = loader.load();
            
            TicketViewController controller = loader.getController();
            controller.setUsuario(usuarioId, rol);
            
            contentArea.getChildren().clear();
            contentArea.getChildren().add(ticketView);
            
            // Actualizar botones activos
            setActiveButton(ticketsButton);
        } catch (Exception e) {
            showError("Error al cargar vista de tickets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleUsers() {
        try {
            URL fxmlUrl = getResourceURL("/fxml/UserView.fxml");
            if (fxmlUrl == null) {
                showError("No se encontró UserView.fxml");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent userView = loader.load();
            
            UserViewController controller = loader.getController();
            controller.setUsuario(usuarioId, rol);
            
            contentArea.getChildren().clear();
            contentArea.getChildren().add(userView);
            
            setActiveButton(usersButton);
        } catch (Exception e) {
            showError("Error al cargar vista de usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleDepartments() {
        try {
            URL fxmlUrl = getResourceURL("/fxml/DepartmentView.fxml");
            if (fxmlUrl == null) {
                showError("No se encontró DepartmentView.fxml");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent deptView = loader.load();
            
            DepartmentViewController controller = loader.getController();
            controller.setUsuario(usuarioId, rol);
            
            contentArea.getChildren().clear();
            contentArea.getChildren().add(deptView);
            
            setActiveButton(departmentsButton);
        } catch (Exception e) {
            showError("Error al cargar vista de departamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleLogout() {
        try {
            MainApp.showLoginView();
        } catch (Exception e) {
            showError("Error al cerrar sesión: " + e.getMessage());
        }
    }
    
    private void setActiveButton(Button activeButton) {
        ticketsButton.getStyleClass().remove("active-button");
        usersButton.getStyleClass().remove("active-button");
        departmentsButton.getStyleClass().remove("active-button");
        
        activeButton.getStyleClass().add("active-button");
    }
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private String extraerValor(String texto, String campo) {
        int startIndex = texto.indexOf(campo + "='");
        if (startIndex == -1) return "";
        startIndex += campo.length() + 2;
        int endIndex = texto.indexOf("'", startIndex);
        if (endIndex == -1) return "";
        return texto.substring(startIndex, endIndex);
    }
}
