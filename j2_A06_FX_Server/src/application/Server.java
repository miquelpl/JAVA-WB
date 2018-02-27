package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public Server() throws IOException, ClassNotFoundException {
		serverSocket = new ServerSocket(1234);
		System.out.println("antes accept");
		clientSocket = serverSocket.accept();
		System.out.println("despues accept");
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
	
	public void closeServerSocket() throws IOException {
		serverSocket.close();
	}
	
	

}
