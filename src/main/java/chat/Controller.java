package chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {

	public Cliente jozef;
	public ListProperty<String> lista = new SimpleListProperty<>(FXCollections.observableArrayList());
	public StringProperty mensaje = new SimpleStringProperty();
	
	@FXML
	private ListView<String> messageList;

	@FXML
	private Button sendButton;

	@FXML
	private TextField textInput;

	@FXML
	private BorderPane view;

	public Controller() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		messageList.itemsProperty().bind(lista);
		mensaje.bindBidirectional(textInput.textProperty());
		
		
		jozef = new Cliente("192.168.1.165", "50555");
		jozef.conectarAlServidor(getLista(), getMensaje());
		
		
	}


	@FXML
	void onSendAction(ActionEvent event) {
		jozef.getEscribeCliente().sendMessage(getMensaje());
		setMensaje("");
	}

	public BorderPane getView() {
		return view;
	}

	public final ListProperty<String> listaProperty() {
		return this.lista;
	}
	

	public final ObservableList<String> getLista() {
		return this.listaProperty().get();
	}
	

	public final void setLista(final ObservableList<String> lista) {
		this.listaProperty().set(lista);
	}

	public final StringProperty mensajeProperty() {
		return this.mensaje;
	}
	

	public final String getMensaje() {
		return this.mensajeProperty().get();
	}
	

	public final void setMensaje(final String mensaje) {
		this.mensajeProperty().set(mensaje);
	}
	
	

}
