package db;

import java.sql.Connection;

public class DBConnectProSchema {

	private Connection connection;
	private String schemaName;
	
	public DBConnectProSchema(Connection connection, String schemaName) {
		this.connection = connection;
		this.schemaName = schemaName;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}
