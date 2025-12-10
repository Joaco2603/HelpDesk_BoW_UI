package cr.ac.ucenfotec.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Aplicación principal JavaFX para el sistema HelpDesk
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class MainApp extends Application {
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("HelpDesk BoW - Sistema de Tickets");
        
        // Cargar la pantalla de login
        showLoginView();
        
        primaryStage.show();
    }
    
    /**
     * Obtiene la URL del recurso, buscando en varias ubicaciones posibles
     */
    private static URL getResourceURL(String path) {
        // Intentar varias rutas posibles
        URL url = MainApp.class.getResource(path);
        if (url == null) {
            url = MainApp.class.getResource("/resources" + path);
        }
        if (url == null) {
            url = MainApp.class.getClassLoader().getResource(path.substring(1));
        }
        if (url == null) {
            url = MainApp.class.getClassLoader().getResource("resources" + path);
        }
        if (url == null) {
            // Intentar como archivo directo
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
    
    /**
     * Muestra la vista de login
     */
    public static void showLoginView() throws Exception {
        URL fxmlUrl = getResourceURL("/fxml/LoginView.fxml");
        if (fxmlUrl == null) {
            throw new IllegalStateException("No se encontró LoginView.fxml. Verifica que la carpeta 'resources' esté marcada como Resources Root en IntelliJ.");
        }
        
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 400, 500);
        
        URL cssUrl = getResourceURL("/css/styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }
    
    /**
     * Muestra la vista principal del dashboard
     */
    public static void showDashboardView(String usuarioId, String rol) throws Exception {
        URL fxmlUrl = getResourceURL("/fxml/DashboardView.fxml");
        if (fxmlUrl == null) {
            throw new IllegalStateException("No se encontró DashboardView.fxml");
        }
        
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        
        // Pasar datos al controlador
        DashboardController controller = loader.getController();
        controller.setUsuario(usuarioId, rol);
        
        Scene scene = new Scene(root, 1200, 700);
        
        URL cssUrl = getResourceURL("/css/styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
    }
    
    /**
     * Muestra la vista de registro (placeholder)
     */
    public static void showRegisterView() throws Exception {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Registro");
        alert.setHeaderText("Función de Registro");
        alert.setContentText("La función de registro está disponible. Contacte al administrador para crear una cuenta.");
        alert.showAndWait();
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
