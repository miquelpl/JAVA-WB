package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import dao.GenericDAO;

@ViewScoped
@ManagedBean
public class GenericTableBean {

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<Class<?>> table;
	private String className;
	private Class<?> selectedRow;

	private static final Map<String, String> tableToClass = new HashMap<String, String>();
	static {
		tableToClass.put("COUNTRIES", "Countries");
		tableToClass.put("DEPARTMENTS", "Departments");
		tableToClass.put("EMPLOYEES", "Employees");
		tableToClass.put("JOBS", "Jobs");
		tableToClass.put("LOCATIONS", "Locations");
		tableToClass.put("REGIONS", "Regions");
	}
	
	public GenericTableBean() {
		this.className = "EMPLOYEES";
	}

	@PostConstruct
    public void init() {
		GenericDAO dao = new GenericDAO();
		try {
			setTable(dao.findAll(tableToClass.get(className)));
			populateColumns();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

	public List<Class<?>> getTable() {
		return table;
	}
	public void setTable(List<Class<?>> table) {
		this.table = table;
	}
	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
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
		switch(tableToClass.get(this.className)) {
			case "Countries":
				columnKeys = new String[] {"countryId", "countryName"};
				break;
			case "Departments":
				columnKeys = new String[] {"departmentId", "departmentName"};
				break;
			case "Employees":
				columnKeys = new String[] {"employeeId", "firstName", "lastName", "email", "phoneNumber", "hireDate", "salary", "commissionPct" };
				break;
			case "Jobs":
				columnKeys = new String[] {"jobId", "jobTitle", "minSalary", "maxSalary"};
				break;
			case "Locations":
				columnKeys = new String[] {"locationId", "streetAddress", "postalCode", "city", "stateProvince"};
				break;
			case "Regions":
				columnKeys = new String[] {"regionId", "regionName"};
				break;
			default:
		}
		for (String columnKey : columnKeys) {
			columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
		}
	}

	public Class<?> getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(Class<?> selectedRow) {
		System.out.println("setSelectedRow: " + selectedRow.getName());
		this.selectedRow = (Class<?>)selectedRow;
	}
	public void onRowSelect(SelectEvent event) {
		Class<?> selected = (Class<?>) event.getObject();
		System.out.println("Row Selected: ");
		FacesMessage msg = new FacesMessage("Row Selected", "Roww");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

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