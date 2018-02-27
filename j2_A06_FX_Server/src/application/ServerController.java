package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ServerController {

	@FXML Button buttonServerStart;
	@FXML Label labelServerMessage;
	
	private Server server;

	@FXML void initialize() {
		ServiceServer serviceServer = new ServiceServer();
		
		buttonServerStart.setOnAction(e->{
			try {
				server = new Server();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			serviceServer.restart();
		});

		serviceServer.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override public void handle(WorkerStateEvent event) {
				System.out.println("serviceServer acabo");
			}
		});
		
	}
}
