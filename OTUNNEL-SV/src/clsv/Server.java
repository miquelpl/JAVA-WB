package clsv;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import dao.OracleDAO;
import model.UserTables;

public class Server implements ServerInterface {

    public Server() {
    	super();
    }

	public List<String> getTabColumns(String table) throws RemoteException {
		String[] namenArr = {"COUNTRY_ID","COUNTRY_NAME","REGION_ID","Ina","Maria"};
		List<String> namenList = Arrays.asList(namenArr);
		return(namenList);
	}

	public List<UserTables> getTables() throws RemoteException {
		OracleDAO oracle = new OracleDAO();
		return(oracle.getTables());
	}


}
