package clsv;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.UserTables;

public interface ServerInterface extends Remote {
	List<String> getTabColumns(String table) throws RemoteException; 
	List<UserTables> getTables() throws RemoteException; 
}
