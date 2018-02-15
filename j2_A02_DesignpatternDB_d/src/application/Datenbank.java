package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.collections.ObservableList;

public class Datenbank {

	public Connection con = null;

	public Datenbank() {

	}

	public void connect() {
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

	public void select(List<Designpattern> dList) {
		try {
	        Statement selectAllStatement = con.createStatement();
	        ResultSet rs = selectAllStatement.executeQuery("SELECT * FROM designpattern");

	        while (rs.next()) {
	    		dList.add(new Designpattern(rs.getString("name"), rs.getString("beschreibung")));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
