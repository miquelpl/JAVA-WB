package clsv;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.DataResult;
import model.UserTabColumns;
import model.UserTables;

public interface ServerInterface extends Remote {
	List<UserTabColumns> getTabColumns(String table) throws RemoteException; 
	List<UserTables> getTables() throws RemoteException; 
	List<?> getRows(String table) throws RemoteException; 
	DataResult runSelect(String dml) throws RemoteException;
}
