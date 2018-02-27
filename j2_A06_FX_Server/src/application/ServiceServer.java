package application;

import java.io.IOException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceServer extends Service<String>{
	
	private Server server;

	@Override protected Task<String> createTask() {
		Task<String> task = new Task<String>() {
			@Override protected String call() throws Exception, IOException {
				return(server.go());
			}
		};
		return task;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
}
