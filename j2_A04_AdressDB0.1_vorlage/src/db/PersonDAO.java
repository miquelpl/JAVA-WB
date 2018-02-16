package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Person;

public class PersonDAO implements dao.PersonDAO {

	private Connection con = null;

	public PersonDAO() {
		this.con = DBConnect.getInstance().getConnection();
	}

	@Override
	public List<Person> findAllPersons() {
		List<Person> dList = new ArrayList<>();

		try {
	        Statement selectAllStatement = con.createStatement();
	        ResultSet rs = selectAllStatement.executeQuery("SELECT * FROM adressen");

	        while (rs.next()) {
	    		dList.add(new Person(
	    				rs.getInt("id"), 
	    				rs.getString("vorname"),
	    				rs.getString("nachname"),
	    				rs.getString("plz"),
	    				rs.getString("ort"),
	    				rs.getString("strasse"),
	    				rs.getString("telefon"),
	    				rs.getString("mobil"),
	    				rs.getString("email")));
	    				
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(dList);
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

}
