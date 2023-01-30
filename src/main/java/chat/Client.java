package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

	private String host;
	private int puerto;
	private Socket socket;
	private OutputStream output;
	private InputStream input;
	private Thread thread;

	public Client(String host, int puerto) {
		this.host = host;
		this.puerto = puerto;
	}

	public void connect() {
		try {
			socket = new Socket(host, puerto);
			output = socket.getOutputStream();
			input = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen(ClientListener listener) {
		
		if (thread != null && thread.isAlive()) {
			return;
		}
		
		thread = new Thread(() -> {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.input))) {
				String message = reader.readLine();
				while(message != null && !message.isEmpty()) {
					listener.onMessageReceived(message);
					message = reader.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();

	}
	
	public void stop() {
		
		thread.interrupt();
		thread = null;
		
	}

	public void send(String message) {
		try {
			PrintWriter writer = new PrintWriter(output, false, StandardCharsets.UTF_8);
			if (!message.equals(":bye")) {
				writer.println(message);
				writer.flush();
				System.out.println(message);
			} else {
				System.out.println("Has salido del chat");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
