package edu.file.encryption.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class WindowController {

	public Label inputFileLabel;
	public TextField outputFileName;
	public Button sendButton;
	public ProgressBar sendProgressBar;
	private Stage stage;

	public void init(Stage stage) {
		this.stage = stage;
	}

	public void loadFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File");
		File file = fileChooser.showOpenDialog(stage);
	}

	public void exit(ActionEvent actionEvent) {
		Platform.exit();
	}
}
