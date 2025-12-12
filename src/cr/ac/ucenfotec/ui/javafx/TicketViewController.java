package cr.ac.ucenfotec.ui.javafx;

import cr.ac.ucenfotec.bl.logic.GestorTicketMySQL;
import cr.ac.ucenfotec.bl.logic.GestorDepartamentoMySQL;
import cr.ac.ucenfotec.bl.logic.GestorUsuarioMySQL;
import cr.ac.ucenfotec.bl.entities.ClasificadorBoW;
import cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.TicketsDAO;
import cr.ac.ucenfotec.dl.DepartamentosDAO;
import cr.ac.ucenfotec.dl.UsuariosDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Controlador para la vista de gestión de Tickets
 * @author Equipo HelpDesk
 * @version 2.0
 */
public class TicketViewController {
    @FXML
    private TableView<Ticket> ticketsTable;
    
    @FXML
    private TableColumn<Ticket, Integer> idColumn;
    
    @FXML
    private TableColumn<Ticket, String> asuntoColumn;
    
    @FXML
    private TableColumn<Ticket, String> estadoColumn;
    
    @FXML
    private TableColumn<Ticket, String> prioridadColumn;
    
    @FXML
    private TableColumn<Ticket, String> departamentoColumn;
    
    @FXML
    private TextField asuntoField;
    
    @FXML
    private TextArea descripcionArea;
    
    @FXML
    private ComboBox<String> prioridadCombo;
    
    @FXML
    private ComboBox<String> estadoCombo;
    
    @FXML
    private ComboBox<Departamento> departamentoCombo;
    
    @FXML
    private Button createButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button clearButton;
    
    @FXML
    private Button classifyButton;
    
    @FXML
    private Label clasificacionLabel;
    
    private String usuarioId;
    private String rol;
    
    private GestorTicketMySQL gestorTicket;
    private GestorDepartamentoMySQL gestorDepartamento;
    private GestorUsuarioMySQL gestorUsuario;
    private ClasificadorBoW clasificador;
    
    private TicketsDAO ticketsDAO;
    private DepartamentosDAO departamentosDAO;
    private UsuariosDAO usuariosDAO;
    
    private Ticket ticketSeleccionado;
    
    @FXML
    public void initialize() {
        gestorTicket = new GestorTicketMySQL();
        gestorDepartamento = new GestorDepartamentoMySQL();
        gestorUsuario = new GestorUsuarioMySQL();
        
        try {
            clasificador = new ClasificadorBoW();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        ticketsDAO = new TicketsDAO();
        departamentosDAO = new DepartamentosDAO();
        usuariosDAO = new UsuariosDAO();
        
        // Configurar columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        asuntoColumn.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        prioridadColumn.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
        departamentoColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDepartamento().getNombre()));
        
        // Configurar ComboBoxes
        prioridadCombo.setItems(FXCollections.observableArrayList("Baja", "Media", "Alta"));
        estadoCombo.setItems(FXCollections.observableArrayList("Abierto", "En Proceso", "Cerrado"));
        
        // Cargar departamentos
        cargarDepartamentos();
        
        // Listener para selección en tabla
        ticketsTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    cargarTicketEnFormulario(newSelection);
                }
            });
    }
    
    public void setUsuario(String usuarioId, String rol) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        
        cargarTickets();
    }
    
    private void cargarDepartamentos() {
        try {
            ArrayList<Departamento> departamentos = departamentosDAO.getDepartamentos();
            departamentoCombo.setItems(FXCollections.observableArrayList(departamentos));
            
            // Configurar cómo se muestra cada departamento
            departamentoCombo.setCellFactory(param -> new ListCell<Departamento>() {
                @Override
                protected void updateItem(Departamento item, boolean empty) {
                    super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        });
        
        departamentoCombo.setButtonCell(new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        });
        } catch (Exception e) {
            showError("Error al cargar departamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void cargarTickets() {
        try {
            ArrayList<Ticket> tickets = ticketsDAO.getTickets();
            ObservableList<Ticket> ticketList = FXCollections.observableArrayList(tickets);
            ticketsTable.setItems(ticketList);
        } catch (Exception e) {
            showError("Error al cargar tickets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCreate() {
        if (!validarFormulario()) {
            return;
        }
        
        try {
            // Obtener el usuario actual
            Usuario usuario = obtenerUsuarioActual();
            if (usuario == null) {
                showError("Error al obtener usuario actual");
                return;
            }
            
            String asunto = asuntoField.getText();
            String descripcion = descripcionArea.getText();
            String prioridad = prioridadCombo.getValue();
            Departamento depto = departamentoCombo.getValue();
            
            // El ID lo genera MySQL con AUTO_INCREMENT, pasamos 0
            String resultado = gestorTicket.createTicket(0, asunto, descripcion, prioridad, usuario, depto);
            
            if (resultado.contains("Error")) {
                showError(resultado);
                return;
            }
            
            cargarTickets();
            limpiarFormulario();
            showInfo("Ticket creado exitosamente");
            
        } catch (Exception e) {
            showError("Error al crear ticket: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleUpdate() {
        if (ticketSeleccionado == null) {
            showError("Por favor seleccione un ticket");
            return;
        }
        
        if (!validarFormulario()) {
            return;
        }
        
        try {
            ticketSeleccionado.setAsunto(asuntoField.getText());
            ticketSeleccionado.setDescripcion(descripcionArea.getText());
            ticketSeleccionado.setEstado(estadoCombo.getValue());
            ticketSeleccionado.setPrioridad(prioridadCombo.getValue());
            ticketSeleccionado.setDepartamento(departamentoCombo.getValue());

            String resultado = gestorTicket.updateTicket(ticketSeleccionado);

            if(resultado.contains("Error")) {
                showInfo(resultado);
            }

            ticketsTable.refresh();
            showInfo("Ticket actualizado exitosamente");
            
        } catch (Exception e) {
            showError("Error al actualizar ticket: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleDelete() {
        if (ticketSeleccionado == null) {
            showError("Por favor seleccione un ticket");
            return;
        }
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar este ticket?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");
        
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String resultado = gestorTicket.deleteTicket(ticketSeleccionado.getId());
                    if (resultado.contains("exitosamente")) {
                        cargarTickets();
                        limpiarFormulario();
                        showInfo("Ticket eliminado exitosamente");
                    } else {
                        showError(resultado);
                    }
                } catch (Exception e) {
                    showError("Error al eliminar ticket: " + e.getMessage());
                }
            }
        });
    }
    
    @FXML
    private void handleClassify() {
        String texto = descripcionArea.getText();
        if (texto == null || texto.isEmpty()) {
            showError("Por favor ingrese una descripción para clasificar");
            return;
        }
        
        String categoriaEmocional = clasificador.clasificarEmocional(texto);
        String categoriaTecnica = clasificador.clasificarTecnico(texto);
        
        clasificacionLabel.setText(
            "Clasificación BoW:\n" +
            "• Emocional: " + categoriaEmocional + "\n" +
            "• Técnica: " + categoriaTecnica
        );
    }
    
    @FXML
    private void handleClear() {
        limpiarFormulario();
    }
    
    private void cargarTicketEnFormulario(Ticket ticket) {
        ticketSeleccionado = ticket;
        
        asuntoField.setText(ticket.getAsunto());
        descripcionArea.setText(ticket.getDescripcion());
        estadoCombo.setValue(ticket.getEstado());
        prioridadCombo.setValue(ticket.getPrioridad());
        departamentoCombo.setValue(ticket.getDepartamento());
        
        clasificacionLabel.setText("");
    }
    
    private void limpiarFormulario() {
        ticketSeleccionado = null;
        
        asuntoField.clear();
        descripcionArea.clear();
        estadoCombo.setValue("Abierto");
        prioridadCombo.setValue("Media");
        departamentoCombo.getSelectionModel().clearSelection();
        clasificacionLabel.setText("");
        
        ticketsTable.getSelectionModel().clearSelection();
    }
    
    private boolean validarFormulario() {
        if (asuntoField.getText().isEmpty()) {
            showError("Por favor ingrese un asunto");
            return false;
        }
        
        if (descripcionArea.getText().isEmpty()) {
            showError("Por favor ingrese una descripción");
            return false;
        }
        
        if (prioridadCombo.getValue() == null) {
            showError("Por favor seleccione una prioridad");
            return false;
        }
        
        if (estadoCombo.getValue() == null) {
            showError("Por favor seleccione un estado");
            return false;
        }
        
        if (departamentoCombo.getValue() == null) {
            showError("Por favor seleccione un departamento");
            return false;
        }
        
        return true;
    }
    
    private Usuario obtenerUsuarioActual() {
        try {
            return usuariosDAO.findUsuarioById(usuarioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
