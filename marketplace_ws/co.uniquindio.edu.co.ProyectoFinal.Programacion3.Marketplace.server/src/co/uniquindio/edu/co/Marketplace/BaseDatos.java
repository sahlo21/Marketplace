package co.uniquindio.edu.co.Marketplace;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.uniquindio.edu.co.Marketplace.model.Administrador;
import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Comentario;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import co.uniquindio.edu.co.Marketplace.persistencia.Persistencia;
import javafx.scene.image.Image;


public class BaseDatos {
	static Marketplace marketplace;


	public static void main(String[] args) {
		inicializarDatos();

		try {
			Persistencia.guardarRecursoMarketPlaceXML(marketplace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(marketplace);

	}



	public static void  inicializarDatos() {

		marketplace = new Marketplace("Fakebook Marketplace", "178");
		Administrador admin=new Administrador();
		admin.setNombre("Tyler");
		admin.setApellidos("Joseph");
		admin.setUsuario("admin");
		admin.setContrasena("11");
		admin.setCedula("21p");
		marketplace.getListaAdministradores().add(admin);
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
		producto.setImagen(null);
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
		marketplace.getListaMuro().add(producto);


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
		producto.setImagen(null);
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
		marketplace.getListaMuro().add(producto);

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
		producto.setImagen(null);
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
		marketplace.getListaMuro().add(producto);

		marketplace.getListaVendedores().add(vendedor);

	}
}
