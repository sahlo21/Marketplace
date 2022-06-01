package co.uniquindio.edu.co.Marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Optional;
import java.util.ResourceBundle;

//import javax.jws.WebParam.Mode;

import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.Exceptions.NombreCortoException;
import co.uniquindio.edu.co.Marketplace.Exceptions.PassIsEqualToUserException;
import co.uniquindio.edu.co.Marketplace.Exceptions.VendedorExistenteException;
import co.uniquindio.edu.co.Marketplace.Exceptions.VendedorNoSeleccionadoException;


import co.uniquindio.edu.co.Marketplace.model.TipoUsuario;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MarketplaceAdminController implements Initializable {

	Aplicacion aplicacion;
	Vendedor vendedorSeleccionado;
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	//	ModelFactoryController modelFactoryController= new ModelFactoryController();
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
	void actualizarVendedorAction(ActionEvent event)   {
		try{
			actualizarVendedor();
		}catch (VendedorNoSeleccionadoException | VendedorExistenteException e) {
			mostrarMensajeError(e.getMessage());
		}

	}

	@FXML
	void eliminarVendedorAction(ActionEvent event)   {
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

	private void agregarVendedor() throws NombreCortoException, VendedorExistenteException, PassIsEqualToUserException {
		String nombre = txtNombreVendedor.getText();
		String apellidos = txtApellidoVendedor.getText();
		String usuario = txtUsuarioVendedor.getText();
		String contrasena = txtContrasenaVendedor.getText();
		String cedula = txtCedulaVendedor.getText();
		String direccion = txtDireccionVendedor.getText();
		TipoUsuario tipoUsuario=TipoUsuario.VENDEDOR;

		if (datosValidosVendedor(nombre, apellidos, usuario, contrasena, cedula, direccion) == true) {
			if(nombre.length()<=2){
                Persistencia.guardarExceptionsLog("NombreCortoException", 3, "Agregar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());
				throw new NombreCortoException("El nombre del  estudiante es muy corto");

			}
			
			if (usuario.equals(contrasena)) {
                Persistencia.guardarExceptionsLog("PassIsEqualToUserException", 3, "Agregar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

				throw new PassIsEqualToUserException("Por su seguridad el nombre de usuario y la contrase�a no pueden ser iguales");
			}
			
			Vendedor vendedor = modelFactoryController.crearVendedor(nombre, apellidos, usuario, contrasena, cedula, direccion);

			if (vendedor != null) {

				listaVendedoresData.add(vendedor);
				limpiarCasillasVendedores();
				mostrarMensaje("Notificaci�n Vendedor", null, "El vendedor se ha creado con �xito",AlertType.INFORMATION);

			} else {
                Persistencia.guardarExceptionsLog("VendedorExistenteException", 3, "Agregar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

				throw new VendedorExistenteException("El vendedor: " + cedula + " ya se encuentra registrado");

			}
		}



	}

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

						flagcProductoActualizado = modelFactoryController.actualizarVendedor(vendedorSeleccionado.getCedula(),
								nombre, apellidos, usuario, contrasena, cedula, direccion);
						if (flagcProductoActualizado == true) {

							tableVendedores.refresh();

							mostrarMensaje("Notificaci�n Vendedor", null, "El vendedor se ha actualizado con �xito",
									AlertType.INFORMATION);
						}else {
			                Persistencia.guardarExceptionsLog("VendedorExistenteException", 3, "Actualizar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

							throw new VendedorExistenteException("El vendedor: " + cedula + " ya se encuentra registrado");

						}
					} else {

						mostrarMensaje("Notificaci�n Vendedor", "Vendedor no ha sido actualizado", null,
								AlertType.ERROR);
					}
				
			}

		} else {
            Persistencia.guardarExceptionsLog("VendedorNoSeleccionadoException", 3, "Actualizar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

			throw new VendedorNoSeleccionadoException("Vendedor no seleccionado\\nDebe seleccionar un Vendedor");
		}

	}

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
					mostrarMensaje("Notificaci�n Vendedor", "Vendedor eliminado",
							"El vendedor se ha eliminado con �xito", AlertType.INFORMATION);

				} else {
					mostrarMensaje("Notificaci�n Vendedor", "Vendedor no eliminado", "El vendedor no  existe",
							AlertType.ERROR);

				}
			}


		} else {
            Persistencia.guardarExceptionsLog("VendedorNoSeleccionadoException", 3, "Eliminar vendedor", modelFactoryController.getAdmin().getNombre(), modelFactoryController.getAdmin().getCedula());

			throw new VendedorNoSeleccionadoException("Vendedor no seleccionado\\nDebe seleccionar un vendedor");
		}

	}

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
			mensaje += "La contrase�a del vendedor es invalida \n";


		if (direccion == null || direccion.equals(""))
			mensaje += "La direcci�n del vendedor es invalida \n";

		if (mensaje.equals("")) {
			return true;
		} else {
			mostrarMensaje("Notificaci�n vendedor", "Datos invalidos", mensaje, AlertType.WARNING);
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
}
