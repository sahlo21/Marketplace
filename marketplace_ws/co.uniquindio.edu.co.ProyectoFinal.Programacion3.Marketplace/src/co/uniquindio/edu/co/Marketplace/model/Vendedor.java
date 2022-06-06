package co.uniquindio.edu.co.Marketplace.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class Vendedor extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String cedula;
	String direccion;
	String id;
	ArrayList<Producto> listaProductos = new ArrayList<>();
	ArrayList<Vendedor> listaContactos = new ArrayList<>();
	ArrayList<Mensaje> listaMensajes = new ArrayList<>();
	ArrayList<Solicitud> listaSolicitud = new ArrayList<>();

	public Vendedor() {

	}

	/**
	 * @param nombre
	 * @param apellidos
	 * @param usuario
	 * @param contrasena
	 * @param cedula
	 * @param direccion
	 * 
	 */
	public Vendedor(String nombre, String apellidos, String usuario, String contrasena, String cedula,
			String direccion) {
		super(nombre, apellidos, usuario, contrasena);
		this.cedula = cedula;
		this.direccion = direccion;

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

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the listaProductos
	 */
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos the listaProductos to set
	 */
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * @return the listaContactos
	 */
	public ArrayList<Vendedor> getListaContactos() {
		return listaContactos;
	}

	/**
	 * @param listaContactos the listaContactos to set
	 */
	public void setListaContactos(ArrayList<Vendedor> listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * @return the listaMensajes
	 */
	public ArrayList<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	/**
	 * @param listaMensajes the listaMensajes to set
	 */
	public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	/**
	 * @return the listaSolicitud
	 */
	public ArrayList<Solicitud> getListaSolicitud() {
		return listaSolicitud;
	}

	/**
	 * @param ListaSolicitud to set
	 */
	public void setListaSolicitud(ArrayList<Solicitud> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}

	@Override
	public String toString() {
		return "Vendedor [cedula=" + cedula + ", direccion=" + direccion + ", id=" + id + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}

	public void imprimirVendedor() {
		for (Producto producto : listaProductos) {
			System.out.println(producto.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((listaContactos == null) ? 0 : listaContactos.hashCode());
		result = prime * result + ((listaMensajes == null) ? 0 : listaMensajes.hashCode());
		result = prime * result + ((listaProductos == null) ? 0 : listaProductos.hashCode());
		result = prime * result + ((listaSolicitud == null) ? 0 : listaSolicitud.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (listaContactos == null) {
			if (other.listaContactos != null)
				return false;
		} else if (!listaContactos.equals(other.listaContactos))
			return false;
		if (listaMensajes == null) {
			if (other.listaMensajes != null)
				return false;
		} else if (!listaMensajes.equals(other.listaMensajes))
			return false;
		if (listaProductos == null) {
			if (other.listaProductos != null)
				return false;
		} else if (!listaProductos.equals(other.listaProductos))
			return false;
		return true;
	}

	public Producto crearProducto(String nombre, Image imagen, Double precio, Estado estado, Categoria categoria,
			String fechaPublicacion, String codVendedor) {
		Producto producto = null;

		producto = buscarProducto(nombre);

		if (producto == null) {

			producto = new Producto(nombre, imagen, precio, estado, categoria, fechaPublicacion, codVendedor);

			producto.setNombre(nombre);
			producto.setImagen(imagen);
			;
			producto.setPrecio(precio);
			;
			producto.setEstado(estado);
			;
			producto.setCategoria(categoria);
			producto.setFechaPublicacion(fechaPublicacion);
			producto.setCodVendedor(codVendedor);

			getListaProductos().add(producto);
			System.out.println("lista pro: " + listaProductos);

			return producto;
		} else {
			return null;

		}
	}

	public boolean eliminarProducto(String nombreProducto) {
		Producto producto = null;

		producto = buscarProducto(nombreProducto);

		if (producto != null) {
			getListaProductos().remove(producto);
			return true;
		} else {

			return false;

		}

	}

	private Producto buscarProducto(String nombre) {

		for (Producto producto : listaProductos) {

			if (producto.getNombre().equals(nombre)) {
				return producto;
			}
		}
		return null;

	}

	public boolean actualizarProducto(String nombreActual, String nombre, Image imagen, Double precio, Estado estado,
			Categoria categoria, String fechaPublicacion) {

		Producto producto = null;

		producto = buscarProducto(nombreActual);

		if (producto != null) {

			producto.setNombre(nombre);
			producto.setImagen(imagen);
			;
			producto.setPrecio(precio);
			;
			producto.setEstado(estado);
			;
			producto.setCategoria(categoria);
			producto.setFechaPublicacion(fechaPublicacion);

			return true;
		} else {
			return false;

		}
	}

	public void crearMensaje(String textoMensaje, String fechaComentario, Vendedor vendedorRemitente) {

		Mensaje msj = null;

		msj = new Mensaje(textoMensaje, fechaComentario, vendedorRemitente, vendedorRemitente.getNombre());

		msj.setFecha(fechaComentario);
		msj.setTexto(textoMensaje);
		msj.setVendedorRemitente(vendedorRemitente);
		msj.setNombreVendedor(vendedorRemitente.getNombre() + " " + vendedorRemitente.getApellidos());

		getListaMensajes().add(msj);
		System.out.println("lista msj: " + listaMensajes);

	}

	public void aceptarSolicitud(Vendedor vendedorAsociado) {
		getListaContactos().add(vendedorAsociado);
		System.out.println("Lista socios: " + listaContactos);
	}

	public void eliminarSolicitud(Solicitud solicitud) {
		getListaSolicitud().remove(solicitud);
	}

	/**
	 * Se crea una nueva solicitud y se guarda en la lista de solicitudes del
	 * vendedor.
	 * 
	 * 
	 * @param vendedorSolicitud
	 * @param nombreSoli
	 * @param apellidoSoli
	 * @param codVendedorDestino
	 */

	public void crearSolicitud(Vendedor vendedorSolicitud, String nombreSoli, String apellidoSoli,
			String codVendedorDestino) {
		Solicitud soli = null;

		soli = new Solicitud(vendedorSolicitud, vendedorSolicitud.getNombre(), vendedorSolicitud.getApellidos(),
				codVendedorDestino);

		soli.setVendedorSolicitud(vendedorSolicitud);
		soli.setNombreSoli(vendedorSolicitud.getNombre());
		soli.setApellidoSoli(vendedorSolicitud.getApellidos());
		soli.setCodVendedorDestino(codVendedorDestino);

		getListaSolicitud().add(soli);
		System.out.println("lista soli: " + listaSolicitud);

	}

	public boolean validarSolicitudExistente(Vendedor vendedorSolicitud, Vendedor vendedorSelect) {
		Vendedor aux;
		for (Solicitud solicitud : vendedorSelect.getListaSolicitud()) {
			aux = solicitud.getVendedorSolicitud();
			if (aux.equals(vendedorSolicitud)) {
				return false;
			}
		}
		return true;
	}
}
