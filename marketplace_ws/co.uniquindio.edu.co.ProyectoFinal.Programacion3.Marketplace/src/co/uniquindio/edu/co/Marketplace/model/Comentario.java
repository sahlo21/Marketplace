package co.uniquindio.edu.co.Marketplace.model;

import java.io.Serializable;
import java.util.Date;

public class Comentario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String texto;
	String fecha;
	String userVendedor;
	String id;

	/**
	 * @param texto
	 * @param fecha
	 * @param vendedor
	 */
	public Comentario(String texto, String fecha, String userVendedor) {
		super();
		this.texto = texto;
		this.fecha = fecha;
		this.userVendedor = userVendedor;
	}


	public Comentario() {
		
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the userVendedor
	 */
	public String getUserVendedor() {
		return userVendedor;
	}


	/**
	 * @param userVendedor the userVendedor to set
	 */
	public void setUserVendedor(String userVendedor) {
		this.userVendedor = userVendedor;
	}


	@Override
	public String toString() {
		return "Comentario [texto=" + texto + ", fecha=" + fecha + ", userVendedor=" + userVendedor + ", id=" + id
				+ "]";
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((userVendedor == null) ? 0 : userVendedor.hashCode());
		return result;
	}


}
