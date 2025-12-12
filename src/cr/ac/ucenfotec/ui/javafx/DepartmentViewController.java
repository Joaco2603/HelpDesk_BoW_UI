package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorDepartamentoMySQL;
import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.DepartamentosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Controlador para la vista de gestión de Departamentos
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class DepartmentViewController {
    @FXML
    private TableView<Departamento> departmentsTable;
    
    @FXML
    private TableColumn<Departamento, Integer> idColumn;
    
    @FXML
    private TableColumn<Departamento, String> nombreColumn;
    
    @FXML
    private TableColumn<Departamento, String> descripcionColumn;
    
    @FXML
    private TableColumn<Departamento, String> contactoColumn;
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private TextArea descripcionArea;
    
    @FXML
    private TextField contactoField;
    
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
    
    private GestorDepartamentoMySQL gestorDepartamento;
    private DepartamentosDAO departamentosDAO;
    
    private Departamento departamentoSeleccionado;
    
    @FXML
    public void initialize() {
        gestorDepartamento = new GestorDepartamentoMySQL();
        departamentosDAO = new DepartamentosDAO();
        
        // Configurar columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        contactoColumn.setCellValueFactory(new PropertyValueFactory<>("contacto"));
        
        // Listener para selección en tabla
        departmentsTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    cargarDepartamentoEnFormulario(newSelection);
                }
            });
    }
    
    public void setUsuario(String usuarioId, String rol) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        
        cargarDepartamentos();
    }
    
    private void cargarDepartamentos() {
        try {
            ArrayList<Departamento> departamentos = departamentosDAO.getDepartamentos();
            ObservableList<Departamento> deptList = FXCollections.observableArrayList(departamentos);
            departmentsTable.setItems(deptList);
        } catch (Exception e) {
            showError("Error al cargar departamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCreate() {
        if (!validarFormulario()) {
            return;
        }
        
        try {
            // Crear departamento (el ID lo genera MySQL con AUTO_INCREMENT, pasamos 0)
            String nombre = nombreField.getText();
            String descripcion = descripcionArea.getText();
            String contacto = contactoField.getText();
            
            String resultado = gestorDepartamento.addDepartamento(0, nombre, descripcion, contacto);
            
            if (resultado.contains("Error")) {
                showError(resultado);
                return;
            }
            
            cargarDepartamentos();
            limpiarFormulario();
            showInfo("Departamento creado exitosamente");
            
        } catch (Exception e) {
            showError("Error al crear departamento: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleUpdate() {
        if (departamentoSeleccionado == null) {
            showError("Por favor seleccione un departamento");
            return;
        }
        
        if (!validarFormulario()) {
            return;
        }
        
        try {
            departamentoSeleccionado.setNombre(nombreField.getText());
            departamentoSeleccionado.setDescripcion(descripcionArea.getText());
            departamentoSeleccionado.setContacto(contactoField.getText());

            String resultado = gestorDepartamento.updateDepartamento(departamentoSeleccionado);

            if(resultado.contains("Error")) {
                showInfo(resultado);
            }
            
            departmentsTable.refresh();
            showInfo("Departamento actualizado exitosamente");
            
        } catch (Exception e) {
            showError("Error al actualizar departamento: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleDelete() {
        if (departamentoSeleccionado == null) {
            showError("Por favor seleccione un departamento");
            return;
        }
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar este departamento?");
        confirmacion.setContentText("Esta acción no se puede deshacer y puede afectar tickets relacionados.");
        
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String resultado = gestorDepartamento.deleteDepartamento(departamentoSeleccionado.getId());
                    if (resultado.contains("exitosamente")) {
                        cargarDepartamentos();
                        limpiarFormulario();
                        showInfo("Departamento eliminado exitosamente");
                    } else {
                        showError(resultado);
                    }
                } catch (Exception e) {
                    showError("Error al eliminar departamento: " + e.getMessage());
                }
            }
        });
    }
    
    @FXML
    private void handleClear() {
        limpiarFormulario();
    }
    
    private void cargarDepartamentoEnFormulario(Departamento departamento) {
        departamentoSeleccionado = departamento;
        
        nombreField.setText(departamento.getNombre());
        descripcionArea.setText(departamento.getDescripcion());
        contactoField.setText(departamento.getContacto());
    }
    
    private void limpiarFormulario() {
        departamentoSeleccionado = null;
        
        nombreField.clear();
        descripcionArea.clear();
        contactoField.clear();
        
        departmentsTable.getSelectionModel().clearSelection();
    }
    
    private boolean validarFormulario() {
        if (nombreField.getText().isEmpty()) {
            showError("Por favor ingrese un nombre");
            return false;
        }
        
        if (descripcionArea.getText().isEmpty()) {
            showError("Por favor ingrese una descripción");
            return false;
        }
        
        if (contactoField.getText().isEmpty()) {
            showError("Por favor ingrese información de contacto");
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
