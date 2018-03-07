package app;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import clsv.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Countries;
import model.Regions;
import model.Tabellen;
import model.UserTabColumns;
import model.UserTables;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AppController {

	@FXML TreeView<String> treeView;
	@FXML TextArea textArea;

	ObservableList<Tabellen> dList = FXCollections.observableArrayList();
	@FXML private TableView<Tabellen> tableView;
	@FXML private TableColumn<Tabellen, String> countryId = new TableColumn<Tabellen, String>("COUNTRY ID");
	@FXML private TableColumn<Tabellen, String> countryName = new TableColumn<Tabellen, String>("COUNTRY NAME");
	@FXML private TableColumn<Tabellen, Integer> regionId = new TableColumn<Tabellen, Integer>("REGION ID");
	@FXML private TableColumn<Tabellen, String> regionName = new TableColumn<Tabellen, String>("REGION NAME");
	@FXML private TableColumn<Tabellen, Integer> departmentId = new TableColumn<Tabellen, Integer>("DEPARTMENT ID");
	@FXML private TableColumn<Tabellen, String> departmentName = new TableColumn<Tabellen, String>("DEPARTMENT NAME");
	@FXML private TableColumn<Tabellen, Integer> managerId = new TableColumn<Tabellen, Integer>("MANAGER ID");
	@FXML private TableColumn<Tabellen, Integer> locationId = new TableColumn<Tabellen, Integer>("LOCATION ID");
	@FXML private TableColumn<Tabellen, Integer> employeeId = new TableColumn<Tabellen, Integer>("EMPLOYEE ID");
	@FXML private TableColumn<Tabellen, String> firstName = new TableColumn<Tabellen, String>("FIRST NAME");
	@FXML private TableColumn<Tabellen, String> lastName = new TableColumn<Tabellen, String>("LAST NAME");
	@FXML private TableColumn<Tabellen, String> email = new TableColumn<Tabellen, String>("EMAIL");
	@FXML private TableColumn<Tabellen, String> phoneNumber = new TableColumn<Tabellen, String>("PHONE NUMBER");
	@FXML private TableColumn<Tabellen, LocalDate> hireDate = new TableColumn<Tabellen, LocalDate>("HIRE DATE");
	@FXML private TableColumn<Tabellen, String> jobId = new TableColumn<Tabellen, String>("JOB ID");
	@FXML private TableColumn<Tabellen, Integer> salary = new TableColumn<Tabellen, Integer>("SALARY");
	@FXML private TableColumn<Tabellen, Integer> commissionPct = new TableColumn<Tabellen, Integer>("COMISSION PCT");
	@FXML private TableColumn<Tabellen, LocalDate> startDate = new TableColumn<Tabellen, LocalDate>("START DATE");
	@FXML private TableColumn<Tabellen, LocalDate> endDate = new TableColumn<Tabellen, LocalDate>("END DATE");
	@FXML private TableColumn<Tabellen, String> jobTitle = new TableColumn<Tabellen, String>("JOB TITLE");
	@FXML private TableColumn<Tabellen, Integer> minSalary = new TableColumn<Tabellen, Integer>("MIN SALARY");
	@FXML private TableColumn<Tabellen, Integer> maxSalary = new TableColumn<Tabellen, Integer>("MIN SALARY");
	@FXML private TableColumn<Tabellen, String> streetAddress = new TableColumn<Tabellen, String>("STREET ADDRESS");
	@FXML private TableColumn<Tabellen, String> postalCode = new TableColumn<Tabellen, String>("POSTAL CODE");
	@FXML private TableColumn<Tabellen, String> city = new TableColumn<Tabellen, String>("CITY");
	@FXML private TableColumn<Tabellen, String> stateProvince = new TableColumn<Tabellen, String>("STATE PROVINCE");

	private Client client;
	
	@FXML public void initialize() throws RemoteException {

		client = new Client();
    	initTree(client);
		tableView.setItems(dList);

//    	List<Countries> countries = (List<Countries>) client.getStub().getRows("COUNTRIES");
//    	List<Tabellen> rows = (List<Tabellen>) client.getStub().getRows("COUNTRIES");
//    	System.out.println(rows);

		countryId.setCellValueFactory(new PropertyValueFactory<>("countryId"));
		countryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
		regionId.setCellValueFactory(new PropertyValueFactory<>("regionId"));
		regionName.setCellValueFactory(new PropertyValueFactory<>("regionName"));
		departmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
		departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		managerId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
		locationId.setCellValueFactory(new PropertyValueFactory<>("locationId"));
		employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		hireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
		jobId.setCellValueFactory(new PropertyValueFactory<>("jobId"));
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		commissionPct.setCellValueFactory(new PropertyValueFactory<>("commissionPct"));
		startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		jobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
		minSalary.setCellValueFactory(new PropertyValueFactory<>("minSalary"));
		maxSalary.setCellValueFactory(new PropertyValueFactory<>("maxSalary"));
		streetAddress.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));
		postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		city.setCellValueFactory(new PropertyValueFactory<>("city"));
		stateProvince.setCellValueFactory(new PropertyValueFactory<>("stateProvince"));
		
//		tableView.getColumns().setAll(countryId, countryName, regionId);
//    	dList.addAll(rows);    	
	 }

	 private void treeViewOnClick(String table) throws RemoteException {   

		List<Tabellen> rows = (List<Tabellen>) client.getStub().getRows(table); 

		switch(table) {
			case "COUNTRIES":
				tableView.getColumns().setAll(countryId, countryName, regionId);
				break;
			case "DEPARTMENTS":
				tableView.getColumns().setAll(departmentId, departmentName, managerId, locationId);
				break;
			case "EMPLOYEES":
				tableView.getColumns().setAll(employeeId, firstName, lastName, email, phoneNumber, hireDate, jobId, salary, commissionPct, managerId, departmentId);
				break;
			case "JOB_HISTORY":
				tableView.getColumns().setAll(employeeId, startDate, endDate, jobId, departmentId);
				break;
			case "JOBS":
				tableView.getColumns().setAll(jobId, jobTitle, minSalary, maxSalary);
				break;
			case "LOCATIONS":
				tableView.getColumns().setAll(locationId, streetAddress, postalCode, city, stateProvince, countryId);
				break;
			case "REGIONS":
				tableView.getColumns().setAll(regionId, regionName);
				break;
			default:
		}
		dList.clear();
		dList.addAll(rows);    	
	}
	 
	private void initTree(Client client) throws RemoteException {
		TreeItem<String> rootItem = new TreeItem<>("ORCL", new ImageView (new Image(getClass().getResourceAsStream("/resource/database.png"))));
    	TreeItem<String> user = new TreeItem<>("HR", new ImageView (new Image(getClass().getResourceAsStream("/resource/user.png"))));
    	TreeItem<String> tables = new TreeItem<>("Tabellen", new ImageView (new Image(getClass().getResourceAsStream("/resource/table_and_list.png"))));
    	TreeItem<String> views = new TreeItem<>("Views", new ImageView (new Image(getClass().getResourceAsStream("/resource/tablebinding.png"))));

    	List<UserTables> userTablesList = (List<UserTables>) client.getStub().getTables();
        for(UserTables table : userTablesList) {
        	TreeItem<String> tableItem = new TreeItem<>(table.getTableName(), new ImageView (new Image(getClass().getResourceAsStream("/resource/table.png")))); 

        	List<UserTabColumns> userTabColumnsList = (List<UserTabColumns>) client.getStub().getTabColumns(table.getTableName());
            for(UserTabColumns column : userTabColumnsList) {
            	TreeItem<String> columnItem = new TreeItem<>(column.getColumnName(), new ImageView (new Image(getClass().getResourceAsStream("/resource/column.png")))); 
            	tableItem.getChildren().add(columnItem);
            }
        	tables.getChildren().add(tableItem);
        }
    
    	rootItem.setExpanded(true);
    	user.setExpanded(true);
    	tables.setExpanded(true);

    	rootItem.getChildren().add(user);
    	user.getChildren().add(tables);
    	user.getChildren().add(views);
    	
    	treeView.setRoot(rootItem);
    	
    	treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				System.out.println(newValue.getParent().getValue());
				if(newValue.getParent().getValue()=="Tabellen")
					treeViewOnClick(newValue.getValue());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	
}
