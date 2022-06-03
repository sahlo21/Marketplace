package co.uniquindio.edu.co.Marketplace.model;

import java.time.format.DateTimeFormatter;

public class Mensaje {
	String texto;
	String fecha;
	String nombreVendedor;
	
	Vendedor vendedorRemitente ;
	/**
	 * @param texto
	 * @param fecha
	 * @param vendedorReceptor
	 * @param vendedorRemitente
	 */
	public Mensaje(String texto, String fecha, Vendedor vendedorRemitente, String nombreVendedor) {
		super();
		this.texto = texto;
		this.fecha = fecha;
		this.nombreVendedor=nombreVendedor;
		this.vendedorRemitente = vendedorRemitente;
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	/**
	 * @return the nombreVendedor
	 */
	public String getNombreVendedor() {
		return nombreVendedor;
	}
	/**
	 * @param nombreVendedor the nombreVendedor to set
	 */
	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}
	/**
	 * @return the vendedorRemitente
	 */
	public Vendedor getVendedorRemitente() {
		return vendedorRemitente;
	}
	/**
	 * @param vendedorRemitente the vendedorRemitente to set
	 */
	public void setVendedorRemitente(Vendedor vendedorRemitente) {
		this.vendedorRemitente = vendedorRemitente;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mensaje [texto=" + texto + ", fecha=" + fecha + ", vendedorRemitente=" + vendedorRemitente + "]";
	}
	
}
