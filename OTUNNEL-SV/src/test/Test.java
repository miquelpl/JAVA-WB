package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clsv.Server;
import clsv.ServerInterface;
import db.DBConnect;

public class Test {

	public static void main(String[] args) {
		DBConnect dbconnect = DBConnect.getInstance();
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement("SELECT SYSDATE FROM DUAL")){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "OTUNNEL";
    		ServerInterface server = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject( server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

}
