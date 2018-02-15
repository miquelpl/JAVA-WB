package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class Datenbank {

	private static Datenbank instance = null;

	public Connection con = null;

	private Datenbank() {
		
		connect();

	}

	public synchronized static Datenbank getInstance() {
		if (instance == null) {
			instance = new Datenbank();
		}
		return instance;
	}

	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java2", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String name, String beschreibung) {
		try {
	        Statement insertStatement = con.createStatement();
	        int n = insertStatement.executeUpdate("INSERT INTO designpattern (name, beschreibung) VALUES ('"+
	        		name+"', '"+beschreibung+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String name) {
		try {
	        Statement insertStatement = con.createStatement();
	        int n = insertStatement.executeUpdate("DELETE FROM designpattern WHERE name='"+name+"'");
//	        System.out.printf("%s borrados: %d\n", "DELETE FROM designpattern WHERE name='"+name()+"'", n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Designpattern> select() {
		List<Designpattern> dList = new ArrayList<>();

		try {
	        Statement selectAllStatement = con.createStatement();
	        ResultSet rs = selectAllStatement.executeQuery("SELECT * FROM designpattern");

	        while (rs.next()) {
	    		dList.add(new Designpattern(rs.getString("name"), rs.getString("beschreibung")));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(dList);
	}


}
