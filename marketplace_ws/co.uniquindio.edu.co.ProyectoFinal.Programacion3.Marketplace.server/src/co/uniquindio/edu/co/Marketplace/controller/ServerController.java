package co.uniquindio.edu.co.Marketplace.controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import co.uniquindio.edu.co.Marketplace.MainServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController implements Runnable {
	Thread hiloServicio_servidor;

	//

	ServerSocket server;
	Socket socket;
	DataInputStream flujoEntrada;
	DataOutputStream flujoSalida;
	String texto;


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
		try{

			server = new ServerSocket(8081);

			while(true){
				

				socket = server.accept();
				
				flujoEntrada = new DataInputStream(socket.getInputStream());
				flujoSalida = new DataOutputStream(socket.getOutputStream());

				flujoSalida.writeUTF("oli todo bien?");
				texto=flujoEntrada.readUTF();

				
				informes.appendText("\n"+""+texto);

				
				flujoEntrada.close();
				flujoSalida.close();
				socket.close();




			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	


	public void setAplicacion(MainServer mainServer) {
		// TODO Auto-generated method stub

	}

}

