package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.HibernateUtil;
import model.Tabellen;
import model.hr.Countries;

public class GenericDAO {

	private Session session = null;
	
	@SuppressWarnings("unchecked")
	public List<Class<?>> findAll(String select, Class<?> klasse) {
		session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery(select).addEntity(klasse);
		return(q.list());
	}
	
	@SuppressWarnings("unchecked")
	public List<Class<?>> findAll(String className) throws ClassNotFoundException {
		String select = "SELECT * FROM " + className.toUpperCase();
		Class<?> klasse = Class.forName("model.hr."+className);
		session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery(select).addEntity(klasse);
		return(q.list());
	}
	
	@SuppressWarnings("unchecked")
	public List<Tabellen> findAll2(String className) throws ClassNotFoundException {
		String select = "SELECT * FROM " + className.toUpperCase();
		Class<?> klasse = Class.forName("model.hr."+className);
		session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery(select).addEntity(klasse);
		return(q.list());
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		String dml = "SELECT * FROM COUNTRIES";
		GenericDAO dao = new GenericDAO();

		dao.findAll2("Locations").stream().forEach(e->System.out.println(e.toString()));

		dao.session.close();
	}

}
