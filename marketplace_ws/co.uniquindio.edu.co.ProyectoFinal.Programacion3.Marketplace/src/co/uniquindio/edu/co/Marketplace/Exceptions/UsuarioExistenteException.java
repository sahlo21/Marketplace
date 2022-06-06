package co.uniquindio.edu.co.Marketplace.Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UsuarioExistenteException extends Exception{


	/**
	 * 
	 * cuando el usuario ya esta registrado 
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertType
	 */

	public UsuarioExistenteException(String titulo, String header, String contenido, AlertType alertType) {
		super(contenido);
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

}
