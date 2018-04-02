package dao;

import java.lang.reflect.Field;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.HibernateUtil;
import model.hr.Employees;

public class GenericDAO {

	private Session session = null;
	
	public List<?> findAll(String select) {
		session = HibernateUtil.getSessionFactory().openSession();
//		Query q = session.createSQLQuery(select);
		Query q = session.createSQLQuery(select).addEntity(Employees.class);
		return(q.list());
	}
	
	public static void main(String[] args) {
		String dml = "SELECT * FROM EMPLOYEES";
		GenericDAO dao = new GenericDAO();
		dao.findAll(dml).stream().forEach(e->System.out.println(e.toString()));
		dao.session.close();
	}

}
