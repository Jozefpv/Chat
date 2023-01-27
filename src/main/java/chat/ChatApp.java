package chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApp extends Application {

	public static Stage primaryStage;
	private Controller controller;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ChatApp.primaryStage = primaryStage;
		controller = new Controller();
		
		primaryStage.setTitle("Chat");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
