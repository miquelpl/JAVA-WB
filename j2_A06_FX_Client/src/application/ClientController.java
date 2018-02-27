package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ClientController {

	@FXML TextField textFieldMessage;
	@FXML Button buttonSchicken;
	
	private Client client;
	
	@FXML void initialize() {
	
		buttonSchicken.setOnAction(e->{
			try {
				client = new Client(textFieldMessage.getText());
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});

	}

}
