package server;

import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.MessageObject;

public class MessageService extends Service<MessageObject>{
	
	private Socket clientSocket;
	private static Logger log = LogManager.getLogger(MessageService.class);

	@Override
	protected Task<MessageObject> createTask() {
		log.info(this.getClass().getName()+": createTask()");
		return new Task<MessageObject>(){
			@Override protected MessageObject call() throws Exception {
				log.info(this.getClass().getName()+": call()");
				//updateMessage("MessageService: call"); // ObjectInputstream erzeugen, Nachricht lesen, zurückgeben
				ObjectInputStream in;
				MessageObject text = null;
				try {
					in = new ObjectInputStream(clientSocket.getInputStream());
					text = (MessageObject) in.readObject();
				} catch (Exception e) {
					log.error(this.getClass().getName()+": method go() Fehler: "+ e.fillInStackTrace());					//e.printStackTrace();
				}
			
				return(text);
			}
		};
	}

	public void setClientSocket(Socket clientSocket) {
		log.info(this.getClass().getName()+": "+clientSocket.toString());
		this.clientSocket = clientSocket;
	}
}
