package clsv;

import java.rmi.RemoteException;
import java.util.List;
import dao.OracleDAO;
import model.Countries;
import model.DataResult;
import model.Tabellen;
import model.UserTabColumns;
import model.UserTables;

public class Server implements ServerInterface {

    public Server() {
    	super();
    }

	public List<UserTabColumns> getTabColumns(String table) throws RemoteException {
		OracleDAO oracle = new OracleDAO();
		return(oracle.getTabColumns(table));
	}

	public List<UserTables> getTables() throws RemoteException {
		OracleDAO oracle = new OracleDAO();
		return(oracle.getTables());
	}

	@Override
	public List<?> getRows(String table) throws RemoteException {
		OracleDAO oracle = new OracleDAO();
		return(oracle.getRows(table));
	}
	
	public DataResult runSelect(String dml) throws RemoteException {
		OracleDAO oracle = new OracleDAO();
		return(oracle.runSelect(dml));
	}

}
