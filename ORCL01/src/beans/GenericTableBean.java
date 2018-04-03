package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dao.GenericDAO;
import model.hr.Employees;

@ViewScoped
@ManagedBean
public class GenericTableBean {

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<?> table;
	private String select;
	private String className;

	public GenericTableBean() {
		this.className = "Employees";
	}

	@PostConstruct
    public void init() {
		populateColumns();
System.out.println("init className: " + className);	
columns.stream().forEach(e->System.out.println(e.getHeader()));
		GenericDAO dao = new GenericDAO();
		try {
			setTable(dao.findAll(className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

	public List<?> getTable() {
		return table;
	}
	public void setTable(List<?> table) {
		this.table = table;
	}
	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
		System.out.println(className);
	}

	public void populateColumns() {
		String[] columnKeys = null;
		columns.clear();
		switch(this.className) {
			case "Employees":
				columnKeys = new String[] { "employeeId", "firstName", "lastName", "email", "phoneNumber", "hireDate", "salary", "commissionPct" };
				break;
			case "Countries":
				System.out.println("populateColumns " + className);
				columnKeys = new String[] { "countryId", "countryName", "regions"};
				break;
			case "Jobs":
				break;
			case "":
				break;
			default:
		}
		for (String columnKey : columnKeys) {
			columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
		}
	}

	public void onRowSelect(SelectEvent event) {
//		event.getComponent().getAttributes().
		select = "SELECT * FROM EMPLOYEES";
		className = "Employees";
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