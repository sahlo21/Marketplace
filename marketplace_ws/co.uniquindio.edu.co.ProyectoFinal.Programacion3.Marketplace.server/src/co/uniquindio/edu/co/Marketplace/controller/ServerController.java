package co.uniquindio.edu.co.Marketplace.controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import co.uniquindio.edu.co.Marketplace.MainServer;
import co.uniquindio.edu.co.Marketplace.model.Administrador;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Usuario;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController implements Runnable {
	Thread hiloServicio_servidor;
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Administrador> listaAdministradores = new ArrayList<>();
	//

	ServerSocket server;
	Socket socket;
	DataInputStream flujoEntrada;
	DataOutputStream flujoSalida;
	ObjectOutputStream flujoSalidaObject;
	ObjectInputStream flujoEntradaObject;
	String texto;
	Marketplace market;


	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	@FXML
	private TextArea informes;

	boolean runHilo;





	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {

		informes.setEditable(false);
		hiloServicio_servidor = new Thread(this);
		hiloServicio_servidor.setDaemon(true);


		hiloServicio_servidor.start();

	}
	@FXML
	void off(ActionEvent event) {
		Platform.exit();
	}






	/**
	 * @return the runHilo
	 */
	public boolean isRunHilo() {
		return runHilo;
	}



	/**
	 * @param runHilo the runHilo to set
	 */
	public void setRunHilo(boolean runHilo) {
		this.runHilo = runHilo;
	}





	@Override
	public void run() {
		market=Persistencia.cargarRecursoMarketplaceXML();

		try{

			server = new ServerSocket(8081);
			System.out.println("Esperando cliente");

			while(true){


				socket = server.accept();

				flujoEntrada = new DataInputStream(socket.getInputStream());
				flujoSalida = new DataOutputStream(socket.getOutputStream());
				flujoSalidaObject = new ObjectOutputStream(socket.getOutputStream());
				flujoEntradaObject = new ObjectInputStream(socket.getInputStream());

				int caso;
				caso=flujoEntrada.readInt();
				System.out.println("caso"+caso);
				switch (caso) {
				case 1:
					String user=flujoEntrada.readUTF();
					String password=flujoEntrada.readUTF();
					Usuario usuarioObtenido=ingreso(user, password);
					if (usuarioObtenido instanceof Vendedor) {
						Vendedor vendedor = (Vendedor) usuarioObtenido;
						flujoSalida.writeUTF(vendedor.getCedula());
						System.out.println(vendedor.getCedula());


					} else if (usuarioObtenido instanceof Administrador) {
						Administrador admin = (Administrador) usuarioObtenido;

						flujoSalida.writeUTF(admin.getCedula());
						System.out.println(admin.getCedula());
					}else {
						System.out.println("Usuario invalido");

					}
					flujoSalida.writeUTF("false");
					System.out.println("llego?");
					if(usuarioObtenido!=null){
						texto="El usuario: "+user+" ha ingresado";
					}
					else{
						texto="Ingreso fallido de: "+user;
					}

					informes.appendText("\n"+"  "+texto);
					break;
				case 2:
					texto=flujoEntrada.readUTF();


					System.out.println(market);

					flujoSalidaObject.writeObject(market);


					informes.appendText("\n"+""+texto);

					break;

				default:

					break;
				}



				flujoEntrada.close();
				flujoSalida.close();
				socket.close();




			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Usuario ingreso(String usuario, String contrasena) {
		Usuario user = null;
		listaVendedores.clear();
		listaVendedores.addAll(obtenerVendedor());
		listaAdministradores.clear();
		listaAdministradores.addAll(obtenerAdministrador());
		for (Vendedor vendedor : listaVendedores) {
			if (usuario.equals(vendedor.getUsuario()) && contrasena.equals(vendedor.getContrasena())) {
				//				guardarRegistroLogin("Inicio de sesi�n correcto", 1, "Iniciar de sesi�n vendedor", vendedor.getNombre(),
				//						vendedor.getCedula());
				return vendedor;
			}
		}
		for (Administrador administrador : listaAdministradores) {
			if (administrador.getUsuario().equals(usuario) && administrador.getContrasena().equals(contrasena)) {

				return administrador;
			} else {
				//				guardarRegistroLogin("Inicio de sesi�n fallido", 1, "Iniciar de sesi�n ", usuario, "No aplica");
			}
		}
		return user;
	}

	public ArrayList<Vendedor> obtenerVendedor() {

		return this.market.getListaVendedores();

	}

	public ArrayList<Administrador> obtenerAdministrador() {

		return this.market.getListaAdministradores();

	}

	public void setAplicacion(MainServer mainServer) {
		// TODO Auto-generated method stub

	}

}

