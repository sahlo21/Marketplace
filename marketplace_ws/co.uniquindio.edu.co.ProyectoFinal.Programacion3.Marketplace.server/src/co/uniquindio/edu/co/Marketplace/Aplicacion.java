package co.uniquindio.edu.co.Marketplace;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import co.uniquindio.edu.co.Marketplace.controller.ServerController;

import co.uniquindio.edu.co.Marketplace.controller.ModelFactoryController;
import co.uniquindio.edu.co.Marketplace.model.Categoria;
import co.uniquindio.edu.co.Marketplace.model.Estado;
import co.uniquindio.edu.co.Marketplace.model.Marketplace;
import co.uniquindio.edu.co.Marketplace.model.Producto;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Aplicacion extends Application {

	private Stage primaryStage;

	Marketplace marketplace= new Marketplace("Fakebook", "178");


	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance(); 

	@Override
	public void start(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Server marketplace");
			showServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showServer() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/Login.fxml"));

			BorderPane rootLayout = (BorderPane) loader.load();
			ServerController logIn = loader.getController();
			logIn.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Inicio de sesi�n");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/iconMarketplace.png")));

			/*
			 * "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//info//iconMarketplace.png
			 * ../../../../../../../../../info/iconMarketplace.png
			 */

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