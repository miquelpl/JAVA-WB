package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.Person;

public class MySQLPersonDAO implements PersonDAO{
	private DBConnect dbconnect;

	public MySQLPersonDAO() {
		dbconnect = DBConnect.getInstance();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePerson(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePerson(int id, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		MySQLPersonDAO dao = new MySQLPersonDAO();
		System.out.println(dao.findAllPersons());
	}

}
