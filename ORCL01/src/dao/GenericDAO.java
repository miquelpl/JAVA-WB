package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.HibernateUtil;
import model.hr.Countries;

public class GenericDAO {

	private Session session = null;
	
	public List<?> findAll(String select, Class<?> klasse) {
		session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery(select).addEntity(klasse);
		return(q.list());
	}
	
	public static void main(String[] args) {
		String dml = "SELECT * FROM COUNTRIES";
		GenericDAO dao = new GenericDAO();
		dao.findAll(dml, Countries.class).stream().forEach(e->System.out.println(e.toString()));
		dao.session.close();
	}

}
