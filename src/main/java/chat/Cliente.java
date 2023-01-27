package chat;
import java.io.IOException;
import java.net.Socket;

import javafx.collections.ObservableList;



public class Cliente {
	private String HOST;
	private String puerto;
	private EscribeCliente escritor;
	private EscuchaCliente lector;

	public Cliente(String HOST, String puerto) {
		this.HOST = HOST;
		this.puerto = puerto;
	}

	public void conectarAlServidor(ObservableList<String> lista, String mensaje) {
		try {
			Socket socket = new Socket(HOST, Integer.parseInt(puerto));
			lector = new EscuchaCliente(socket.getInputStream(), lista);
			escritor = new EscribeCliente(socket.getOutputStream());
			lector.start();
		} catch (NumberFormatException | IOException e) {

		}

	}
	
	public EscribeCliente getEscribeCliente() {
		return escritor;
	}

}
