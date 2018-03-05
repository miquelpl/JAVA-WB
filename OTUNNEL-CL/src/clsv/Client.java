package clsv;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import model.UserTabColumns;
import model.UserTables;

public class Client {

    private ServerInterface stub;
    private final String HOST = "192.168.2.107";
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST);
            stub = (ServerInterface) registry.lookup("OTUNNEL");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public ServerInterface getStub() {
    	return stub;
    }
    
}
