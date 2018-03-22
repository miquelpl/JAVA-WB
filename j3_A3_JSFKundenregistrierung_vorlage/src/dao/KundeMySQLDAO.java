package dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import db.DBConnect;
import model.Kunde;

public class KundeMySQLDAO implements KundeDAO {
	
	private Connection con;// TODO con.close
	
	public KundeMySQLDAO() {
		con = DBConnect.getInstance().getConnection();
	}
	
	// TODO Dummy
	/* (non-Javadoc)
	 * @see dao.KundeDAO#findKunde(java.lang.String, java.lang.String)
	 */
	@Override
	public Kunde findKunde(String usr, String pwd){
		Kunde kunde = null;
		try (PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM kunde WHERE username=? AND passwort=?")){
			ps.setString(1, usr);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kunde = new Kunde();
				kunde.setKundenNummer(rs.getInt("id"));
				kunde.setVorname(rs.getString("vorname"));
				kunde.setNachname(rs.getString("nachname"));
				kunde.setEmail(rs.getString("email"));
				kunde.setUsername(usr);
				kunde.setPasswort(pwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(kunde);
	}
	
	
	/* (non-Javadoc)
	 * @see dao.KundeDAO#storeNewKunde(model.Kunde)
	 */
	@Override
	public boolean storeNewKunde(Kunde newKunde){
		try {
	        Statement insertStatement = con.createStatement();

	        String dml = "INSERT INTO kunde (vorname, nachname, email, username, passwort ) VALUES ('"
					+newKunde.getVorname()+"', '"
					+newKunde.getNachname()+"', '"
					+newKunde.getEmail()+"', '"
					+newKunde.getUsername()+"', '"
					+newKunde.getPasswort()+"')";

			//System.out.println(dml);
			
			int n = insertStatement.executeUpdate(dml, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				int lastInsertId = rs.getInt(1); 
				newKunde.setKundenNummer(lastInsertId);
			}
			rs.close();
	        return(n>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		
	}
}
