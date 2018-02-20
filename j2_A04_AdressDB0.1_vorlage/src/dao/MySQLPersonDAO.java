package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.Person;

public class MySQLPersonDAO implements PersonDAO{
	private DBConnect dbconnect;

	private int lastInsertId = 0;
	
	private Person neuePerson = null;

	public Person getNeuePerson() {
		return this.neuePerson;
	}

	public int getLastInsertId() {
		return this.lastInsertId;
	}

	public MySQLPersonDAO() {
		dbconnect = DBConnect.getInstance();
	}

	@Override
	public List<Person> findAllPersons() {
		return selectFilteredPerson("1", "1");
	}

	@Override
	public boolean savePerson(Person person) {

		neuePerson = new Person();
		
		try {
	        Statement insertStatement = dbconnect.getConnection().createStatement();

	        String dml = "INSERT INTO adressen (vorname, nachname, plz, ort, strasse, telefon, mobil, email) VALUES ('"
					+person.getVorname()+"', '"
					+person.getNachname()+"', '"
					+person.getPlz()+"', '"
					+person.getOrt()+"', '"
					+person.getStrasse()+"', '"
					+person.getTelefon()+"', '"
					+person.getMobil()+"', '"
					+person.getEmail()+"')";

			//System.out.println(dml);
			
			int n = insertStatement.executeUpdate(dml, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				lastInsertId = rs.getInt(1); 
				person.setId(lastInsertId);
				neuePerson = person;
				neuePerson.setId(lastInsertId);
			}

	        return(n>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletePerson(int id) {
		try {
	        PreparedStatement updateStatement = dbconnect.getConnection().prepareStatement("DELETE FROM adressen WHERE id=?");
	        updateStatement.setInt(1, id);
	        int u = updateStatement.executeUpdate();
	        System.out.println("Gel�scht: "+u);
	        return(u>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePerson(int id, String fieldName, String newValue) {
		try {
	        PreparedStatement updateStatement = dbconnect.getConnection().prepareStatement("UPDATE adressen SET "+fieldName+"='"+newValue+"' WHERE id=?");
	        updateStatement.setInt(1, id);
	        int u = updateStatement.executeUpdate();
	        System.out.println("Ge�ndert: "+u);
	        return(u>0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Person> selectFilteredPerson(String fieldName, String value) {

		List<Person> personList = new ArrayList<>();
		String whereFeld = null;
		String whereValue = null;

		if(value==null||value=="") {
			whereFeld = "1";
			whereValue = "1";
		}
		else {
			whereFeld = fieldName;
			whereValue = value;
		}

		String dml = "SELECT * FROM adressen WHERE "+whereFeld+"=?";
        System.out.println(dml);
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement(dml)){
			ps.setString(1, whereValue);
			
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

	public static void main(String[] args) {
		MySQLPersonDAO dao = new MySQLPersonDAO();
		System.out.println(dao.findAllPersons());
	}

}
