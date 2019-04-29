package edu.file.encryption.app;

import edu.file.protocol.component.enums.ConnectionStatus;
import edu.file.protocol.component.interfaces.ConnectionEventHandler;
import javafx.scene.control.ProgressBar;

public class FileTransferEventHandler implements ConnectionEventHandler {

	private ProgressBar sendProgressBar;

	public FileTransferEventHandler(ProgressBar sendProgressBar) {
		this.sendProgressBar = sendProgressBar;
	}

	@Override
	public void reportTransferProgress(double v) {

	}

	@Override
	public void reportStatus(ConnectionStatus connectionStatus) {

	}
}
