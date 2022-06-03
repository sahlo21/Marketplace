package co.uniquindio.edu.co.Marketplace.Exceptions;

public class PassIsEqualToUserException extends Exception {
	
	/**
	 * Excepcion generada cuando la contraseña es similar al nombre de usuario
	 * 
	 * @param mensaje
	 */

	public PassIsEqualToUserException(String mensaje) {
		super(mensaje);
	}

}
