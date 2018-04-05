package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.HibernateUtil;
import model.hr.Employees;

public class EmployeesDAO {

	@SuppressWarnings("unchecked")
	public List<Employees> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("From Employees"); 
		return(q.list());
	}
	
	public static void main(String[] args) {
		EmployeesDAO dao = new EmployeesDAO();
		dao.findAll().stream().forEach(e->System.out.println(e.toString()));

	}

}
