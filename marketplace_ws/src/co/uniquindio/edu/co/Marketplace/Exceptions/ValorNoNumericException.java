package co.uniquindio.edu.co.Marketplace.Exceptions;

public class ValorNoNumericException extends Exception {


	/**
	 * excepcion generada cuando el valor ingresado
	 * para un producto no es de tipo numerico
	 *
	 * @param mensaje
	 */

	public ValorNoNumericException(String mensaje) {
		super(mensaje);
	}
}
