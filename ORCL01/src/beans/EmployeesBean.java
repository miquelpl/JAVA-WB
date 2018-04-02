package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.EmployeesDAO;
import model.hr.Employees;

@SessionScoped
@ManagedBean
public class EmployeesBean {

	private List<Employees> listEmployees;

	public EmployeesBean() {
	}

	@PostConstruct
    public void init() {
		EmployeesDAO dao = new EmployeesDAO();
		listEmployees = dao.findAll();
    }

	public List<Employees> getListEmployees() {
		EmployeesDAO dao = new EmployeesDAO();
		return dao.findAll();
	}

	public void setListEmployees(List<Employees> listEmployees) {
		this.listEmployees = listEmployees;
	}

	
}
