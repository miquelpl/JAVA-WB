package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import exception.DBConnectException;
import model.Person;

public class MySQLPersonDAO implements PersonDAO {

	private DBConnect dbconnect;
    

	
	
	public MySQLPersonDAO() throws DBConnectException {
	    dbconnect = DBConnect.getInstance();
		// Falls z.B. Logging dazu kommt, oder DAO noch wichtige andere "Aktionen" macht
// kann wieder try/catch gemacht werden
//		try {
//		} catch (DBConnectException e) {
//			// evtl. hier logger
//			throw new DAOException();
//		}
	}

	@Override
	public List<Person> findAllPersons() {
		// ArrayList oder ObservableArrayList erzeutgen
		// Statemnet
		// Select
		List<Person> personList = new ArrayList<>();
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement("SELECT * FROM adressen")){
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setOrt(rs.getString("ort"));
				p.setPlz(rs.getString("plz"));
				p.setStrasse(rs.getString("strasse"));
				p.setTelefon(rs.getString("telefon"));
				p.setMobil(rs.getString("mobil"));
				p.setEmail(rs.getString("email"));
				personList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personList;
	}

	@Override
	public boolean savePerson(Person person) {
		final String q = "INSERT INTO adressen (vorname,nachname,ort,plz,strasse, telefon,mobil,email) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement inserStatement = dbconnect.getConnection().prepareStatement(q);
			
			inserStatement.setString(1, person.getVorname());
			inserStatement.setString(2, person.getNachname());
			inserStatement.setString(3, person.getOrt());
			inserStatement.setString(4, person.getPlz());
			inserStatement.setString(5, person.getStrasse());
			inserStatement.setString(6, person.getTelefon());
			inserStatement.setString(7, person.getMobil());
			inserStatement.setString(8, person.getEmail());
			
			return inserStatement.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deletePerson(int id) {
		final String q = "DELETE FROM adressen WHERE id =?";
		try {
			PreparedStatement deleteStatement = dbconnect.getConnection().prepareStatement(q);
			deleteStatement.setInt(1, id);
			return deleteStatement.executeUpdate()==1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updatePerson(int id, String fieldName, String newValue) {
		String q = "UPDATE adressen SET " + fieldName + "= ? WHERE id = ?";
		try {
			PreparedStatement updateStatement = dbconnect.getConnection().prepareStatement(q);
			updateStatement.setString(1, newValue);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
			
			return updateStatement.getUpdateCount()==1;
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		MySQLPersonDAO dao;
		try {
			dao = new MySQLPersonDAO();
			System.out.println(dao.findAllPersons());
		} catch (DBConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
