package server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceServer extends Service<String>{
	
	private Server server;
	private static Logger log = LogManager.getLogger(ServiceServer.class);

	@Override protected Task<String> createTask() {
		Task<String> task = new Task<String>() {
			@Override protected String call() throws Exception, IOException {
				log.info(this.getClass().getName()+": call");
				server.serverSocketAccept();
				server.go();
				return("ok");
			}
		};
		return task;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
}
