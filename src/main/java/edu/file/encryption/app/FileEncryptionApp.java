package edu.file.encryption.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileEncryptionApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
		primaryStage.setTitle("File Encryption App");
		primaryStage.setScene(new Scene(root, 1000, 700));
		primaryStage.show();
		// Test
	}

	public static void main(String[] args) {
		launch(args);
	}

}
