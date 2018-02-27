package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public Client(String message) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket serverSocket = new Socket("localhost", 1234);
		
		ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
		out.writeObject(message);		
		System.out.println(in.readObject());
		serverSocket.close();
	}
}
