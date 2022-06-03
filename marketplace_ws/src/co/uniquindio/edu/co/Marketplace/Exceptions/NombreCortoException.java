package co.uniquindio.edu.co.Marketplace.Exceptions;

public class NombreCortoException extends Exception{

	/**
	 * excepcion generada cuando se ingresa un nombre muy corto
	 * @param mensaje
	 */

	public NombreCortoException(String mensaje) {
		super(mensaje);
	}

}
