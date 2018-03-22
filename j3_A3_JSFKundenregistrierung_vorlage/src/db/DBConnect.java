package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Prop;


public final class DBConnect {

	private static DBConnect instance = null;
	private Connection con;
	
	private  DBConnect() {
		try {
			
			Class.forName( "com.mysql.jdbc.Driver" ).newInstance();///!!!!Tomcat
			con  = DriverManager.getConnection(	Prop.get("url")+Prop.get("db"),
												Prop.get("usr"),
												Prop.get("pwd")
					);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
