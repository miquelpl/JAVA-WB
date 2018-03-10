package clsv;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.OracleDAO;
import db.OracleDBConnections;
import model.DataResult;
import model.MetaData;
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

	public boolean createConnection(String user, String pwd) throws RemoteException {
		Connection ok = null;
		OracleDBConnections connections = new OracleDBConnections();
		try {
			ok = connections.createConnection(user, pwd);
			System.out.println("connection "+user);
		} catch (SQLException e) {
			e.printStackTrace();
			return(false);
		}
		return(ok != null);
	}

	public DataResult runSelect(String user, String dml) throws RemoteException {
        List<List<Object>> data = new ArrayList<>();
        List<MetaData> metaData = new ArrayList<>();
		Connection con = null;
		
        System.out.println("runSelect: "+user+", "+dml);
        OracleDBConnections connections = new OracleDBConnections();
		try {
			con = connections.getConnection(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        System.out.println(dml);
		try (PreparedStatement ps = con.prepareStatement(dml)){
			
			ResultSet rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1 ; i <= columnCount ; i++) {
            	metaData.add(new MetaData(rs.getMetaData().getColumnName(i), rs.getMetaData().getColumnClassName(i)));
            }

            while (rs.next()) {
				List<Object> row = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DataResult(metaData, data);
	}

}
