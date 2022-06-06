package co.uniquindio.edu.co.Marketplace.persistencia;

import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.uniquindio.edu.co.Marketplace.controller.ModelFactoryController;
import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Comentario;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;

public class Persistencia {
	/**
	 * Kevin
	 */
//	public static final String RUTA_ARCHIVO_VENDEDORES= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoVendedores.txt";
//	public static final String RUTA_ARCHIVO_PRODUCTOS= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoProductos.txt";
//	public static final String RUTA_ARCHIVO_COMENTARIOS= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoComentarios.txt";
//	public static final String RUTA_ARCHIVO_LOG_EXCEPTIONS = "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//exceptionsMarketplaceLog.txt";
//	public static final String RUTA_ARCHIVO_LOG_MARKETPLACE ="C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//marketplaceLog.txt";
//	public static final String RUTA_ARCHIVO_LOG_LOGIN ="C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//registroLogin.txt";
//	public static final String RUTA_ARCHIVO_MODELXML = "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//model.xml";

	
//	/**
//	 * Didier
//	 */
	public static final String RUTA_ARCHIVO_VENDEDORES = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoProductos.txt";
	public static final String RUTA_ARCHIVO_COMENTARIOS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoComentarios.txt";
	public static final String RUTA_ARCHIVO_LOG_EXCEPTIONS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\exceptionsMarketplaceLog.txt";
	public static final String RUTA_ARCHIVO_LOG_MARKETPLACE = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\marketplaceLog.txt";
	public static final String RUTA_ARCHIVO_LOG_LOGIN = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\registroLogin.txt";

	/**
	 * Agregue una ruta dentro del proyecto para poder mirar como se comportaba el
	 * archivo
	 */
	// public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO =
	// "C:\\Users\\Daniel
	// Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\model.dat";

	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "src/co/uniquindio/edu/co/Marketplace/resources/model.dat";

	// public static final String RUTA_ARCHIVO_OBJETOS =
	// "src/resources/archivoObjetos.txt";
	// public static final String RUTA_ARCHIVO_MODELO_BANCO_BINARIO =
	// "src/resources/model.dat";
	// public static final String RUTA_ARCHIVO_MODELO_BANCO_XML =
	// "src/resources/model.xml";
	// public static void cargarDatosArchivos(Banco banco) throws
	// FileNotFoundException, IOException {
	//
	//
	// //cargar archivo de clientes
	// ArrayList<Cliente> clientesCargados = cargarClientes();
	//
	// if(clientesCargados.size() > 0)
	// banco.getListaClientes().addAll(clientesCargados);
	//
	//
	// //cargar archivos empleados
	// ArrayList<Empleado> empleadosCargados = cargarEmpleados();
	//
	// if(empleadosCargados.size() > 0)
	// banco.getListaEmpleados().addAll(empleadosCargados);
	//
	// //cargar archivo objetos
	//
	// //cargar archivo empleados
	//
	// //cargar archivo prestamo
	//
	// }
	//
	//
	//
	//
	//
	// /**
	// * Guarda en un archivo de texto todos la informaci�n de las personas
	// almacenadas en el ArrayList
	// * @param objetos
	// * @param ruta
	// * @throws IOException
	// */
	// public static void guardarClientes(ArrayList<Cliente> listaClientes) throws
	// IOException {
	// // TODO Auto-generated method stub
	// String contenido = "";
	//
	// for(Cliente cliente:listaClientes)
	// {
	// contenido+=
	// cliente.getNombre()+","+cliente.getApellido()+","+cliente.getCedula()+","+cliente.getDireccion()
	// +","+cliente.getCorreo()+","+cliente.getFechaNacimiento()+","+cliente.getTelefono()+"\n";
	// }
	// ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
	//
	// }
	//
	//
	// public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados)
	// throws IOException {
	//
	// // TODO Auto-generated method stub
	// String contenido = "";
	//
	// for(Empleado empleado:listaEmpleados)
	// {
	// contenido+=
	// empleado.getNombre()+","+empleado.getApellido()+","+empleado.getCedula()+","+empleado.getFechaNacimiento()+"\n";
	// }
	// ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
	// }
	//
	//
	//
	//// ----------------------LOADS------------------------
	//
	// /**
	// *
	// * @param tipoPersona
	// * @param ruta
	// * @return un Arraylist de personas con los datos obtenidos del archivo de
	// texto indicado
	// * @throws FileNotFoundException
	// * @throws IOException
	// */
	// public static ArrayList<Cliente> cargarClientes() throws
	// FileNotFoundException, IOException
	// {
	// ArrayList<Cliente> clientes =new ArrayList<Cliente>();
	//
	// ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
	// String linea="";
	//
	// for (int i = 0; i < contenido.size(); i++)
	// {
	// linea = contenido.get(i);
	// Cliente cliente = new Cliente();
	// cliente.setNombre(linea.split(",")[0]);
	// cliente.setApellido(linea.split(",")[1]);
	// cliente.setCedula(linea.split(",")[2]);
	// cliente.setDireccion(linea.split(",")[3]);
	// cliente.setCorreo(linea.split(",")[4]);
	// cliente.setFechaNacimiento(linea.split(",")[5]);
	// cliente.setTelefono(linea.split(",")[6]);
	// clientes.add(cliente);
	// }
	// return clientes;
	// }
	//
	//
	//
	// private static ArrayList<Empleado> cargarEmpleados() throws IOException {
	//
	// ArrayList<Empleado> empleados =new ArrayList<Empleado>();
	//
	// ArrayList<String> contenido =
	// ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
	// String linea="";
	//
	// for (int i = 0; i < contenido.size(); i++)
	// {
	// linea = contenido.get(i);
	// Empleado empleado = new Empleado();
	// empleado.setNombre(linea.split(",")[0]);
	// empleado.setApellido(linea.split(",")[1]);
	// empleado.setCedula(linea.split(",")[2]);
	// empleado.setFechaNacimiento(linea.split(",")[3]);
	// empleados.add(empleado);
	// }
	// return empleados;
	// }
	//
	//
	//
	//

	public static void almacenarDatos(String rutaArchivo, String formato, Object args,Object args1,Object args2,Object args3) throws IOException {
		
//		FileWriter archivoSalidad;
		Formatter archivo;
		
		archivo = new Formatter(rutaArchivo);
		archivo.format(formato, args,args1,args2,args3);
		archivo.flush();
		archivo.close();
		
//		archivoSalidad = new FileWriter(rutaArchivo, true);
//		archivoSalidad.write(formato);
//		archivoSalidad.flush();
//		archivoSalidad.close();

	}
	
	
	public static void escribirArchivo(String rutaArchivo, ArrayList<String> texto,boolean adicionar) throws IOException 
	{
		
		FileWriter archivoSalida = null;
		BufferedWriter  bufferSalida;
		
		archivoSalida = new FileWriter(rutaArchivo, adicionar);
		bufferSalida =  new BufferedWriter(archivoSalida);
		
		for (String linea : texto) 
		{
			bufferSalida.write(linea+"\n");
		}
		
		
		bufferSalida.flush();
		bufferSalida.close();
		
		archivoSalida.close();
	}
	
	
	public static ArrayList<String> leerArchivo(String ruta) throws IOException {

		ArrayList<String>  contenido = new ArrayList<String>();
		FileReader fr=new FileReader(ruta);
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";
		while((linea = bfr.readLine())!=null) 
		{
			contenido.add(linea);
		}
		bfr.close();
		fr.close();
		return contenido;
	}
	
	
	public static void serializarObjeto (String rutaArchivo, Object objeto) throws IOException 
	{
		ObjectOutputStream salidaObjeto;
		
		
		salidaObjeto = new ObjectOutputStream(new FileOutputStream(rutaArchivo)); 
		salidaObjeto.writeObject(objeto);
		salidaObjeto.close();
	}
	
	
	public static Object deseRializarObjeto (String nombreArchivo) throws Exception 
	{
		Object objecto;
		ObjectInputStream entradaObjeto;
		
		entradaObjeto = new ObjectInputStream(new FileInputStream(nombreArchivo));
		objecto = entradaObjeto.readObject();
		entradaObjeto.close();
		
		return objecto;
	}
	
	
	


	public static void guardarRegistroLogin(String mensajeLog, int nivel, String accion, String nombreUsuario,
			String codigo) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_LOGIN, nombreUsuario, codigo);
	}

	public static void guardarMarketplaceLog(String mensajeLog, int nivel, String accion, String nombreUsuario,
			String codigo) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_MARKETPLACE, nombreUsuario, codigo);
	}

	public static void guardarExceptionsLog(String mensajeLog, int nivel, String accion, String nombreUsuario,
			String codigo) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_EXCEPTIONS, nombreUsuario, codigo);
	}

	/**
	 * Guarda en un archivo de texto todos la informaci�n de las personas
	 * almacenadas en el ArrayList
	 * 
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		String contenidoProducto = "";
		String contenidoComentario = "";
		System.out.println("lsita guardar vedne:" + listaVendedores);

		for (Vendedor vendedor : listaVendedores) {
			contenido += vendedor.getNombre() + "@" + vendedor.getApellidos() + "@" + vendedor.getCedula() + "@"
					+ vendedor.getDireccion() + "@" + vendedor.getUsuario() + "@" + vendedor.getContrasena() + "@"
					+ vendedor.getId() + "\n";
			ArrayList<Producto> listaProductos;
			listaProductos = vendedor.getListaProductos();
			for (Producto producto : listaProductos) {
				contenidoProducto += producto.getNombre() + "@" + producto.getImagen() + "@" + producto.getPrecio()
						+ "@" + producto.getEstado() + "@" + producto.getCategoria() + "@"
						+ producto.getFechaPublicacion() + "@" + producto.getCodVendedor() + "@" + producto.getId()
						+ "\n";
				ArrayList<Comentario> listaComentarios;
				listaComentarios = producto.getListaComentarios();
				for (Comentario comentario : listaComentarios) {
					contenidoComentario += comentario.getTexto() + "@" + comentario.getFecha() + "@"
							+ comentario.getUserVendedor() + "@" + comentario.getId() + "\n";

				}
				ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMENTARIOS, contenidoComentario, false);

			}
			ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProducto, false);
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);

	}

	public static ArrayList<Producto> cargarDatosArchivos(Marketplace marketplace)
			throws FileNotFoundException, IOException {

		// cargar archivo de vendedores
		ArrayList<Vendedor> vendedoresCargados = cargarVendedor();
		ArrayList<Producto> productosCargados = cargarProductos();
		ArrayList<Comentario> comentariosCargados = cargarComentarios();
		System.out.println(vendedoresCargados);
		System.out.println(productosCargados);
		System.out.println(comentariosCargados);

		ArrayList<Producto> listaMuro = new ArrayList<>();

		marketplace.getListaVendedores().addAll(vendedoresCargados);
		for (Vendedor vendedor : vendedoresCargados) {
			for (Producto producto : productosCargados) {

				if (vendedor.getId().equals(producto.getId())) {
					vendedor.getListaProductos().add(producto);
					listaMuro.add(producto);
					for (Comentario comentario : comentariosCargados) {
						if (producto.getId().equals(comentario.getId())) {
							producto.getListaComentarios().add(comentario);
						}
					}
				}

			}
		}

		return listaMuro;
	}

	// cargar archivo objetos

	// cargar archivo empleados

	// cargar archivo prestamo

	public static ArrayList<Vendedor> cargarVendedor() throws FileNotFoundException, IOException {
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@")[0]);
			vendedor.setApellidos(linea.split("@")[1]);
			vendedor.setCedula(linea.split("@")[2]);
			vendedor.setDireccion(linea.split("@")[3]);
			vendedor.setUsuario(linea.split("@")[4]);
			vendedor.setContrasena(linea.split("@")[5]);
			vendedor.setId(linea.split("@")[6]);

			vendedores.add(vendedor);
		}
		return vendedores;
	}

	public static ArrayList<Producto> cargarProductos() throws FileNotFoundException, IOException {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Producto producto = new Producto();
			producto.setNombre(linea.split("@")[0]);
			producto.setImagen(null);
			producto.setPrecio(Double.parseDouble(linea.split("@")[3]));

			if (linea.split("@")[4].equals("PUBLICADO")) {
				producto.setEstado(Estado.PUBLICADO);
				System.out.println("lina slit 4" + linea.split("@")[4]);

			} else if (linea.split("@")[4].equals("VENDIDO")) {
				producto.setEstado(Estado.VENDIDO);

			} else if (linea.split("@")[4].equals("CANCELADO")) {
				producto.setEstado(Estado.CANCELADO);
			}

			if (linea.split("@")[4].equals("ACCESORIOS")) {
				producto.setCategoria(Categoria.ACCESORIOS);

			} else if (linea.split("@")[5].equals("ELECTRODOMESTICOS")) {
				producto.setCategoria(Categoria.ELECTRODOMESTICOS);

			} else if (linea.split("@")[5].equals("HOGAR")) {
				producto.setCategoria(Categoria.HOGAR);
			} else if (linea.split("@")[5].equals("TECNOLOGIA")) {
				producto.setCategoria(Categoria.TECNOLOGIA);
			} else if (linea.split("@")[5].equals("VEHICULOS")) {
				producto.setCategoria(Categoria.VEHICULOS);
			}
			producto.setFechaPublicacion(linea.split("@")[6]);
			producto.setCodVendedor(linea.split("@")[7]);
			producto.setId(linea.split("@")[8]);

			productos.add(producto);
		}
		return productos;
	}

	public static ArrayList<Comentario> cargarComentarios() throws FileNotFoundException, IOException {
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMENTARIOS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Comentario comentario = new Comentario();
			comentario.setTexto(linea.split("@")[0]);
			comentario.setFecha(linea.split("@")[1]);
			comentario.setUserVendedor(linea.split("@")[2]);
			comentario.setId(linea.split("@")[3]);

			comentarios.add(comentario);
		}
		return comentarios;
	}
	//
	//
	// public static boolean iniciarSesion(String usuario, String contrasenia)
	// throws FileNotFoundException, IOException, UsuarioExcepcion {
	//
	// if(validarUsuario(usuario,contrasenia)) {
	// return true;
	// }else {
	// throw new UsuarioExcepcion("Usuario no existe");
	// }
	//
	// }
	//
	// private static boolean validarUsuario(String usuario, String contrasenia)
	// throws FileNotFoundException, IOException
	// {
	// ArrayList<Usuario> usuarios =
	// Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
	//
	// for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
	// {
	// Usuario usuarioAux = usuarios.get(indiceUsuario);
	// if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) &&
	// usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// public static ArrayList<Usuario> cargarUsuarios(String ruta) throws
	// FileNotFoundException, IOException {
	// ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
	//
	// ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
	// String linea="";
	//
	// for (int i = 0; i < contenido.size(); i++) {
	// linea = contenido.get(i);
	//
	// Usuario usuario = new Usuario();
	// usuario.setUsuario(linea.split(",")[0]);
	// usuario.setContrasenia(linea.split(",")[1]);
	//
	// usuarios.add(usuario);
	// }
	// return usuarios;
	// }
	//
	//
	//// ----------------------SAVES------------------------
	//
	// /**
	// * Guarda en un archivo de texto todos la informaci�n de las personas
	// almacenadas en el ArrayList
	// * @param objetos
	// * @param ruta
	// * @throws IOException
	// */
	//
	// public static void guardarObjetos(ArrayList<Cliente> listaClientes, String
	// ruta) throws IOException {
	// String contenido = "";
	//
	// for(Cliente clienteAux:listaClientes) {
	// contenido+=
	// clienteAux.getNombre()+","+clienteAux.getApellido()+","+clienteAux.getCedula()+clienteAux.getDireccion()
	// +","+clienteAux.getCorreo()+","+clienteAux.getFechaNacimiento()+","+clienteAux.getTelefono()+"\n";
	// }
	// ArchivoUtil.guardarArchivo(ruta, contenido, true);
	// }
	//
	//
	//
	//
	//
	// //------------------------------------SERIALIZACI�N y XML
	//
	//
	// public static Banco cargarRecursoBancoBinario() {
	//
	// Banco banco = null;
	//
	// try {
	// banco =
	// (Banco)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return banco;
	// }
	//
	// public static void guardarRecursoBancoBinario(Banco banco) {
	//
	// try {
	// ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO,
	// banco);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	//
	// public static Banco cargarRecursoBancoXML() {
	//
	// Banco banco = null;
	//
	// try {
	// banco =
	// (Banco)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return banco;
	//
	// }
	//
	//
	//
	// public static void guardarRecursoBancoXML(Banco banco) {
	//
	// try {
	// ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML,
	// banco);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static Marketplace cargarRecursosMarketplaceBinario() {

		Marketplace marketplace = null;

		try {
			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marketplace;
	}

	public static void guardarRecursosMarketplaceBinario(Marketplace marketplace) {

		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void guardarRecursoMarketPlaceXML(Marketplace marketplace) {
//		try {
//			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELXML, marketplace);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static Marketplace cargarRecursoMarketplaceXML() {
//		Marketplace marketplace = null;
//		try {
//			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELXML);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return marketplace;
//	}

}
