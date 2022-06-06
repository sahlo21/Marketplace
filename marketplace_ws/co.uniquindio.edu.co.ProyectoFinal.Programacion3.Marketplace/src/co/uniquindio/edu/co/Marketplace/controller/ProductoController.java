package co.uniquindio.edu.co.Marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.Exceptions.ProductoNoSeleccionadoException;
import co.uniquindio.edu.co.Marketplace.model.Comentario;
import co.uniquindio.edu.co.Marketplace.model.MeGusta;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductoController implements Initializable {
	private ModelFactoryController modelFactoryController;
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<MeGusta> listaMeGusta = new ArrayList<>();
	ObservableList<Comentario> listaComentariosData = FXCollections.observableArrayList();
	Aplicacion aplicacion;
	Producto productoSeleccionado;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextArea textAreaComentario;

	@FXML
	private TableView<Comentario> tableComentarios;

	@FXML
	private TableColumn<Comentario, String> columnComentario;

	@FXML
	private TableColumn<Comentario, Date> columnFecha;

	@FXML
	private TableColumn<Comentario, String> columnUser;

	@FXML
	private Label lblCantidadLikes;

	@FXML
	private Label labelNombreProducto;

	@FXML
	private Label labelPrecioProducto;

	@FXML
	private Label labelCategoriaProducto;
	@FXML
	private ImageView ivImagen;
	@FXML
	private Button buttonLike;

	@FXML
	void meGustaAction(ActionEvent event) {
		contarMeGusta();
	}

	@FXML
	void abrirPerfilVendedorAction(ActionEvent event) {
		abrirPerfil();
	}

	@FXML
	void agregarComentarioAction(ActionEvent event) {
		crearComentarioProducto();
	}


	/**
	 * metodo para abrir el perfil del usuario 
	 */
	void abrirPerfil() {

		try {
			// Cargo la vista
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MarketplacePerfilView.fxml"));

			// Cargo la ventana
			Parent root = loader.load();
			// Cojo el controlador
			PerfilVendedorController controlador = loader.getController();
			String codeVendedorPerfil = productoSeleccionado.getCodVendedor();
			listaVendedores.clear();
			listaVendedores.addAll(modelFactoryController.obtenerVendedor());

			for (Vendedor vendedor : listaVendedores) {

				if (codeVendedorPerfil.equals(vendedor.getCedula())) {

					controlador.initAttributtes(vendedor);

				}
			}

			// Creo el Scene
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Perfil vendedor");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("../resources/user.png")));

			stage.showAndWait();

		} catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}

	/**
	 * metoo que crea un nuevo comentario sobre un producto 
	 */
	public void crearComentarioProducto() {
		String textoComenatario = textAreaComentario.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComentario = sdf.format(new Date());
		String vendedorComentario = modelFactoryController.getVendedorLogueado().getNombre();


		if (!textoComenatario.equals("")) {
			Comentario comentarioProducto = null;

			comentarioProducto = modelFactoryController.crearComentario(textoComenatario, fechaComentario,
					vendedorComentario);

             textAreaComentario.setText("");
			if (comentarioProducto != null) {
				listaComentariosData.add(comentarioProducto);
			}
		} else {
			mostrarMensajeError("Por favor escriba un comentario");
		}
	}

	public void contarMeGusta() {

		String codVendedorLike = modelFactoryController.getVendedorLogueado().getCedula();

		MeGusta miLike = null;

		miLike = modelFactoryController.crearMeGusta(codVendedorLike);
		buttonLike.setStyle("-fx-background-color: #FF3380");

		if (miLike == null) {
			modelFactoryController.eliminarMeGusta(codVendedorLike);
			buttonLike.setStyle("-fx-background-color: #0080ff;");
		}
		lblCantidadLikes.setText(" " + modelFactoryController.getProductoActual().getListaMeGustas().size());

	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		modelFactoryController = ModelFactoryController.getInstance();

		columnComentario.setCellValueFactory(new PropertyValueFactory<Comentario, String>("texto"));
		columnFecha.setCellValueFactory(new PropertyValueFactory<Comentario, Date>("fecha"));
		columnUser.setCellValueFactory(new PropertyValueFactory<Comentario, String>("userVendedor"));

	}

	public void initAttributtes(Producto productoSeleccionado, Vendedor vendedorActual) {
		this.productoSeleccionado = productoSeleccionado;
		if (productoSeleccionado != null) {
			ArrayList<MeGusta> listaMeGustaAux = new ArrayList<>();
			listaMeGustaAux=productoSeleccionado.getListaMeGustas();
			System.out.println("liasdjas: "+listaMeGustaAux);
			
			for (MeGusta meGusta : listaMeGustaAux) {
				if (meGusta.getCodVendedor().equals(vendedorActual.getCedula())) {
					 buttonLike.setStyle("-fx-background-color: #FF3380");
				}else{
					 buttonLike.setStyle("-fx-background-color: #0080ff");
				}	
			}
			labelNombreProducto.setText(labelNombreProducto.getText() + productoSeleccionado.getNombre());
			labelPrecioProducto.setText(labelPrecioProducto.getText() + productoSeleccionado.getPrecio());
			labelCategoriaProducto.setText(labelCategoriaProducto.getText() + productoSeleccionado.getCategoria());
			ivImagen.setImage(productoSeleccionado.getImagen());
			lblCantidadLikes.setText(" " + productoSeleccionado.getListaMeGustas().size());
			listaComentariosData.addAll(productoSeleccionado.getListaComentarios());
			tableComentarios.getItems().clear();
			tableComentarios.setItems(listaComentariosData);
		
		} else {
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
//	public void setAplicacion(Aplicacion aplicacion) {
//		this.aplicacion=aplicacion;
//		tableProductos.getItems().clear();
//		tableProductos.setItems(getListaProductoData());
//		tableMuro.getItems().clear();
//		tableMuro.setItems(getListaMuro());
//
//
//
//	}

}
