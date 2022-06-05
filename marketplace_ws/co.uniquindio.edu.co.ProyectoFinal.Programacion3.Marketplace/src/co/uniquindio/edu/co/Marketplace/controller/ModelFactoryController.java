package co.uniquindio.edu.co.Marketplace.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import co.uniquindio.edu.co.Marketplace.Aplicacion;
import co.uniquindio.edu.co.Marketplace.model.Administrador;
import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Comentario;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.MeGusta;
import co.uniquindio.edu.co.Marketplace.model.Mensaje;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Solicitud;
import co.uniquindio.edu.co.Marketplace.model.TipoUsuario;
import co.uniquindio.edu.co.Marketplace.model.Usuario;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ModelFactoryController {
	Socket miSocket;


	ObjectOutputStream flujoSalidaObject;
	ObjectInputStream flujoEntradaObject;

	DataOutputStream flujoSalidaData;
	DataInputStream flujoEntradaData;


	Marketplace marketplace =new Marketplace();
	LoginControllerN loginController;
	Aplicacion aplicacion;
	Vendedor vendedorLogueado;
	Producto productoActual;
	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Producto> listaMuro = new ArrayList<>();
	ArrayList<Administrador> listaAdministradores = new ArrayList<>();
	Administrador admin = new Administrador();

	// ------------------------------ Singleton
	// ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {

		// 1. inicializar datos y luego guardarlo en archivos
//		iniciarSalvarDatosPrueba();

		// 2. Cargar los datos de los archivos
//		cargarDatosDesdeArchivos();

		//guardarRecursosMarketplace();

//		guardarRecursoXML();

		// 3. Guardar y Cargar el recurso serializable binario
//		guardarResourceBinario();
//		cargarResourceBinario();

		// 4. Guardar y Cargar el recurso serializable XML
//		guardarResourceXML();
//		cargarResourceXML();

		// crear el administrador
		admin.setNombre("Tyler");
		admin.setApellidos("Joseph");
		admin.setUsuario("admin");
		admin.setContrasena("11");
		admin.setCedula("21p");
		marketplace.getListaAdministradores().add(admin);


		if (marketplace == null) {
			System.out.println("es null");

		}
	}
	public void cargarMarketplaceServer(){
		try{
			miSocket =  new Socket("localhost", 8081);

			System.out.println("Conectado cliente");


			flujoSalidaObject = new ObjectOutputStream(miSocket.getOutputStream());
			flujoEntradaObject = new ObjectInputStream(miSocket.getInputStream());
			flujoSalidaData = new DataOutputStream(miSocket.getOutputStream());
			flujoEntradaData = new DataInputStream(miSocket.getInputStream());

			//Se reciben los datos que vienen desde el servidor


			flujoSalidaData.writeInt(2);
			flujoSalidaData.writeUTF("Datos correctamente al cliente");
			
			marketplace=(Marketplace) flujoEntradaObject.readObject();
			System.out.println(marketplace);
            listaMuro=marketplace.getListaMuro();
            admin.setNombre("Tyler");
    		admin.setApellidos("Joseph");
    		admin.setUsuario("admin");
    		admin.setContrasena("11");
    		admin.setCedula("21p");
    		marketplace.getListaAdministradores().add(admin);


			flujoEntradaData.close();
			flujoSalidaData.close();
			flujoEntradaObject.close();
			flujoSalidaObject.close();
			miSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarResourceXML() {
		Persistencia.cargarRecursoMarketplaceXML();
	}

	private void guardarResourceXML() {
		Persistencia.guardarRecursoMarketPlaceXML(marketplace);
	}

	private void cargarResourceBinario() {
		Persistencia.cargarRecursosMarketplaceBinario();
	}

	private void guardarResourceBinario() {
		Persistencia.guardarRecursosMarketplaceBinario(marketplace);
	}

	private void iniciarSalvarDatosPrueba() {

		inicializarDatos();

		try {

			Persistencia.guardarVendedores(getMarketplace().getListaVendedores());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Producto> getListaMuro() {
		return listaMuro;
	}

	public void setListaMuro(ArrayList<Producto> listaMuro) {
		this.listaMuro = listaMuro;
	}

	private void cargarDatosDesdeArchivos() {

		marketplace = new Marketplace("Fakebook Marketplace", "178");

		try {
			listaMuro.clear();
			this.listaMuro.addAll(Persistencia.cargarDatosArchivos(getMarketplace()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void guardarRecursosMarketplace() {
//
//		Marketplace marketplace = new Marketplace();
//		marketplace.setNombre("Marketplace");
//		marketplace.setNit("900010101");
//
//		Vendedor vendedor = new Vendedor();
//		vendedor.setNombre("Robinson");
//		vendedor.setApellidos("Crusso Dark");
//		vendedor.setCedula("301020");
//		vendedor.setUsuario("robin");
//		vendedor.setDireccion("Armenia");
//
//		marketplace.getListaVendedores().add(vendedor);
//
//		Persistencia.guardarRecursoMarketPlaceXML(marketplace);
//		Persistencia.guardarRecursosMarketplaceBinario(marketplace);
//	}

	private void inicializarDatos() {

		marketplace = new Marketplace("Fakebook Marketplace", "178");

		/**
		 * Vendedor 1
		 */

		Vendedor vendedor = new Vendedor();
		vendedor.setNombre("Kevin");
		vendedor.setApellidos("Sanchez");
		vendedor.setCedula("1006341989");
		vendedor.setUsuario("kaka");
		vendedor.setContrasena("11");
		vendedor.setDireccion("Narnia del este");
		vendedor.setId("1");

		Producto producto = new Producto();
		producto.setNombre("Nevera");
		producto.setImagen(new Image(this.getClass().getResourceAsStream("../resources/nevera.jpg")));
		producto.setPrecio(1570000.30);
		;
		producto.setEstado(Estado.PUBLICADO);
		;
		producto.setCategoria(Categoria.ELECTRODOMESTICOS);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaPublicacion = sdf.format(new Date());
		producto.setFechaPublicacion(fechaPublicacion);
		producto.setCodVendedor(vendedor.getCedula());
		producto.setId("1");

		Comentario comentario = new Comentario("Fino se�ores", fechaPublicacion, vendedor.getNombre());
		comentario.setId("1");
		producto.getListaComentarios().add(comentario);
		vendedor.getListaProductos().add(producto);
		listaMuro.add(producto);

		marketplace.getListaVendedores().add(vendedor);

		/**
		 * Vendedor 2
		 */
		vendedor = new Vendedor();
		vendedor.setNombre("Didier");
		vendedor.setApellidos("Grisales");
		vendedor.setCedula("1519");
		vendedor.setUsuario("kaka2");
		vendedor.setContrasena("11");
		vendedor.setDireccion("Narnia del oeste");
		vendedor.setId("2");

		producto = new Producto();
		producto.setNombre("Xbox");
		producto.setImagen(new Image(this.getClass().getResourceAsStream("../resources/xbox.jpg")));
		producto.setPrecio(1900000.50);
		;
		producto.setEstado(Estado.PUBLICADO);
		;
		producto.setCategoria(Categoria.TECNOLOGIA);
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		fechaPublicacion = sdf.format(new Date());
		producto.setFechaPublicacion(fechaPublicacion);
		producto.setCodVendedor(vendedor.getCedula());
		producto.setId("2");

		comentario = new Comentario("Viva playStation", fechaPublicacion, vendedor.getNombre());
		comentario.setId("2");
		producto.getListaComentarios().add(comentario);

		vendedor.getListaProductos().add(producto);
		listaMuro.add(producto);

		marketplace.getListaVendedores().add(vendedor);

		/**
		 * Vendedor 3
		 */
		vendedor = new Vendedor();
		vendedor.setNombre("Christian");
		vendedor.setApellidos("Santofimio");
		vendedor.setCedula("1529");
		vendedor.setUsuario("kaka3");
		vendedor.setContrasena("11");
		vendedor.setDireccion("Narnia del sur");
		vendedor.setId("3");

		producto = new Producto();
		producto.setNombre("MacBook");
		producto.setImagen(new Image(this.getClass().getResourceAsStream("../resources/mac.jpg")));
		producto.setPrecio(3120000.70);
		;
		producto.setEstado(Estado.PUBLICADO);
		;
		producto.setCategoria(Categoria.TECNOLOGIA);
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		fechaPublicacion = sdf.format(new Date());
		producto.setFechaPublicacion(fechaPublicacion);
		producto.setCodVendedor(vendedor.getCedula());
		producto.setId("3");

		comentario = new Comentario("Potente", fechaPublicacion, vendedor.getNombre());
		comentario.setId("3");
		producto.getListaComentarios().add(comentario);

		vendedor.getListaProductos().add(producto);
		listaMuro.add(producto);

		marketplace.getListaVendedores().add(vendedor);

	}

	public void guardarMarketplaceLog(String mensaje, int nivel, String accion, String nombreUsuario, String codigo) {
		Persistencia.guardarMarketplaceLog(mensaje, nivel, accion, nombreUsuario, codigo);
	}

	public void guardarRegistroLogin(String mensaje, int nivel, String accion, String nombreUsuario, String codigo) {
		Persistencia.guardarRegistroLogin(mensaje, nivel, accion, nombreUsuario, codigo);
	}

	public void guardarExceptionsLog(String mensaje, int nivel, String accion, String nombreUsuario, String codigo) {
		Persistencia.guardarExceptionsLog(mensaje, nivel, accion, nombreUsuario, codigo);
	}

	public ArrayList<Vendedor> obtenerVendedor() {

		return this.marketplace.getListaVendedores();

	}

	public ArrayList<Administrador> obtenerAdministrador() {

		return this.marketplace.getListaAdministradores();

	}

	public Vendedor crearVendedor(String nombre, String apellidos, String usuario, String contrasena, String cedula,
			String direccion) {

		Vendedor vendedor = null;

		vendedor = marketplace.crearVendedor(nombre, apellidos, usuario, contrasena, cedula, direccion);
		if (vendedor != null) {
			guardarMarketplaceLog("Vendedor creado", 1, "Crear vendedor", admin.getNombre(), admin.getCedula());
		}
		return vendedor;

	}

	public boolean actualizarVendedor(String codigoActual, String nombre, String apellidos, String usuario,
			String contrasena, String cedula, String direccion) {

		boolean flagProductoActualizado = false;

		flagProductoActualizado = marketplace.actualizarVendedor(codigoActual, nombre, apellidos, usuario, contrasena,
				cedula, direccion);
		if (flagProductoActualizado != false) {

			guardarMarketplaceLog("Vendedor actualizado", 1, "Actualizar vendedor", admin.getNombre(),
					admin.getCedula());
		}
		return flagProductoActualizado;

	}

	public boolean eliminarVendedor(String codigoVendedor) {
		guardarMarketplaceLog("Vendedor eliminado", 1, "Eliminar vendedor", admin.getNombre(), admin.getCedula());

		return marketplace.eliminarVendedor(codigoVendedor);

	}

	public ArrayList<Producto> obtenerProducto() {
		return this.vendedorLogueado.getListaProductos();

	}
	public ArrayList<Mensaje> obtenerMensajes() {
		return this.vendedorLogueado.getListaMensajes();
	}


	public ArrayList<Producto> obtenerMuro() {
		return listaMuro;

	}
	
	public ArrayList<Solicitud> obtenerSolicitudes() {
		return this.vendedorLogueado.getListaSolicitud();
	}

	public ArrayList<Vendedor> obtenerAsociados() {
		return this.vendedorLogueado.getListaContactos();
	}

	public ArrayList<Comentario> obtenerComentario() {
		ArrayList<Comentario> liscom = new ArrayList<>();

		liscom = productoActual.getListaComentarios();

		return liscom;

	}

	public MeGusta crearMeGusta(String codVendedorLike) {
		MeGusta meGusta = null;
		meGusta = productoActual.crearMeGusta(codVendedorLike);
		if (meGusta != null) {
			guardarMarketplaceLog("Me gusta creado", 1, "Crear me gusta", vendedorLogueado.getNombre(),
					vendedorLogueado.getCedula());
		}
		return meGusta;
	}

	public Comentario crearComentario(String texto, String fecha, String userVendedor) {
		Comentario comentario = null;
		comentario = productoActual.crearComentario(texto, fecha, userVendedor);
		if (comentario != null) {
			guardarMarketplaceLog("Comentario creado", 1, "Crear comentario", vendedorLogueado.getNombre(),
					vendedorLogueado.getCedula());
		}
		return comentario;
	}

	public Producto crearProducto(String nombre, Image imagen, Double precio, Estado estado, Categoria categoria,
			String fechaPublicacion, String codVendedor) {

		Producto producto = null;

		producto = vendedorLogueado.crearProducto(nombre, imagen, precio, estado, categoria, fechaPublicacion,
				codVendedor);
		if (producto.getEstado() == estado.PUBLICADO) {

		}
		listaMuro.add(producto);
		if (producto != null) {
			guardarMarketplaceLog("Producto creado", 1, "Crear producto", vendedorLogueado.getNombre(),
					vendedorLogueado.getCedula());
		}
		return producto;

	}
	public void crearMensaje(String textoMensaje, String fechaComentario, String vendedorMensaje, Vendedor vendedorRemitente) {
		// TODO Auto-generated method stub
		Mensaje msj = null;
		for (Vendedor vendedorSelect : marketplace.getListaVendedores()) {
			if (vendedorSelect.getCedula().equals(vendedorMensaje)) {
				vendedorSelect.crearMensaje(textoMensaje, fechaComentario, vendedorRemitente);
			}
		}
	}
	

	public void crearSolicitud(Vendedor vendedorSolicitud, String vendedorDestino, boolean respuesta) {
		for (Vendedor vendedorSelect : marketplace.getListaVendedores()) {
			if (vendedorSelect.getCedula().equals(vendedorDestino)) {
				vendedorSelect.crearSolicitud(vendedorSolicitud, vendedorSolicitud.getNombre(),
						vendedorSolicitud.getApellidos(), vendedorDestino, respuesta);
				
			}
		}
		
	}
	
	public boolean validarSolicitudExistente(Vendedor vendedorSolicitud, Vendedor vendedorSelect) {
		return vendedorSelect.validarSolicitudExistente(vendedorSolicitud, vendedorSelect);
	}
	
	public void aceptarSolicitud(Vendedor vendedorSolicitud, Vendedor vendedorAceptaLogeado) {
		vendedorSolicitud.aceptarSolicitud(vendedorAceptaLogeado);
		vendedorLogueado.aceptarSolicitud(vendedorSolicitud);
	}
	
	public void eliminarSolicitud(Solicitud solicitud, Vendedor vendedor) {
		vendedor.eliminarSolicitud(solicitud);
	}

	
	
	public boolean actualizarProducto(String nombreActual, String nombre, Image imagen, Double precio, Estado estado,
			Categoria categoria, String fechaPublicacion) {

		boolean flagProductoActualizado = false;

		flagProductoActualizado = vendedorLogueado.actualizarProducto(nombreActual, nombre, imagen, precio, estado,
				categoria, fechaPublicacion);
		if (flagProductoActualizado != false) {

			guardarMarketplaceLog("Producto actualizado", 1, "Actualizar producto", vendedorLogueado.getNombre(),
					vendedorLogueado.getCedula());
		}
		return flagProductoActualizado;

	}

	public void eliminarMeGusta(String codVendedorLike) {
		guardarMarketplaceLog("Me gusta eliminado", 1, "Eliminar me gusta", vendedorLogueado.getNombre(),
				vendedorLogueado.getCedula());
		productoActual.eliminarMeGusta(codVendedorLike);

	}

	public boolean eliminarProducto(String nombreProducto) {
		Producto producto = null;

		producto = buscarProducto(nombreProducto);
		if (producto != null) {
			listaMuro.remove(producto);
			guardarMarketplaceLog("Producto eliminado", 1, "Eliminar producto", vendedorLogueado.getNombre(),
					vendedorLogueado.getCedula());

		}

		return vendedorLogueado.eliminarProducto(nombreProducto);
	}

	private Producto buscarProducto(String nombre) {

		for (Producto producto : listaMuro) {

			if (producto.getNombre().equals(nombre)) {
				return producto;
			}
		}
		return null;

	}
	

	public Usuario ingreso(String usuario, String contrasena) {
		Usuario user = null;
		listaVendedores.clear();
		listaVendedores.addAll(obtenerVendedor());
		listaAdministradores.clear();
		listaAdministradores.addAll(obtenerAdministrador());
		for (Vendedor vendedor : listaVendedores) {
			if (usuario.equals(vendedor.getUsuario()) && contrasena.equals(vendedor.getContrasena())) {
				guardarRegistroLogin("Inicio de sesi�n correcto", 1, "Iniciar de sesi�n vendedor", vendedor.getNombre(),
						vendedor.getCedula());
				return vendedor;
			}
		}
		for (Administrador administrador : listaAdministradores) {
			if (administrador.getUsuario().equals(usuario) && administrador.getContrasena().equals(contrasena)) {
				guardarRegistroLogin("Inicio de sesi�n correcto", 1, "Iniciar de sesi�n administrador",
						administrador.getNombre(), administrador.getCedula());
				return administrador;
			} else {
				guardarRegistroLogin("Inicio de sesi�n fallido", 1, "Iniciar de sesi�n ", usuario, "No aplica");
			}
		}
		return user;
	}

	public void setVendedorLogueado(Vendedor vendedor2) {
		this.vendedorLogueado = vendedor2;
	}

	/**
	 * @return the vendedorLogueado
	 */
	public Vendedor getVendedorLogueado() {
		return vendedorLogueado;
	}

	/**
	 * @return the admin
	 */
	public Administrador getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	void cerrarSesionVendedor(Aplicacion aplicacion2) {
		guardarRegistroLogin("Cierre de sesi�n correcto", 1, "Cerrar sesi�n vendedor", vendedorLogueado.getNombre(),
				vendedorLogueado.getCedula());

		aplicacion2.showLogin();
	}

	void cerrarSesionAdmin(Aplicacion aplicacion2) {
		guardarRegistroLogin("Cierre de sesi�n fallido", 1, "Cerrar sesi�n administrador", admin.getNombre(),
				admin.getCedula());

		aplicacion2.showLogin();
	}

	/**
	 * @return the marketplace
	 */
	public Marketplace getMarketplace() {
		return marketplace;
	}

	/**
	 * @param marketplace the marketplace to set
	 */
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public Producto getProductoActual() {
		return productoActual;
	}

	public void setProductoActual(Producto productoActual) {
		this.productoActual = productoActual;
	}

	public MeGusta buscarLike(String cod) {
		System.out.println("prodmodel: +"+cod);
		MeGusta mg = null;
		mg = productoActual.buscarMeGusta(cod);
		System.out.println("mg:"+mg);

		return mg;
	}



	
	

}
