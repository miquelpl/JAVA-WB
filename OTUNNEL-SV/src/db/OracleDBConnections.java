package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleDBConnections {
	
	public static List<DBConnectProSchema> connections;

	public OracleDBConnections() {
		if(connections == null) {
			connections = new ArrayList<>();
		}
	}

	public Connection getConnection(String user) throws SQLException {
		Connection con = null;
		for(DBConnectProSchema connection : connections) {
			if(connection.getSchemaName().compareTo(user)==0) {
				con = connection.getConnection();
				break;
			}
		}
		
		return(con);
	}

	public Connection createConnection(String user, String pwd) throws SQLException {
		Connection con = null;

		boolean exist = false;
		for(DBConnectProSchema connection : connections) {
			if(connection.getSchemaName().compareTo(user)==0) {
				exist = true;
				con = connection.getConnection();
				break;
			}
		}
		
		if(!exist) {
			con  = DriverManager.getConnection(Prop.get("url")+Prop.get("db"), user, pwd);
			connections.add(new DBConnectProSchema(con, user));
		}

		return(con);
	}
	
}
