package chat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chat.Client;
import javafx.application.Platform;
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

	public Client client;
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
		
		client = new Client("192.168.1.138", 50555);
		client.connect();
		client.send("Profe");
		client.listen(message -> {
			System.out.println(message);
			Platform.runLater(() -> {
				lista.add(message);
			});
		});
		
		
	}


	@FXML
	void onSendAction(ActionEvent event) {
		client.send(getMensaje());
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
