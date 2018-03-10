package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnect {

	private static DBConnect instance = null;
	private Connection con;
	
	private  DBConnect() {
		try {
			con  = DriverManager.getConnection(	Prop.get("url")+Prop.get("db"),
												Prop.get("usr"),
												Prop.get("pwd")	);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnect getInstance(){
		if(instance == null){
			instance = new DBConnect();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
}
