package dao;

import java.util.List;

import model.Person;

public interface PersonDAO {
	/**
	 * 
	 * @return 
	 */
	public List<Person> findAllPersons();
	public boolean savePerson(Person person);
	public boolean deletePerson(int id);
	public boolean updatePerson(int id, String fieldName, String newValue);

}
