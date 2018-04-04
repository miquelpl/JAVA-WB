package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import dao.EmployeesDAO;
import model.hr.Employees;

@SessionScoped
@ManagedBean
public class EmployeesBean {

	private List<Employees> listEmployees;
	private Employees selectedRow;

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

	public Employees getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(Employees selectedRow) {
		this.selectedRow = (Employees)selectedRow;
	}
	public void onRowSelect(SelectEvent event) {
		Employees selected = (Employees) event.getObject();
		System.out.println("Row Selected: " + selected.toString());
		FacesMessage msg = new FacesMessage("Employee Selected", Integer.toString(((Employees) event.getObject()).getEmployeeId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
}
