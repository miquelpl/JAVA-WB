package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.EmployeesDAO;
import model.hr.Employees;

@ViewScoped
@ManagedBean
public class GenericTableBean {

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<Employees> table;

	public GenericTableBean() {
		populateColumns();
		setTable(null);
	}

	@PostConstruct
    public void init() {
		EmployeesDAO dao = new EmployeesDAO();
		setTable(dao.findAll());
    }

	public List<Employees> getTable() {
		return table;
	}

	public void setTable(List<Employees> table) {
		this.table = table;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public void populateColumns() {
		String[] columnKeys = new String[] { "employeeId", "firstName", "lastName", "email", "phoneNumber", "hireDate", "salary", "commissionPct" };
		for (String columnKey : columnKeys) {
			columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
		}
	}

	// getters and setters
	static public class ColumnModel implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}
}