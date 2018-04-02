package dao;

public interface GenericInterfaceDAO {

	<T> T find(int id);
	<T> T find(String id);
	<T> T findAll();

}