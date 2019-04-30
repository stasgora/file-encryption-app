package edu.file.encryption.app;

import edu.file.encryption.component.CryptoComponent;
import edu.file.encryption.component.enums.CipherAlgorithmMode;
import edu.file.encryption.component.interfaces.ICryptoComponent;
import edu.file.protocol.component.FileSender;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
	public TextField recipient;
	public Button sendButton;
	public ProgressBar sendProgressBar;
	public Label stateLabel;
	public ChoiceBox algorithmMode;
	private Stage stage;

	private ICryptoComponent cryptoComponent;
	private FileSender fileSender;

	private File chosenFile;

	private static final String BASE_INPUT_FILE_TEXT = "Input file: ";
	private static final String RECIPIENT_IP = "127.0.0.1";

	public void init(Stage stage) {
		this.stage = stage;
		cryptoComponent = new CryptoComponent();
		try {
			fileSender = new FileSender(new FileTransferEventHandler(sendProgressBar, stateLabel), cryptoComponent, Inet4Address.getByName(RECIPIENT_IP));
		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE, "No recipient with IP " + RECIPIENT_IP + " found", e);
		}
		algorithmMode.getItems().setAll(CipherAlgorithmMode.values());
		algorithmMode.setValue(CipherAlgorithmMode.CBC);
	}

	public void sendFile(ActionEvent event) {
		fileSender.sendFile(chosenFile, (CipherAlgorithmMode) algorithmMode.getValue(), recipient.getText());
	}

	public void loadFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File");
		File file = fileChooser.showOpenDialog(stage);
		if(file == null || !file.exists()) {
			return;
		}
		chosenFile = file;
		stateLabel.setText("");
		setUIComponentActiveState(true);
		inputFileLabel.setText(BASE_INPUT_FILE_TEXT + file.getAbsolutePath());
	}

	private void setUIComponentActiveState(boolean active) {
		recipient.setDisable(!active);
		algorithmMode.setDisable(!active);
	}

	public void exit(ActionEvent actionEvent) {
		Platform.exit();
	}

	public void keyTyped(KeyEvent event) {
		sendButton.setDisable(recipient.getText().isEmpty());
	}
}
