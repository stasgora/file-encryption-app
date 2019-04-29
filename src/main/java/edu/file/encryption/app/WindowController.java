package edu.file.encryption.app;

import edu.file.encryption.component.CryptoComponent;
import edu.file.encryption.component.interfaces.ICryptoComponent;
import edu.file.protocol.component.FileSender;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowController {

	private static final Logger LOGGER = Logger.getLogger(WindowController.class.getName());

	public Label inputFileLabel;
	public TextField outputFileName;
	public Button sendButton;
	public ProgressBar sendProgressBar;
	public Label stateLabel;
	public TextField recipient;
	private Stage stage;

	private ICryptoComponent cryptoComponent;
	private FileSender fileSender;

	private static final String BASE_INPUT_FILE_TEXT = "Input file: ";
	private static final String RECIPIENT_IP = "";

	public void init(Stage stage) {
		this.stage = stage;
		cryptoComponent = new CryptoComponent("user", "pass");
		try {
			fileSender = new FileSender(new FileTransferEventHandler(sendProgressBar), cryptoComponent, Inet4Address.getByName(RECIPIENT_IP));
		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE, "No recipient with IP " + RECIPIENT_IP + " found", e);
		}
	}

	public void sendFile(ActionEvent event) {

	}

	public void loadFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File");
		File file = fileChooser.showOpenDialog(stage);
		if(file == null || !file.exists()) {
			return;
		}
		setUIComponentActiveState(true);
		inputFileLabel.setText(BASE_INPUT_FILE_TEXT + file.getAbsolutePath());
	}

	private void setUIComponentActiveState(boolean active) {
		outputFileName.setDisable(!active);
		recipient.setDisable(!active);
	}

	public void exit(ActionEvent actionEvent) {
		Platform.exit();
	}

	public void keyTyped(KeyEvent event) {
		sendButton.setDisable(recipient.getText().isEmpty() || outputFileName.getText().isEmpty());
	}
}
