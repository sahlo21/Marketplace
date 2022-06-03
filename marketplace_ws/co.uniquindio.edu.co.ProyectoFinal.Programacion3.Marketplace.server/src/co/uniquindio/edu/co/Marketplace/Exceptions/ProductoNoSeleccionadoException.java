package co.uniquindio.edu.co.Marketplace.Exceptions;

public class ProductoNoSeleccionadoException extends NullPointerException {

	public ProductoNoSeleccionadoException() {
		this("Usuario no seleccionado");
	}

	/**
	 * Excepcion Vendedor no Seleccionado
	 * 
	 * @param mensaje
	 */

	public ProductoNoSeleccionadoException(String mensaje) {
		super(mensaje);
	}

}
