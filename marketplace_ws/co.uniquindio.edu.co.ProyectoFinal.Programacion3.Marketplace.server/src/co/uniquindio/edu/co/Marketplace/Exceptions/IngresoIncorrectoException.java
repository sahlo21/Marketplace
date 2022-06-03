package co.uniquindio.edu.co.Marketplace.Exceptions;

public class IngresoIncorrectoException extends Exception{

	/**
	 * excepcion generada cuando se ingresa incorrectamente
	 * @param mensaje
	 */

	public IngresoIncorrectoException(String mensaje) {
		super(mensaje);
	}

}
