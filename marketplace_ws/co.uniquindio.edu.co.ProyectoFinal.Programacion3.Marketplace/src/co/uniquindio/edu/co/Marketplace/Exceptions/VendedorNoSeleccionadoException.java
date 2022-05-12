package co.uniquindio.edu.co.Marketplace.Exceptions;

public class VendedorNoSeleccionadoException extends NullPointerException {

	public VendedorNoSeleccionadoException() {
		this("Usuario no seleccionado");
	}

	/**
	 * Excepcion Vendedor no Seleccionado
	 * 
	 * @param mensaje
	 */

	public VendedorNoSeleccionadoException(String mensaje) {
		super(mensaje);
	}

}
