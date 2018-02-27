package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ServerController {

	@FXML Button buttonServerStart;
	@FXML Label labelServerMessage;
	private Server server;
	
	@FXML void initialize() {
		ServiceServer serviceServer = new ServiceServer();
		labelServerMessage.textProperty().bind(serviceServer.valueProperty());

		buttonServerStart.setOnAction(e->{
			try {
				server = new Server();
				serviceServer.setServer(server);
				serviceServer.restart();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});

		serviceServer.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override public void handle(WorkerStateEvent event) {
				try {
					server.closeServerSocket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
