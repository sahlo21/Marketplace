package co.uniquindio.edu.co.Marketplace.Exceptions;

public class PassIsEqualToUserException extends Exception {
	
	/**
	 * Excepcion generada cuando la contrase�a es similar al nombre de usuario
	 * 
	 * @param mensaje
	 */

	public PassIsEqualToUserException(String mensaje) {
		super(mensaje);
	}

}
