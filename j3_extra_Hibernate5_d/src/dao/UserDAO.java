package dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.HibernateUtil;
import model.User;

public class UserDAO {

	
	public void saveUser(User u) {
		Transaction tr = null;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.save(u);  // saveOrUpdate
			tr.commit();// hier wird gespeichert
		
			System.out.println(	wasCommitted(session));
			
			
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
	}

	private boolean wasCommitted(Session session) {
		return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
	}
	
	//--- Hibernate SQL (HQL)

	public List<User> findAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		TypedQuery<User> q = session.createQuery("From User"); // ************* Hibrenate 5 ********************
		return q.getResultList();
	}
	

	
	// HQL
	public List<User> findByNachname(String nachname){
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<User> q = session.createQuery("From User WHERE nachname =:n");// ************* Hibrenate 5 ********************
		q.setParameter("n", nachname);
		return q.getResultList();
	}
	
	//Criteria
	public List<User> findByExample(User exampleUser){
		Session session = HibernateUtil.getSessionFactory().openSession();
	//	Criteria criteria = session.createCriteria(User.class);
		
		// ******************** Hibernat 5 *****************************
		  CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
		  Root<User> root = criteriaQuery.from(User.class);
		  criteriaQuery.select(root);
		return session.createQuery(criteriaQuery).getResultList();
	}

	
	public void deleteUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		User deleteUser = (User) session.get(User.class, id);  // get (null) oder load (Exception)
		if(deleteUser!=null) {
			session.delete(deleteUser);
			tr.commit();
		}
		
	}
	
	public void updateEMail(int id, String newEmail) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		User updateUser = (User) session.get(User.class, id);  // get (null) oder load (Exception)
		if(updateUser!=null) {
			updateUser.setEmail(newEmail);
			session.update(updateUser);
			tr.commit();
			System.out.println(wasCommitted(session)); // true/false;
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserDAO dao = new UserDAO();
		User u = new User("Max","Meier","werw@ewrw.de","234324");
		
		dao.saveUser(u);
	//	System.out.println(dao.findAll());
	//	System.out.println(dao.findAllNative());

//		User exampleUser = new User();
//		exampleUser.setNachname("Meier");
//		System.out.println(dao.findByExample(exampleUser));
	}

}
