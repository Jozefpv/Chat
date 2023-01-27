package chat;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class EscribeCliente{

	private OutputStream output;
	 

	public EscribeCliente(OutputStream output) {
		this.output = output;
	}

	public void sendMessage(String mensaje) {
		try (PrintWriter salida = new PrintWriter(output, true, Charset.forName("UTF8"))) {
			String linea = mensaje;
			if(!linea.equals(":bye")) {
				salida.println(linea);
				System.out.println(linea);
			} else {
				System.out.println("Has salido del chat");

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
