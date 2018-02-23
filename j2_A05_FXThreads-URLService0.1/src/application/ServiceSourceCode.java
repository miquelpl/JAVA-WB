package application;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceSourceCode extends Service<String>{
	
	private String adress;

	public void setUrl(String adress) {
		this.adress = adress;
	}

	@Override protected Task<String> createTask() {
		
		Task<String> task = new Task<String>() {
			@Override protected String call() throws Exception, IOException {
				URL url = new URL(adress);
				StringBuilder sb = new StringBuilder();
				try(Scanner sc = new Scanner(url.openStream())) {
					final long MAX =1000;
					int i = 0;
					while(sc.hasNext()){
						sb.append(sc.nextLine()+"\n");
						updateProgress(i++, MAX);
					}
				} 
				
				return sb.toString();
			}
		};
		return task;
	}


}
