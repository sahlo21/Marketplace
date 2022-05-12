package co.uniquindio.edu.co.Marketplace.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.DateFormatter;

import javafx.scene.image.Image;

public class Producto {

	String nombre;
	Image imagen;
	Double precio;
	Estado estado;
	Categoria categoria;
	String fechaPublicacion;
	String codVendedor;
	String id;
	ArrayList<MeGusta> listaMeGustas = new ArrayList<>();
	ArrayList<Comentario> listaComentarios = new ArrayList<>();

	/**
	 * @param nombre
	 * @param imagen
	 * @param precio
	 * @param estado
	 * @param categoria
	 * @param fechaPublicacion
	 */
	public Producto(String nombre, Image imagen, Double precio, Estado estado, Categoria categoria,
			String fechaPublicacion, String codVendedor) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.precio = precio;
		this.estado = estado;
		this.categoria = categoria;
		this.fechaPublicacion = fechaPublicacion;
	}
	public Producto(){
		super();

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
	 * @return the codVendedor
	 */
	public String getCodVendedor() {
		return codVendedor;
	}

	/**
	 * @param codVendedor the codVendedor to set
	 */
	public void setCodVendedor(String codVendedor) {
		this.codVendedor = codVendedor;
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
	 * @return the imagen
	 */
	public Image getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the listaMeGustas
	 */
	public ArrayList<MeGusta> getListaMeGustas() {
		return listaMeGustas;
	}

	/**
	 * @param listaMeGustas the listaMeGustas to set
	 */
	public void setListaMeGustas(ArrayList<MeGusta> listaMeGustas) {
		this.listaMeGustas = listaMeGustas;
	}

	/**
	 * @return the listaComentarios
	 */
	public ArrayList<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * @param listaComentarios the listaComentarios to set
	 */
	public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	//
	public MeGusta crearMeGusta(String codVendedorLike) {
		MeGusta meGusta = null;
		meGusta = buscarMeGusta(codVendedorLike);
		if (meGusta == null) {
			meGusta = new MeGusta(codVendedorLike);
			meGusta.setCodVendedor(codVendedorLike);
			getListaMeGustas().add(meGusta);

			return meGusta;
		} else {
			return null;
		}

	}

	/*
	 * elimina un like de la lista de me gusta
	 */
	public boolean eliminarMeGusta(String cod) {
		MeGusta meGusta = null;

		meGusta = buscarMeGusta(cod);

		if (meGusta != null) {
			getListaMeGustas().remove(meGusta);
			System.out.println("lis  " + getListaMeGustas());
			return true;
		} else {

			return false;

		}

	}

	/*
	 * busca un like en la lista por medio del codigo del usuario.
	 */
	public MeGusta buscarMeGusta(String cod) {

		for (MeGusta meGusta : listaMeGustas) {

			if (meGusta.getCodVendedor().equals(cod)) {
				return meGusta;
			}
		}
		return null;

	}

	public Comentario crearComentario(String texto, String fecha, String userVendedor) {

		if (texto != null) {
			Comentario comentario = new Comentario(texto, fecha, userVendedor);
			getListaComentarios().add(comentario);
			System.out.println("lis com:::"+ getListaComentarios());

			return comentario;
		} else {
			return null;
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
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaPublicacion == null) ? 0 : fechaPublicacion.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((listaComentarios == null) ? 0 : listaComentarios.hashCode());
		result = prime * result + ((listaMeGustas == null) ? 0 : listaMeGustas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
	}



	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", imagen=" + imagen + ", precio=" + precio + ", estado=" + estado
				+ ", categoria=" + categoria + ", fechaPublicacion=" + fechaPublicacion + ", codVendedor=" + codVendedor
				+ ", id=" + id + "]";
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (categoria != other.categoria)
			return false;
		if (estado != other.estado)
			return false;
		if (fechaPublicacion == null) {
			if (other.fechaPublicacion != null)
				return false;
		} else if (!fechaPublicacion.equals(other.fechaPublicacion))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (listaComentarios == null) {
			if (other.listaComentarios != null)
				return false;
		} else if (!listaComentarios.equals(other.listaComentarios))
			return false;
		if (listaMeGustas == null) {
			if (other.listaMeGustas != null)
				return false;
		} else if (!listaMeGustas.equals(other.listaMeGustas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

}
