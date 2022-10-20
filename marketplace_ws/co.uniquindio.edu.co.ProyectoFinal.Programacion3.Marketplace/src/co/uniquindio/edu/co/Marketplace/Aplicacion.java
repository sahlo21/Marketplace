package co.uniquindio.edu.co.Marketplace;

import java.io.IOException;

import co.uniquindio.edu.co.Marketplace.controller.LoginController;
import co.uniquindio.edu.co.Marketplace.controller.MarketplaceAdminController;
import co.uniquindio.edu.co.Marketplace.controller.MarketplaceVendedorController;
import co.uniquindio.edu.co.Marketplace.controller.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private Stage primaryStage;

	LoginController logIn;


	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	private int numInicios=0; 

	@Override
	public void start(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Fakebook marketplace");
			showLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * carga la vista del loggin
	 */
	public void showLogin() {
		

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/Login.fxml"));

			BorderPane rootLayout = (BorderPane) loader.load();
			logIn = loader.getController();
			logIn.setAplicacion(this, numInicios);
		
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Inicio de sesion");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/iconMarketplace.png")));

			/*
			 * "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//info//iconMarketplace.png
			 * ../../../../../../../../../info/iconMarketplace.png
			 */

			primaryStage.show();
			numInicios++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdministrador() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/MarketplaceAdminView.fxml"));

			BorderPane rootLayout = (BorderPane) loader.load();
			MarketplaceAdminController administradorController = loader.getController();
			administradorController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Portal administrativo");

			primaryStage.show();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//	public void showProducto(Producto productoSeleccionado) {
//
//		try {
//
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Aplicacion.class.getResource("view/MarketplaceProductoView.fxml"));
//
//			BorderPane rootLayout = (BorderPane) loader.load();
//			ProductoController controller = loader.getController();
//			controller.setAplicacion(this);
//
//			controller.initAttributtes(productoSeleccionado);
//			Scene scene = new Scene(rootLayout);
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Portal administrativo");
//
//			primaryStage.show();
//
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}	


	public void showVendedor() {


		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/MarketplaceVendedorView.fxml"));

			BorderPane rootLayout = (BorderPane) loader.load();
			MarketplaceVendedorController marketplaceVendedorController = loader.getController();
			marketplaceVendedorController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			primaryStage.setTitle("Portal de vendedor");

			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



}