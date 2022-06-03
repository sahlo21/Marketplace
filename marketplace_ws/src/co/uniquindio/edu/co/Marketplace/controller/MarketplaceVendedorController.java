package co.uniquindio.edu.co.Marketplace.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.Exceptions.ProductoExistenteException;
import co.uniquindio.edu.co.Marketplace.Exceptions.ProductoNoSeleccionadoException;
import co.uniquindio.edu.co.Marketplace.Exceptions.ValorNoNumericException;
import co.uniquindio.edu.co.Marketplace.Exceptions.VendedorNoSeleccionadoException;
import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Mensaje;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MarketplaceVendedorController implements Initializable{

	Aplicacion aplicacion;
	Producto productoSeleccionado;
	Mensaje msjSeleccionado;

	ArrayList<Producto> listaProductos = new ArrayList<>();
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
	ObservableList<Producto> listaMuro = FXCollections.observableArrayList();
	ObservableList<Mensaje> listaMensaje = FXCollections.observableArrayList();

	//	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

	private ModelFactoryController modelFactoryController;

	@FXML
	private DatePicker datePublicacionProducto;

	@FXML
	private ComboBox<Categoria> cbCategoria;

	@FXML
	private TextField txtPrecioProducto;

	@FXML
	private TableColumn<Producto, Image> columnImagenProducto;

	@FXML
	private TableColumn<Producto, String> columnPrecioProducto;
	@FXML
	private TableColumn<Producto, Date> columnFechaPublicacion;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private ImageView ivImagen;

	@FXML
	private TextField txtNombreProducto;

	@FXML
	private TableColumn<Producto, Image> columnImagenMuro;

	@FXML
	private TableColumn<Producto, String> columnPrecioMuro;
	@FXML
	private TableColumn<Producto, Date> columnFechaMuro;

	@FXML
	private TableColumn<Producto, String> columnProductoMuro;

	@FXML
	private TableView<Producto> tableProductos;

	@FXML
	private TableView<Producto> tableMuro;
	@FXML
	private TableColumn<Producto, String> columnNombreProducto;

	@FXML
	private TableColumn<Producto, Categoria> columnCategoriaMuro;

	@FXML
	private TableColumn<Producto, Estado> columnEstadoMuro;

	@FXML
	private TableColumn<Producto, Categoria> columnCategoriaProducto;

	@FXML
	private TableColumn<Producto, Estado> columnEstadoProducto;

	@FXML
	private TableView<Mensaje> tableMensaje;
	@FXML
	private TableColumn<Mensaje, String> columnFechaMensaje;

	@FXML
	private TableColumn<Mensaje, String> columnMensaje;

	@FXML
	private TableColumn<Mensaje, String> columnRemitente;

	@FXML
	private Label lblUserAdmin;
	@FXML
	private Label lblFecha;

	@FXML
	private Label lblHora;





	@FXML
	void cerrarSesionAction(ActionEvent event) {
		modelFactoryController.cerrarSesionVendedor(aplicacion);
	}


	@FXML
	void nuevoProductoAction(ActionEvent event) {
		limpiarCasillasProductos();
	}

	@FXML
	void actualizarProductoAction(ActionEvent event) {
		try {
			actualizarProducto();
		} catch (ProductoNoSeleccionadoException | ProductoExistenteException e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}
	}

	@FXML
	void agregarProductoAction(ActionEvent event) {

		try {
			agregarProducto();
		} catch (ProductoExistenteException | ValorNoNumericException e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}

	}


	@FXML
	void seleccionarImagenAction(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");

		// Agregar filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
				);

		// Obtener la imagen seleccionada
		File imgFile = fileChooser.showOpenDialog(aplicacion.getPrimaryStage());

		// Mostar la imagen
		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			ivImagen.setImage(image);
		}

	}

	@FXML
	void aceptarSolicitudAction(ActionEvent event) {


	}
	@FXML
	void enviarSolicitudAction(ActionEvent event) {


	}

	@FXML
	void eliminarProductoAction(ActionEvent event) {

		try{
			eliminarProducto();
		} catch (ProductoNoSeleccionadoException e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}
	}
	@FXML
	void abrirProductoAction(ActionEvent event) {
		if(productoSeleccionado!=null){

			abrirProducto();
		}else{
			mostrarMensajeError("Producto no seleccionado\n\nDebe seleccionar un producto");

		}

		//		aplicacion.showProducto(productoSeleccionado);
		//		modelFactoryController.setProductoActual(productoSeleccionado);
	}



	private void abrirProducto() {


		try {
			// Cargo la vista
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MarketplaceProductoView.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			// Cojo el controlador
			ProductoController controlador = loader.getController();


			controlador.initAttributtes(productoSeleccionado, modelFactoryController.getVendedorLogueado());


			modelFactoryController.setProductoActual(productoSeleccionado);

			// Creo el Scene
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Detalles producto");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("../resources/iconMarketplace.png")));


			stage.showAndWait();




		} catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}



	@FXML
	void responderMensajeAction(ActionEvent event) {
		if (msjSeleccionado!=null) {
		try {
			// Cargo la vista
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ChatVendedorView.fxml"));

			// Cargo la ventana
			Parent root = loader.load();
			// Cojo el controlador
			ChatVendedorController controlador = loader.getController();
			
				
			
			controlador.setVendedorSeleccionado(msjSeleccionado.getVendedorRemitente());

			






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
		}else{
			mostrarMensajeError("Por favor seleccione un mensaje");
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelFactoryController = ModelFactoryController.getInstance();



		lblFecha.setText(lblFecha.getText()+LocalDate.now(Clock.systemDefaultZone ()));
		lblHora.setText(lblHora.getText()+LocalTime.now());
		lblUserAdmin.setText(modelFactoryController.getVendedorLogueado().getNombre()+" "+modelFactoryController.getVendedorLogueado().getApellidos());



		columnPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
		columnNombreProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		//		columnImagenProducto.setCellValueFactory(new PropertyValueFactory<Producto, Image>("imagen"));
		columnEstadoProducto.setCellValueFactory(new PropertyValueFactory<Producto, Estado>("estado"));
		columnCategoriaProducto.setCellValueFactory(new PropertyValueFactory<Producto, Categoria>("categoria"));
		columnFechaPublicacion.setCellValueFactory(new PropertyValueFactory<Producto, Date>("fechaPublicacion"));

		tableProductos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{

			productoSeleccionado = newSelection;


			mostrarInformacionProducto(productoSeleccionado);

		});
		columnPrecioMuro.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnProductoMuro.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));

		//		columnImagenMuro.setPrefWidth(80); 
		//		columnImagenMuro.setCellValueFactory(new PropertyValueFactory<>("imagen"));
		//		columnImagenMuro.setCellValueFactory(new PropertyValueFactory<Producto, String>("imagen"));
		columnEstadoMuro.setCellValueFactory(new PropertyValueFactory<Producto, Estado>("estado"));
		columnCategoriaMuro.setCellValueFactory(new PropertyValueFactory<Producto, Categoria>("categoria"));
		columnFechaMuro.setCellValueFactory(new PropertyValueFactory<Producto, Date>("fechaPublicacion"));
		cbCategoria.getItems().addAll(Categoria.ACCESORIOS, Categoria.ELECTRODOMESTICOS, Categoria.HOGAR, Categoria.TECNOLOGIA, Categoria.VEHICULOS);
		tableMuro.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{

			productoSeleccionado = newSelection;


		});
		columnRemitente.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("nombreVendedor"));
		columnFechaMensaje.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("fecha"));
		columnMensaje.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("texto"));

		tableMensaje.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{

			msjSeleccionado = newSelection;


		});
	}

	public ObservableList<Producto> getListaProductoData() {

		listaProductosData.addAll(modelFactoryController.obtenerProducto());

		return listaProductosData;
	}
	public ObservableList<Producto> getListaMuro() {

		listaMuro.addAll(modelFactoryController.obtenerMuro());

		return listaMuro;
	}
	public ObservableList<Mensaje> getListaMensajes() {

		listaMensaje.addAll(modelFactoryController.obtenerMensajes());

		return listaMensaje;
	}


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
		tableProductos.getItems().clear();
		tableProductos.setItems(getListaProductoData());
		tableMuro.getItems().clear();
		tableMuro.setItems(getListaMuro());
		tableMensaje.getItems().clear();
		tableMensaje.setItems(getListaMensajes());



	}
	private void mostrarInformacionProducto(Producto productoSeleccionado) {


		if(productoSeleccionado != null){
			txtNombreProducto.setText(productoSeleccionado.getNombre());
			txtPrecioProducto.setText(String.valueOf(productoSeleccionado.getPrecio()));
			ivImagen.setImage(productoSeleccionado.getImagen());
			cbCategoria.setValue(productoSeleccionado.getCategoria());


		}
	}
	private void agregarProducto() throws ProductoExistenteException, ValorNoNumericException {

		String nombre=txtNombreProducto.getText();
		Image imagen= ivImagen.getImage();
		String precioAux=txtPrecioProducto.getText();;
		Categoria categoria=cbCategoria.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaPublicacion = sdf.format(new Date());


		Estado estado = Estado.PUBLICADO;
		String codVendedor=modelFactoryController.getVendedorLogueado().getCedula();

		if(datosValidosProducto(nombre, imagen, precioAux, categoria) == true){
			if (!(isNumericDouble(precioAux))) {
				Persistencia.guardarExceptionsLog("ValorNoNumericException", 3, "Agregar producto", modelFactoryController.getVendedorLogueado().getNombre(), modelFactoryController.getVendedorLogueado().getCedula());

				throw new ValorNoNumericException("El precio del producto debe ser un dato numerico");
			}
			double precio = Double.parseDouble(txtPrecioProducto.getText());


			Producto producto = null;


			producto = modelFactoryController.crearProducto(nombre,  imagen,  precio,  estado, categoria, fechaPublicacion, codVendedor);

			if(producto != null){

				listaProductosData.add(producto);
				listaMuro.add(producto);
				limpiarCasillasProductos();
				mostrarMensaje("Notificaci�n Producto", "Producto publicado", "El producto se ha publicado con �xito", AlertType.INFORMATION);
			}else{
				Persistencia.guardarExceptionsLog("ProductoExistenteException", 3, "Agregar producto",  modelFactoryController.getVendedorLogueado().getNombre(), modelFactoryController.getVendedorLogueado().getCedula());

				throw new ProductoExistenteException("El producto: "+nombre+" ya se encuentra registrado");

			}



		}
	}

	private void actualizarProducto() throws ProductoNoSeleccionadoException, ProductoExistenteException  {

		String nombre=txtNombreProducto.getText();
		Image imagen= ivImagen.getImage();
		String precioAux=txtPrecioProducto.getText();;
		Categoria categoria=cbCategoria.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaPublicacion = sdf.format(new Date());

		Estado estado = Estado.PUBLICADO;


		boolean flagcProductoActualizado = false;



		if(productoSeleccionado != null){
			if (mostrarMensajeConfirmacion("Desea actualizar a: " + productoSeleccionado.getNombre())) {
				if(datosValidosProducto(nombre, imagen, precioAux, categoria)== true){

					double precio = Double.parseDouble(txtPrecioProducto.getText());

					flagcProductoActualizado = modelFactoryController.actualizarProducto(productoSeleccionado.getNombre(), nombre,  imagen,  precio,  estado, categoria, fechaPublicacion);
					if(flagcProductoActualizado == true){

						tableProductos.refresh();
						tableMuro.refresh();
						//					limpiarCasillasProductos();
						mostrarMensaje("Notificaci�n Producto", "Producto actualizado", "El producto se ha actualizado con �xito", AlertType.INFORMATION);
					}else{
						Persistencia.guardarExceptionsLog("ProductoExistenteException", 3, "Actualizar producto",  modelFactoryController.getVendedorLogueado().getNombre(), modelFactoryController.getVendedorLogueado().getCedula());

						throw new ProductoExistenteException("El producto: "+nombre+" ya se encuentra registrado");

					}
				}else{

					mostrarMensaje("Notificaci�n Producto", "Producto no ha sido publicado","El producto no se puede actualizar", AlertType.ERROR);
				}
			}

		}else{
			Persistencia.guardarExceptionsLog("ProductoNoSeleccionadoException", 3, "Actualizar producto",  modelFactoryController.getVendedorLogueado().getNombre(), modelFactoryController.getVendedorLogueado().getCedula());

			throw new ProductoNoSeleccionadoException("Producto no seleccionado\nDebe seleccionar un producto");
		}



	}


	private void eliminarProducto()throws ProductoNoSeleccionadoException {

		boolean flagProductoEliminado = false;

		if(productoSeleccionado != null){
			if (mostrarMensajeConfirmacion("Desea eliminar a: " + productoSeleccionado.getNombre())) {

				flagProductoEliminado  = modelFactoryController.eliminarProducto(productoSeleccionado.getNombre());
				if(flagProductoEliminado  == true){

					listaProductosData.remove(productoSeleccionado);
					listaMuro.remove(productoSeleccionado);
					productoSeleccionado = null;
					tableProductos.getSelectionModel().clearSelection();
					limpiarCasillasProductos();					
					listaMuro.clear();	
					listaMuro.addAll(modelFactoryController.obtenerMuro());
					tableMuro.setItems(listaMuro);

					mostrarMensaje("Notificaci�n Producto", "Producto eliminado", "El producto se ha eliminado con �xito", AlertType.INFORMATION);

				}
			}
		}else{
			Persistencia.guardarExceptionsLog("ProductoNoSeleccionadoException", 3, "Eliminar producto",  modelFactoryController.getVendedorLogueado().getNombre(), modelFactoryController.getVendedorLogueado().getCedula());

			throw new ProductoNoSeleccionadoException("Producto no seleccionado\nDebe seleccionar un producto");
		}
	}




	public static boolean isNumericDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	public static boolean isNumericInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	private boolean datosValidosProducto(String nombre, Image imagen, String precioAux,
			Categoria categoria) {

		String mensaje = "";

		if(nombre == null || nombre.equals(""))
			mensaje += "El nombre del producto es invalido \n";

		if(precioAux == null || precioAux.equals(""))
			mensaje += "El precio del producto es invalido \n";

		if(categoria == null || categoria.equals(""))

			mensaje += "El categoria del producto es invalido \n";

		if(imagen==null){
			mensaje += "Por favor seleccione un imagen \n";

		}


		if(mensaje.equals("")){
			return true;
		}else{
			mostrarMensaje("Notificaci�n producto", "Datos invalidos", mensaje, AlertType.WARNING);
			return false;
		}
	}
	private void limpiarCasillasProductos() {

		txtNombreProducto.setText("");
		txtPrecioProducto.setText("");
		cbCategoria.setValue(null);
		ivImagen.setImage(null);



	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmaci�n");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}


	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
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


	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}


	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}


}
