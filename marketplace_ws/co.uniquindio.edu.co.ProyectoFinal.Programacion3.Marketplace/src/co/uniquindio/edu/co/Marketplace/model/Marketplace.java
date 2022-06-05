package co.uniquindio.edu.co.Marketplace.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Marketplace implements Serializable{
	
	ArrayList<Producto> listaMuro = new ArrayList<>();


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nombre;
	String nit;
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Administrador> listaAdministradores = new ArrayList<Administrador>();

	/**
	 * @param nombre
	 * @param nit
	 */
	public Marketplace(String nombre, String nit) {
		super();
		this.nombre = nombre;
		this.nit = nit;
	}
	public Marketplace(){
		
	}

	/**
	 * @return the listaMuro
	 */
	public ArrayList<Producto> getListaMuro() {
		return listaMuro;
	}
	/**
	 * @param listaMuro the listaMuro to set
	 */
	public void setListaMuro(ArrayList<Producto> listaMuro) {
		this.listaMuro = listaMuro;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}


	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}


	/**
	 * @return the listaVendedores
	 */
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}


	/**
	 * @param listaVendedores the listaVendedores to set
	 */
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}


	/**
	 * @return the listaAdministradores
	 */
	public ArrayList<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}
	/**
	 * @param listaAdministradores the listaAdministradores to set
	 */
	public void setListaAdministradores(ArrayList<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Marketplace [listaMuro=" + listaMuro + ", nombre=" + nombre + ", nit=" + nit + ", listaVendedores="
				+ listaVendedores + ", listaAdministradores=" + listaAdministradores + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nit == null) ? 0 : nit.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marketplace other = (Marketplace) obj;
		if (nit == null) {
			if (other.nit != null)
				return false;
		} else if (!nit.equals(other.nit))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public Vendedor crearVendedor(String nombre, String apellidos, String usuario, String contrasena, String cedula, String direccion) {
		Vendedor vendedor = null;

		vendedor = buscarVendedor(cedula);

		if(vendedor == null){
			


			vendedor = new Vendedor(nombre,  apellidos,  usuario,  contrasena, cedula,  direccion);


			vendedor.setNombre(nombre);
			vendedor.setApellidos(apellidos);
			vendedor.setCedula(cedula);
			vendedor.setUsuario(usuario);
			vendedor.setContrasena(contrasena);
			vendedor.setDireccion(direccion);
			

			
			getListaVendedores().add(vendedor);
			System.out.println(getListaVendedores());
			return vendedor;
		}else{
			return null;

		}
	}

	public boolean eliminarVendedor(String codigoActual) {
		Vendedor vendedor = null;

		vendedor = buscarVendedor(codigoActual);

		if(vendedor != null){
			getListaVendedores().remove(vendedor);

			return true;
		}else{

			return false;

		}

	}






	public boolean actualizarVendedor(String codigoActual, String nombre, String apellidos, String usuario, String contrasena, String cedula, String direccion) {
		Vendedor vendedor = null;

		vendedor = buscarVendedor(codigoActual);

		if(vendedor != null){


			vendedor.setNombre(nombre);
			vendedor.setApellidos(apellidos);
			vendedor.setCedula(cedula);
			vendedor.setUsuario(usuario);
			vendedor.setContrasena(contrasena);
			vendedor.setDireccion(direccion);

			return true;
		}else{
			return false;

		}
	}

	private Vendedor buscarVendedor(String cedulaActual) {

		for (Vendedor vendedor : listaVendedores) {

			if(vendedor.getCedula().equals(cedulaActual)){
				return vendedor;
			}
		}
		return null;

	}
	public Usuario ingresar(String Nomusuario, String contrasenia, TipoUsuario tipoUsuario) {
		Usuario usuario = null;

		switch (tipoUsuario) {
		case VENDEDOR:
			for (Vendedor vendedor : listaVendedores) {
				if (vendedor.getUsuario().equals(Nomusuario) && vendedor.getContrasena().equals(contrasenia)) {
					usuario = vendedor;
				}
			}

			break;
		case ADMINISTRADOR:
			for (Administrador administrador : listaAdministradores) {
				if (administrador.getUsuario().equals(Nomusuario)
						&& administrador.getContrasena().equals(contrasenia)) {
					return administrador;
				}
				return usuario = administrador;
			}
			break;
		default:
			break;
		}
		return usuario;
	}


}
