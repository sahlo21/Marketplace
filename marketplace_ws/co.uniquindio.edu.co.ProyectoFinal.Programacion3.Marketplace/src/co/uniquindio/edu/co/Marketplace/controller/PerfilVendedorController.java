package co.uniquindio.edu.co.Marketplace.controller;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PerfilVendedorController implements Initializable {
	
	ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
	Vendedor vendedorSeleccionado;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
	@FXML
	private TableColumn<Producto, Image> columnImagenProducto;

	@FXML
	private TableColumn<Producto, String> columnPrecioProducto;
	@FXML
	private TableColumn<Producto, Date> columnFechaPublicacion;
	@FXML
	private TableColumn<Producto, Categoria> columnCategoriaProducto;

	@FXML
	private TableColumn<Producto, Estado> columnEstadoProducto;
	
	

	@FXML
	private TableColumn<Producto, String> columnNombreProducto;

   
    @FXML
    private TableView<Producto> tablePublicaciones;

    @FXML
    private Label labelNombreVendedor;

   
    @FXML
    private Label labelApellidoVendedor;
	 ModelFactoryController modelFactoryController;

  
   
    @FXML
	void agregarContactoAction(ActionEvent event) {

		Vendedor vendedorSolicitud = modelFactoryController.getVendedorLogueado();


		if (vendedorSolicitud.getCedula() != vendedorSeleccionado.getCedula()) {
			System.out.println("Perfil Vendedor soli "+vendedorSolicitud.getNombre());
			System.out.println("Perfil Vendedor destino "+ vendedorSeleccionado.getNombre());
			modelFactoryController.crearSolicitud(vendedorSolicitud, vendedorSeleccionado.getCedula(), false);
		}
	}


    @FXML
    void enviarMensajeAction(ActionEvent event) {
    	try {
			// Cargo la vista
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ChatVendedorView.fxml"));

			// Cargo la ventana
			Parent root = loader.load();
			// Cojo el controlador
			ChatVendedorController controlador = loader.getController();
			controlador.setVendedorSeleccionado(vendedorSeleccionado);

			//           controlador.initAttributtes(personas);

			

			


			// Creo el Scene
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Chat");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("../resources/email.png")));

			

			stage.showAndWait();

		} catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
    }
   

    public void initAttributtes(Vendedor vendedorSeleccionado) {
		if(vendedorSeleccionado!=null){
	    	this.vendedorSeleccionado=vendedorSeleccionado;

		System.out.println("productoSeleccionado: "+vendedorSeleccionado);
		labelNombreVendedor.setText(labelNombreVendedor.getText() + vendedorSeleccionado.getNombre());
		labelApellidoVendedor.setText(labelApellidoVendedor.getText() + vendedorSeleccionado.getApellidos());
		listaProductosData.addAll(vendedorSeleccionado.getListaProductos());
		tablePublicaciones.getItems().clear();
		tablePublicaciones.setItems(listaProductosData);
//		
		}else{
			mostrarMensajeError("Producto no seleccionado\nDebe seleccionar un producto");

		}
	}
    private boolean mostrarMensajeError(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}


    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelFactoryController = ModelFactoryController.getInstance();

		columnPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
		columnNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		columnImagenProducto.setCellValueFactory(new PropertyValueFactory<Producto, Image>("imagen"));
		columnEstadoProducto.setCellValueFactory(new PropertyValueFactory<Producto, Estado>("estado"));
		columnCategoriaProducto.setCellValueFactory(new PropertyValueFactory<Producto, Categoria>("categoria"));
		columnFechaPublicacion.setCellValueFactory(new PropertyValueFactory<Producto, Date>("fechaPublicacion"));
    }
}
