package co.uniquindio.edu.co.Marketplace.model;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cedula;
	




	/**
	 * @param nombre
	 * @param apellidos
	 * @param usuario
	 * @param contrasena
	 */
	public Administrador(String nombre, String apellidos, String usuario, String contrasena) {
		super(nombre, apellidos, usuario, contrasena);
		// TODO Auto-generated constructor stub
	}



	public Administrador(){
		
	}



	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}



	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
