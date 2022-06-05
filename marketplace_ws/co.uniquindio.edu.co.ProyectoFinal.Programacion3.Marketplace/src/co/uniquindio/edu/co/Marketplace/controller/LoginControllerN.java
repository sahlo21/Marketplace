package co.uniquindio.edu.co.Marketplace.controller;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.Exceptions.IngresoIncorrectoException;
import co.uniquindio.edu.co.Marketplace.Exceptions.NoSeleccionTerminosException;
import co.uniquindio.edu.co.Marketplace.model.Administrador;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Usuario;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginControllerN implements Initializable{




	@FXML
	private TextField txtUsuarioIngreso;

	@FXML
	private Button buttonLogin;

	@FXML
	private TextField txtContrasenaIngreso;

	@FXML
	private CheckBox cbCondiciones;

	boolean flagLogin=false;
	char tipoLogin;
	Aplicacion aplicacion;
	private ModelFactoryController modelFactoryController; 
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	Socket miSocket;


	ObjectOutputStream flujoSalidaObject;
	ObjectInputStream flujoEntradaObject;

	DataOutputStream flujoSalidaData;
	DataInputStream flujoEntradaData;
	int contador = 0;
	boolean estadoCargareServer=false;
	private int flagCargar;

	@FXML
	private Button buttonCargar;
	public LoginControllerN() {


	}

	@FXML
	void cbCondiciones(ActionEvent event) {

	}

	@FXML
	private Label wrongLogIn;
	public void cargarMarketplaceServerAction(ActionEvent event) {

		modelFactoryController.cargarMarketplaceServer();
		buttonCargar.setDisable(true);

	}


	public void iniciarSesionAction(ActionEvent event) throws IOException{

		try {
			inicioSesion();
		} catch ( NoSeleccionTerminosException | IngresoIncorrectoException e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}

	}

	void inicioSesion() throws IOException, NoSeleccionTerminosException, IngresoIncorrectoException {

		String usuario = txtUsuarioIngreso.getText().toString();
		String contrasena = txtContrasenaIngreso.getText().toString();

		Usuario usuarioObtenido = null;

		usuarioObtenido = modelFactoryController.ingreso(usuario, contrasena);
		if(cbCondiciones.isSelected()){
			if (usuarioObtenido != null) {
				if (usuarioObtenido instanceof Vendedor) {
					Vendedor vendedor = (Vendedor) usuarioObtenido;
					mostrarMensajeInformacion("Bienvenido: "+vendedor.getNombre());
					modelFactoryController.setVendedorLogueado(vendedor);
					aplicacion.showVendedor();

				} else if (usuarioObtenido instanceof Administrador) {
					mostrarMensajeInformacion("Bienvenido administrador");
					aplicacion.showAdministrador();
				}else {
		            Persistencia.guardarExceptionsLog("IngresoIncorrectoException", 3, "Inicio de sesi�n", usuario, "No aplica");

					throw new IngresoIncorrectoException("Ha ingresado mal el usuario y/o contrase�a.");


				}


			} else {
				mostrarMensajeError("Ha ingresado mal el usuario y/o contrase�a.");		}

		}else{
            Persistencia.guardarExceptionsLog("NoSeleccionTerminosException", 3, "Inicio de sesi�n", usuario, "No aplica");

			throw new NoSeleccionTerminosException("Por favor, acepte los terminos y condiciones.");
		}

	}


	private boolean mostrarMensajeInformacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelFactoryController = ModelFactoryController.getInstance();

	}
	public void setAplicacion(Aplicacion mainAux, int flagCargar) {
		aplicacion= mainAux;
		this.flagCargar=flagCargar;
		System.out.println(this.flagCargar);
		if (flagCargar!=0) {
			buttonCargar.setDisable(true);

		}
	}






}


