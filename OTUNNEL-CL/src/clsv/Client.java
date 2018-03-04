package clsv;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import model.UserTables;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = "192.168.2.107";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            ServerInterface stub = (ServerInterface) registry.lookup("OTUNNEL");
            List<UserTables> response = (List<UserTables>) stub.getTables();
            System.out.println("response: " + response);
            for(UserTables elem : response) {
            	System.out.println(elem.getTableName());
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
