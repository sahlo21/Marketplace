package co.uniquindio.edu.co.Marketplace.model;

public class Solicitud {

	Vendedor vendedorSolicutud;
	String nombreSoli;
	String apellidoSoli;
	String codVendedorDestino;
	boolean respuesta;

	public Solicitud(Vendedor vendedorSolicitud, String nombreSoli, String apellidoSoli, String codVendedorDestino,
			boolean respuesta) {
		super();
		this.vendedorSolicutud = vendedorSolicitud;
		this.nombreSoli = nombreSoli;
		this.apellidoSoli = apellidoSoli;
		this.codVendedorDestino = codVendedorDestino;
		this.respuesta = respuesta;
	}

	public Solicitud() {

	}

	public Vendedor getVendedorSolicitud() {
		return vendedorSolicutud;
	}

	public void setVendedorSolicitud(Vendedor vendedorSolicutud) {
		this.vendedorSolicutud = vendedorSolicutud;
	}

	public String getCodVendedorDestino() {
		return codVendedorDestino;
	}

	public void setCodVendedorDestino(String codVendedorDestino) {
		this.codVendedorDestino = codVendedorDestino;
	}

	public Vendedor getVendedorSolicutud() {
		return vendedorSolicutud;
	}

	public void setVendedorSolicutud(Vendedor vendedorSolicutud) {
		this.vendedorSolicutud = vendedorSolicutud;
	}

	public String getNombreSoli() {
		return nombreSoli;
	}

	public void setNombreSoli(String nombreSoli) {
		this.nombreSoli = nombreSoli;
	}

	public String getApellidoSoli() {
		return apellidoSoli;
	}

	public void setApellidoSoli(String apellidoSoli) {
		this.apellidoSoli = apellidoSoli;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "Solicitud [Vendedor solicitud" + vendedorSolicutud + "Nombre Solicitud: "+ nombreSoli + " "+ apellidoSoli + ", Codigo solicitud Destino=" + codVendedorDestino
				+ ", Respuesta solicitud=" + respuesta + "]";
	}

}
