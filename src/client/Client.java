package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.MessageObject;
import ui.MessengerController;

public class Client {

	private String serverhost;
	private static Logger log = LogManager.getLogger(Client.class);

	//Socket und Outputstream erzeugen und Nachricht schreiben
	public void writeMSG(MessageObject msgObj) throws UnknownHostException, IOException{
		log.info(this.getClass().getName()+" : writeMSG");
//		serverSocket.bind(new InetSocketAddress(serverhost, MessengerController.PORT));
		Socket serverSocket = new Socket(serverhost, MessengerController.PORT);
		ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
		out.writeObject(msgObj);	
		out.close();
		serverSocket.close();
	}

	public String getServerhost() {
		return serverhost;
	}
	
	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}
	
}
