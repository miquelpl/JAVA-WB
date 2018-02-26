package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.DBConnectException;

public final class DBConnect {

	private static DBConnect instance = null;
	private Connection con;
	
	private  DBConnect() throws DBConnectException {
		try {
			con  = DriverManager.getConnection(	Prop.get("url")+Prop.get("db"),
												Prop.get("usr"),
												Prop.get("pwd")
					);
		} catch (SQLException e) {
			//e.printStackTrace();
			//TODO log.error   
			
			throw new DBConnectException();
		}
	}
	
	public static DBConnect getInstance() throws DBConnectException{
		if(instance == null){
			instance = new DBConnect();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
}
