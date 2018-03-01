package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ui.MessengerController;

public class Server {

	private ServerSocket serverSocket;
	private MessageService messageService;
	private static Logger log = LogManager.getLogger(Server.class);
	private Socket clientSocket = null;

	public Server() {
		try {
			serverSocket = new ServerSocket();
			messageService = new MessageService();// TODO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Server binden: serverSocket.bind(new InetSocketAddress(localIP, 1111));
	//Clientsocket erzeugen(holen), auf Message warten und
	// MessageService starten
	public void serverStart(String localhost) throws IOException{
		log.info(this.getClass().getName()+": serverStart() Anfang");
		serverSocket.bind(new InetSocketAddress(localhost, MessengerController.PORT));
		//go();
	}

	public void go() throws IOException {
		clientSocket = serverSocket.accept();
		messageService.setClientSocket(clientSocket);
		messageService.restart();
	}

	public MessageService getService() {
		return messageService;
	}
	
	public void setService(MessageService service) {
		this.messageService = service;
	}
	
}
