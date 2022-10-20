package co.uniquindio.edu.co.Marketplace.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.Marketplace.model.Comentario;
import co.uniquindio.edu.co.Marketplace.model.Mensaje;
import co.uniquindio.edu.co.Marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ChatVendedorController {
	private ModelFactoryController modelFactoryController;
	Vendedor vendedorSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMensaje;

    
    /**
     * 
     * @param event
     */
    @FXML
    void enviarMensajeAction(ActionEvent event) {
    	String textoMensaje = txtMensaje.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComentario = sdf.format(new Date());
		Vendedor vendedorMensaje = modelFactoryController.getVendedorLogueado();


		if (!txtMensaje.equals("")) {
			if(vendedorSeleccionado.getCedula()!= vendedorMensaje.getCedula()){

			modelFactoryController.crearMensaje(textoMensaje, fechaComentario,
					vendedorSeleccionado.getCedula(), vendedorMensaje);
			mostrarMensaje("Notificacion mensake", "Mensaje enviado", "El mensaje se ha enviado correctamente", AlertType.INFORMATION);
			txtMensaje.setText("");
			}else{
				mostrarMensajeError("No puedes enviarte mensajes a ti mismo WTF");

			}
		}else{
			mostrarMensajeError("Ingrese texto");
		}
    }

    @FXML
    void initialize() {
      
		modelFactoryController = ModelFactoryController.getInstance();

    }

	public void setVendedorSeleccionado(Vendedor vendedorSeleccionado) {
    	this.vendedorSeleccionado=vendedorSeleccionado;
		// TODO Auto-generated method stub
		
	}

//	private boolean mostrarMensajeInformacion(String mensaje) {
//
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setHeaderText(null);
//		alert.setTitle("Informacion");
//		alert.setContentText(mensaje);
//		Optional<ButtonType> action = alert.showAndWait();
//
//		if (action.get() == ButtonType.OK) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	private void mostrarMensajeInformacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informacion");
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	private void mostrarMensajeError(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(mensaje);
		alert.showAndWait();
		
	}
	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

}
