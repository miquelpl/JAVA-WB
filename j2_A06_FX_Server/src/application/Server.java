package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public Server() throws IOException, ClassNotFoundException {
		serverSocket = new ServerSocket(1234);
		clientSocket = serverSocket.accept();
	}

	public String go() throws IOException, ClassNotFoundException {
		ObjectInputStream in;
		String text = null;
		try {
			in = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			text = String.valueOf(in.readObject());
			out.writeObject(text.toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(text);
	}

	public void closeServerSocket() throws IOException {
		serverSocket.close();
	}

}
