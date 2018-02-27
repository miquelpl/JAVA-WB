package application;

import java.io.IOException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceServer extends Service<String>{

	@Override protected Task<String> createTask() {
		
		Task<String> task = new Task<String>() {
			@Override protected String call() throws Exception, IOException {
				return("fin");
			}
		};
		return task;
	}
}
