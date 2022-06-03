package co.uniquindio.edu.co.Marketplace.Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProductoExistenteException extends Exception{


	

	public ProductoExistenteException(String mensaje) {
		super(mensaje);
	}

}
