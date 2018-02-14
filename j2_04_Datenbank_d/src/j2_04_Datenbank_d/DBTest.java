package j2_04_Datenbank_d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	public static void main(String[] args) {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java2", "root", "");
			
	        Statement selectAllStatement = con.createStatement();

	        ResultSet rs = selectAllStatement.executeQuery("SELECT * FROM designpattern");

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String beschreibung = rs.getString("beschreibung");
	            System.out.printf("%d %s \t\t %s\n", id, name, beschreibung);
	        
	        }

	        Statement insertStatement = con.createStatement();

	        int n = insertStatement.executeUpdate("INSERT INTO designpattern (name, beschreibung) VALUES ('DAO', 'KAPSELT db - Zugriff - Data Access Objekt')");
	        
	        System.out.println(n);
	        	
	        
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
