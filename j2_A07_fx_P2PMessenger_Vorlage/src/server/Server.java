package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	private ServerSocket serverSocket;
	private MessageService messageService;

	public Server() {
		try {
			serverSocket = new ServerSocket();
			messageService = new MessageService();// TODO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MessageService getService() {
		return messageService;
	}

	public void setService(MessageService service) {
		this.messageService = service;
	}


	
	// Server binden: serverSocket.bind(new InetSocketAddress(localIP, 1111));
	//Clientsocket erzeugen(holen), auf Message warten und
	// MessageService starten
	public void serverStart(String localhost){
		//hier...
		
	}

}
