package chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.collections.ObservableList;


public class EscuchaCliente extends Thread {

	private InputStream input;
	private ObservableList<String> lista;

	public EscuchaCliente(InputStream input, ObservableList<String> lista) {
		this.input = input;
		this.lista = lista;
	}

	@Override
	public void run() {
		try (BufferedReader entrada = new BufferedReader(new InputStreamReader(this.input))) {
			String linea = entrada.readLine();
			while(!linea.isEmpty()) {
				System.out.println("--------->" + linea);
				lista.add(linea);
				linea = entrada.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
