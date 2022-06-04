package co.uniquindio.edu.co.Marketplace.persistencia;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	public static final String RUTA_ARCHIVO_VENDEDORES= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoProductos.txt";
	public static final String RUTA_ARCHIVO_COMENTARIOS= "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//archivos//archivoComentarios.txt";
	public static final String RUTA_ARCHIVO_LOG_EXCEPTIONS = "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//exceptionsMarketplaceLog.txt";
	public static final String RUTA_ARCHIVO_LOG_MARKETPLACE ="C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//marketplaceLog.txt";
	public static final String RUTA_ARCHIVO_LOG_LOGIN ="C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//log//registroLogin.txt";
	public static final String RUTA_ARCHIVO_MODELXML = "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//td//persistencia//model.xml";

//	/**
//	 * Didier
//	 */
//	public static final String RUTA_ARCHIVO_VENDEDORES = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoVendedores.txt";
//	public static final String RUTA_ARCHIVO_PRODUCTOS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoProductos.txt";
//	public static final String RUTA_ARCHIVO_COMENTARIOS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\archivos\\archivoComentarios.txt";
//	public static final String RUTA_ARCHIVO_LOG_EXCEPTIONS = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\exceptionsMarketplaceLog.txt";
//	public static final String RUTA_ARCHIVO_LOG_MARKETPLACE = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\marketplaceLog.txt";
//	public static final String RUTA_ARCHIVO_LOG_LOGIN = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\log\\registroLogin.txt";
//	public static final String RUTA_ARCHIVO_MODELXML = "C:\\Users\\Daniel Guiral\\Documents\\GitHub\\Marketplace\\td\\persistencia\\model.xml";

	

	

	public static void guardarRecursoMarketPlaceXML(Marketplace marketplace) {
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELXML, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Marketplace cargarRecursoMarketplaceXML() {
		Marketplace marketplace = null;
		try {
			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marketplace;
	}

}
