package co.uniquindio.edu.co.Marketplace.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

//import javax.jws.WebParam.Mode;

import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.Exceptions.NombreCortoException;
import co.uniquindio.edu.co.Marketplace.Exceptions.PassIsEqualToUserException;
import co.uniquindio.edu.co.Marketplace.Exceptions.VendedorExistenteException;
import co.uniquindio.edu.co.Marketplace.Exceptions.VendedorNoSeleccionadoException;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.TipoUsuario;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MarketplaceAdminController implements Initializable {

	Aplicacion aplicacion;
	Vendedor vendedorSeleccionado;
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	// ModelFactoryController modelFactoryController= new ModelFactoryController();
	private ModelFactoryController modelFactoryController;
	/**
	 *
	 */
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML

	private TextField txtContrasenaVendedor;

	@FXML
	private TextField txtUsuarioVendedor;

	@FXML
	private TextField txtDireccionVendedor;

	@FXML
	private TableView<Vendedor> tableVendedores;

	@FXML
	private TableColumn<Vendedor, String> columnNombreVendedor;

	@FXML
	private TextField txtCedulaVendedor;

	@FXML
	private TableColumn<Vendedor, String> columnDireccionVendedor;

	@FXML
	private TextField txtNombreVendedor;

	@FXML
	private TextField txtApellidoVendedor;

	@FXML
	private TableColumn<Vendedor, String> columnCedulaVendedor;

	@FXML
	private TableColumn<Vendedor, String> columnUsuarioVendedor;

	@FXML
	private TableColumn<Vendedor, String> columnApellidoVendedor;

	@FXML
	private Label lblFecha;

	@FXML
	private Label lblHora;

	@FXML
	void cerrarSesionAction(ActionEvent event) {
		modelFactoryController.cerrarSesionAdmin(aplicacion);

	}

	@FXML
	void nuevoVendedorAction(ActionEvent event) {
		limpiarCasillasVendedores();
	}

	@FXML
	void agregarVendedorAction(ActionEvent event) {
		try {
			agregarVendedor();
		} catch (VendedorExistenteException | NombreCortoException | PassIsEqualToUserException e) {
			mostrarMensajeError(e.getMessage());
		}
	}

	@FXML
	void actualizarVendedorAction(ActionEvent event) {
		try {
			actualizarVendedor();
		} catch (VendedorNoSeleccionadoException | VendedorExistenteException e) {
			mostrarMensajeError(e.getMessage());
		}

	}

	@FXML
	void eliminarVendedorAction(ActionEvent event) {
		try {
			eliminarVendedor();
		} catch (VendedorNoSeleccionadoException e) {
			mostrarMensajeError(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelFactoryController = ModelFactoryController.getInstance();

		lblFecha.setText(lblFecha.getText() + LocalDate.now(Clock.systemDefaultZone()));
		lblHora.setText(lblHora.getText() + LocalTime.now());

		columnNombreVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nombre"));
		columnApellidoVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("apellidos"));
		columnCedulaVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("cedula"));
		columnUsuarioVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("usuario"));
		columnDireccionVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("direccion"));

		tableVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			vendedorSeleccionado = newSelection;

			mostrarInformacionVendedor(vendedorSeleccionado);

		});
	}

	public ObservableList<Vendedor> getListaVendedorData() {

		listaVendedoresData.addAll(modelFactoryController.obtenerVendedor());

		return listaVendedoresData;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		tableVendedores.getItems().clear();
		tableVendedores.setItems(getListaVendedorData());
	}

	private void mostrarInformacionVendedor(Vendedor vendedorSeleccionado) {

		if (vendedorSeleccionado != null) {
			txtNombreVendedor.setText(vendedorSeleccionado.getNombre());
			txtApellidoVendedor.setText(vendedorSeleccionado.getApellidos());
			txtUsuarioVendedor.setText(vendedorSeleccionado.getUsuario());
			txtContrasenaVendedor.setText(vendedorSeleccionado.getContrasena());
			txtCedulaVendedor.setText(vendedorSeleccionado.getCedula());
			txtDireccionVendedor.setText(vendedorSeleccionado.getDireccion());

		}

	}

	private void limpiarCasillasVendedores() {

		txtNombreVendedor.setText("");
		txtApellidoVendedor.setText("");
		txtUsuarioVendedor.setText("");
		txtContrasenaVendedor.setText("");
		txtCedulaVendedor.setText("");
		txtDireccionVendedor.setText("");
		txtNombreVendedor.setText("");

	}

	/**
	 * metodo para agregar un nuevo vendedor
	 * 
	 * @throws NombreCortoException
	 * @throws VendedorExistenteException
	 * @throws PassIsEqualToUserException
	 */

	private void agregarVendedor() throws NombreCortoException, VendedorExistenteException, PassIsEqualToUserException {
		String nombre = txtNombreVendedor.getText();
		String apellidos = txtApellidoVendedor.getText();
		String usuario = txtUsuarioVendedor.getText();
		String contrasena = txtContrasenaVendedor.getText();
		String cedula = txtCedulaVendedor.getText();
		String direccion = txtDireccionVendedor.getText();
		TipoUsuario tipoUsuario = TipoUsuario.VENDEDOR;

		if (datosValidosVendedor(nombre, apellidos, usuario, contrasena, cedula, direccion) == true) {
			if (nombre.length() <= 2) {
				Persistencia.guardarExceptionsLog("NombreCortoException", 3, "Agregar vendedor",
						modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());
				throw new NombreCortoException("El nombre del  estudiante es muy corto");

			}

			if (usuario.equals(contrasena)) {
				Persistencia.guardarExceptionsLog("PassIsEqualToUserException", 3, "Agregar vendedor",
						modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

				throw new PassIsEqualToUserException(
						"Por su seguridad el nombre de usuario y la contrasenia no pueden ser iguales");
			}
			if (modelFactoryController.obtenerVendedor().size() <10) {

				Vendedor vendedor = modelFactoryController.crearVendedor(nombre, apellidos, usuario, contrasena, cedula,
						direccion);

				if (vendedor != null) {

					listaVendedoresData.add(vendedor);
					limpiarCasillasVendedores();
					mostrarMensaje("Notificacion Vendedor", null, "El vendedor se ha creado con exito",
							AlertType.INFORMATION);

				} else {
					Persistencia.guardarExceptionsLog("VendedorExistenteException", 3, "Agregar vendedor",
							modelFactoryController.getAdmin().getNombre(),
							modelFactoryController.getAdmin().getCedula());

					throw new VendedorExistenteException("El vendedor: " + cedula + " ya se encuentra registrado");

				}
			} else {
				mostrarMensajeError("No se pueden agregar mas vendedores");
			}
		}

	}

	/**
	 * metodo para actualizar un vendedor
	 * 
	 * @throws VendedorNoSeleccionadoException
	 * @throws VendedorExistenteException
	 */

	private void actualizarVendedor() throws VendedorNoSeleccionadoException, VendedorExistenteException {

		String nombre = txtNombreVendedor.getText();
		String apellidos = txtApellidoVendedor.getText();
		String usuario = txtUsuarioVendedor.getText();
		String contrasena = txtContrasenaVendedor.getText();
		String cedula = txtCedulaVendedor.getText();
		String direccion = txtDireccionVendedor.getText();

		boolean flagcProductoActualizado = false;

		if (vendedorSeleccionado != null) {
			if (mostrarMensajeConfirmacion("Desea actualizar a: " + vendedorSeleccionado.getNombre())) {

				if (datosValidosVendedor(nombre, apellidos, usuario, contrasena, cedula, direccion)) {

					flagcProductoActualizado = modelFactoryController.actualizarVendedor(
							vendedorSeleccionado.getCedula(), nombre, apellidos, usuario, contrasena, cedula,
							direccion);
					if (flagcProductoActualizado == true) {

						tableVendedores.refresh();

						mostrarMensaje("Notificacion Vendedor", null, "El vendedor se ha actualizado con Exito",
								AlertType.INFORMATION);
					} else {
						Persistencia.guardarExceptionsLog("VendedorExistenteException", 3, "Actualizar vendedor",
								modelFactoryController.getAdmin().getNombre(),
								modelFactoryController.getAdmin().getCedula());

						throw new VendedorExistenteException("El vendedor: " + cedula + " ya se encuentra registrado");

					}
				} else {

					mostrarMensaje("Notificaciï¿½n Vendedor", "Vendedor no ha sido actualizado", null, AlertType.ERROR);
				}

			}

		} else {
			Persistencia.guardarExceptionsLog("VendedorNoSeleccionadoException", 3, "Actualizar vendedor",
					modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

			throw new VendedorNoSeleccionadoException("Vendedor no seleccionado\\nDebe seleccionar un Vendedor");
		}

	}

	/**
	 * metodo que elimina a un vendedor de la lista de vendedores
	 * 
	 * @throws VendedorNoSeleccionadoException
	 */
	private void eliminarVendedor() throws VendedorNoSeleccionadoException {

		boolean flagVendedorEliminado = false;

		if (vendedorSeleccionado != null) {

			if (mostrarMensajeConfirmacion("Desea eliminar a: " + vendedorSeleccionado.getNombre())) {
				flagVendedorEliminado = modelFactoryController.eliminarVendedor(vendedorSeleccionado.getCedula());

				if (flagVendedorEliminado == true) {

					listaVendedoresData.remove(vendedorSeleccionado);
					vendedorSeleccionado = null;
					tableVendedores.getSelectionModel().clearSelection();
					limpiarCasillasVendedores();
					mostrarMensaje("Notificaciï¿½n Vendedor", "Vendedor eliminado",
							"El vendedor se ha eliminado con Exito", AlertType.INFORMATION);

				} else {
					mostrarMensaje("Notificacion Vendedor", "Vendedor no eliminado", "El vendedor no  existe",
							AlertType.ERROR);

				}
			}

		} else {
			Persistencia.guardarExceptionsLog("VendedorNoSeleccionadoException", 3, "Eliminar vendedor",
					modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

			throw new VendedorNoSeleccionadoException("Vendedor no seleccionado\\nDebe seleccionar un vendedor");
		}

	}

	/**
	 * verificacion de credenciales
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param usuario
	 * @param contrasena
	 * @param cedula
	 * @param direccion
	 * @return
	 */
	private boolean datosValidosVendedor(String nombre, String apellidos, String usuario, String contrasena,
			String cedula, String direccion) {

		String mensaje = "";

		if (nombre == null || nombre.equals(""))
			mensaje += "El nombre del vendedor es invalido \n";

		if (apellidos == null || apellidos.equals(""))
			mensaje += "Los apellidos del vendedor es invalido \n";

		if (usuario == null || usuario.equals(""))
			mensaje += "El usuario del vendedor es invalido \n";

		if (contrasena == null || contrasena.equals(""))
			mensaje += "La contrasenia del vendedor es invalida \n";

		if (direccion == null || direccion.equals(""))
			mensaje += "La direccion del vendedor es invalida \n";

		if (mensaje.equals("")) {
			return true;
		} else {
			mostrarMensaje("Notificacion vendedor", "Datos invalidos", mensaje, AlertType.WARNING);
			return false;
		}
	}

	public static boolean isNumericDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNumericInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
	// -------------------------------------------METODOS
	// ESTADISTICAS----------------------------------------------------------------

	@FXML
	void mostrarTop10Action(ActionEvent event) {
		mostrarTop10();
	}

	@FXML
	void mostrarProductosVenderdorAction(ActionEvent event) {
		mostrarProductosVenderdor();
	}

	@FXML
	void mostrarContactosVenderdorAction(ActionEvent event) {
		mostrarContactosVenderdorAction();
	}

	@FXML
	void mostrarProductosFechaAction(ActionEvent event) {

	}

	@FXML
	void mostrarMensajesVendedoresAction(ActionEvent event) {

	}
	// ----------------------------------Exportar
	// estaditicas-----------------------------

	@FXML
	void exportarMegustasStatsAction(ActionEvent event) {
		crearArchivoMeGustas();
	}

	@FXML
	void exportarProductosVendedorStatsAction(ActionEvent event) {
		crearArchivoProductos();
	}

	@FXML
	void exportarContactosStatsAction(ActionEvent event) {
		crearArchivoContacto();
	}

	@FXML
	void exportarProductosFechaStatsAction(ActionEvent event) {

	}

	@FXML
	void exportarMensajesStatsAction(ActionEvent event) {

	}

	private static void crearArchivoMeGustas() {

		String rutaArchivo, formato, nombre;
		int edad, peso;
		double altura;
		ArrayList<String> listaDatosSalida = new ArrayList<String>();

		listaDatosSalida.add("Linea 1");
		listaDatosSalida.add("Linea 2");
		listaDatosSalida.add("Linea 3");
		listaDatosSalida.add("Linea 4");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaPublicacion = sdf.format(new Date());

		rutaArchivo = "src/co/uniquindio/edu/co/Marketplace/resources/reporteMeGustas.txt";

		formato = "Reporte de estadistica me gustas\r\n" + "Fecha: %s"
				+ "Reporte realizado por: <Nombre de usuario>\r\n" + "\r\n"
				+ "Información del reporte: <es el contenido del reporte>\r\n";

		nombre = "algo";
		edad = 21;
		altura = 1.73;
		peso = 50;

		try {

			Persistencia.almacenarDatos(rutaArchivo, formato, nombre, edad, altura, peso);
			// Persistencia.escribirArchivo(rutaArchivo, listaDatosSalida, false);
			JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void crearArchivoProductos() {

		String rutaArchivo, formato, nombre;
		int edad, peso;
		double altura;
		ArrayList<String> listaDatosSalida = new ArrayList<String>();

		listaDatosSalida.add("Linea 1");
		listaDatosSalida.add("Linea 2");
		listaDatosSalida.add("Linea 3");
		listaDatosSalida.add("Linea 4");

		rutaArchivo = "src/co/uniquindio/edu/co/Marketplace/resources/reporteProductos.txt";

		formato = "El estudiante %s de edad %d y altura %2.2f es apto para jugar %d\n";
		nombre = "algo";
		edad = 21;
		altura = 1.73;
		peso = 50;

		try {

			Persistencia.almacenarDatos(rutaArchivo, formato, nombre, edad, altura, peso);
			// Persistencia.escribirArchivo(rutaArchivo, listaDatosSalida, false);
			JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void crearArchivoContacto() {

		String rutaArchivo, formato, nombre;
		int edad, peso;
		double altura;
		ArrayList<String> listaDatosSalida = new ArrayList<String>();

		listaDatosSalida.add("Linea 1");
		listaDatosSalida.add("Linea 2");
		listaDatosSalida.add("Linea 3");
		listaDatosSalida.add("Linea 4");

		rutaArchivo = "src/co/uniquindio/edu/co/Marketplace/resources/reporteContacto.txt";

		formato = "El estudiante %s de edad %d y altura %2.2f es apto para jugar %d\n";
		nombre = "algo";
		edad = 21;
		altura = 1.73;
		peso = 50;

		try {

			Persistencia.almacenarDatos(rutaArchivo, formato, nombre, edad, altura, peso);
			// Persistencia.escribirArchivo(rutaArchivo, listaDatosSalida, false);
			JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * muestra el top10 de productos con mas MeGusta
	 */
	private void mostrarTop10() {

		Stage stage = new Stage();
		stage.setTitle("Me gustas por producto");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Top  Me Gusta");

		yAxis.setLabel("Me Gustas");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("TOP10 MeGustas");

		for (Producto producto : modelFactoryController.getListaMuro()) {

			series1.getData().add(new XYChart.Data(producto.getNombre(), producto.getListaMeGustas().size()));
		}

		Scene scene = new Scene(bc, 800, 600);
		bc.getData().addAll(series1);

		stage.setScene(scene);

		stage.showAndWait();

	}

	/**
	 * muestra las publicaciones del vendedor estadisticamente
	 */
	private void mostrarProductosVenderdor() {

		Stage stage = new Stage();
		stage.setTitle("Productos por vendedor");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Productos por vendedor");

		yAxis.setLabel("Productos");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Productos por vendedor");

		for (Vendedor vendedor : modelFactoryController.obtenerVendedor()) {

			series1.getData().add(new XYChart.Data(vendedor.getNombre(), vendedor.getListaProductos().size()));
		}

		Scene scene = new Scene(bc, 800, 600);
		bc.getData().addAll(series1);

		stage.setScene(scene);

		stage.showAndWait();

	}

	/**
	 * mueestra los contactos que tiene el vendedor
	 */
	private void mostrarContactosVenderdorAction() {

		Stage stage = new Stage();
		stage.setTitle("Contactos por vendedor");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Contactos por vendedor");

		yAxis.setLabel("Contactos");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Contactos por vendedor");

		for (Vendedor vendedor : modelFactoryController.obtenerVendedor()) {

			series1.getData().add(new XYChart.Data(vendedor.getNombre(), vendedor.getListaContactos().size()));
		}

		Scene scene = new Scene(bc, 800, 600);
		bc.getData().addAll(series1);

		stage.setScene(scene);

		stage.showAndWait();

	}

	private void m(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("contactos por vendedor");
		stage.setWidth(500);
		stage.setHeight(500);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("raul", 13),
				new PieChart.Data("juan", 25), new PieChart.Data("luisa", 10), new PieChart.Data("pablo", 22),
				new PieChart.Data("nicolas", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Contactos");

		((Group) scene.getRoot()).getChildren().add(chart);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * muestra graficamente los productos por mes
	 * 
	 * @param stage
	 */
	private void mostrarProductosPorMes(Stage stage) {
		stage.setTitle("Line Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Mes");

		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Monitoreo de productos publicados por meses:");

		XYChart.Series series = new XYChart.Series();
		series.setName("");

		series.getData().add(new XYChart.Data("Jan", 3));
		series.getData().add(new XYChart.Data("Feb", 14));
		series.getData().add(new XYChart.Data("Mar", 15));
		series.getData().add(new XYChart.Data("Apr", 24));
		series.getData().add(new XYChart.Data("May", 34));

		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().add(series);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * muestra graficamente los mensajes entre usuarios
	 * 
	 * @param stage
	 */
	private void mostrarMensajesEntreUsuarios(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Mensajes entre 2 vendedores");
		stage.setWidth(500);
		stage.setHeight(500);

		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("vendedor2", 13), new PieChart.Data("vendedor1", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Mensajes enviados entre 2 vendedores");

		((Group) scene.getRoot()).getChildren().add(chart);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * metodo que muestra la estadistica grafica de las publicaciones hechas por
	 * usuario
	 * 
	 * @param stage
	 */
	public void mostrarPublicacionesPorUsuario(Stage stage) {
		String usuario = "";
		stage.setTitle("Productos publicados");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("");

		yAxis.setLabel("Publicaciones");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("usuario:");
		series1.getData().add(new XYChart.Data(usuario, 10));

		Scene scene = new Scene(bc, 800, 600);
		bc.getData().addAll(series1);
		stage.setScene(scene);
		stage.show();
	}

}
