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

public class LoginController2 implements Initializable{




	@FXML
	private TextField txtUsuarioIngreso;

	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonCargar;



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





	public LoginController2() {

	}





	@FXML
	void cbCondiciones(ActionEvent event) {

	}

	@FXML
	private Label wrongLogIn;





	public void iniciarSesionAction(ActionEvent event) throws IOException{

		try {
			inicioSesion();
		} catch ( NoSeleccionTerminosException | IngresoIncorrectoException e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}

	}
	public void cargarMarketplaceServerAction(ActionEvent event) {

		modelFactoryController.cargarMarketplaceServer();
		buttonCargar.setDisable(true);

	}


	void inicioSesion() throws IOException, NoSeleccionTerminosException, IngresoIncorrectoException {
		if (!estadoCargareServer) {
			mostrarMensajeInformacion("Le sugerimos cargar el programa para un mejor funcionamiento");
		}
		String usuario = txtUsuarioIngreso.getText().toString();
		String contrasena = txtContrasenaIngreso.getText().toString();

		String codeuUsuarioObtenido ="";
		Usuario usuarioObtenido=null;
		try{
			miSocket =  new Socket("localhost", 8081);

			System.out.println("Conectado cliente");


			flujoSalidaObject = new ObjectOutputStream(miSocket.getOutputStream());
			flujoEntradaObject = new ObjectInputStream(miSocket.getInputStream());
			flujoSalidaData = new DataOutputStream(miSocket.getOutputStream());
			flujoEntradaData = new DataInputStream(miSocket.getInputStream());

			//Se reciben los datos que vienen desde el servidor



			flujoSalidaData.writeInt(1);
			flujoSalidaData.writeUTF(usuario);
			flujoSalidaData.writeUTF(contrasena);

			codeuUsuarioObtenido= flujoEntradaData.readUTF();
		

			System.out.println(modelFactoryController.obtenerAdministrador());

			for (Vendedor vendedor : modelFactoryController.obtenerVendedor()) {
				if (vendedor.getCedula().equals(codeuUsuarioObtenido)) {
					
					
//					usuarioObtenido = (Usuario) vendedor;
//					System.out.println(usuarioObtenido);

					usuarioObtenido=vendedor;
					System.out.println(usuarioObtenido);
				}
				
			}
			for (Administrador admin : modelFactoryController.obtenerAdministrador()) {
				if (admin.getCedula().equals(codeuUsuarioObtenido)) {
//					usuarioObtenido = (Usuario) admin;
//					System.out.println(usuarioObtenido);

					usuarioObtenido=admin;
					System.out.println(usuarioObtenido);
				}
			}


			flujoEntradaData.close();
			flujoSalidaData.close();
			flujoEntradaObject.close();
			flujoSalidaObject.close();
			miSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		alert.setTitle("Información");
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


