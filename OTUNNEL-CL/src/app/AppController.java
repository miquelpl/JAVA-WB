package app;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import clsv.Client;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DataResult;
import model.MetaData;
import model.Tabellen;
import model.UserTabColumns;
import model.UserTables;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.event.ActionEvent;

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
	private TreeItem<String> rootItem;
	
	@FXML TextArea dbmsTextArea;
	@FXML Button boton;
	@FXML ImageView runImage;
	@FXML ImageView stopImage;
	@FXML TabPane tabPane;

	private static Logger log = LogManager.getLogger();

	@FXML public void initialize() throws RemoteException {

		client = new Client();
    	initTree(client);
		tableView.setItems(dList);

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
		
	 }

	 private void treeViewOnClick(String table) throws RemoteException {   

		log.info("Table selected "+ table);

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
		rootItem = new TreeItem<>("ORCL", new ImageView (new Image(getClass().getResourceAsStream("/resource/database.png"))));
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
				if(newValue.getParent().getValue()=="Tabellen")
					treeViewOnClick(newValue.getValue());
			} catch (RemoteException e) {
				log.error(" Fehler: "+ e.fillInStackTrace());                               // e.printStackTrace();
			}
		});
	}

	@FXML private TextField server;
	@FXML private TextField datenbank;
	@FXML private TextField benutzer;
	@FXML private TextField port;
	@FXML private PasswordField password;
	@FXML private Button cancelButton;
	@FXML private Button createButton;

	@FXML public void buttonOnClick(MouseEvent event) {

		DBPromptController dbpc = new DBPromptController();
		
		Scene scene = new Scene(dbpc, 500, 300);
		Stage stage = new Stage();
		stage.setTitle("Neue Datenbank Verbindung");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();

//		try {
//			dbPrompt = (DialogPane)FXMLLoader.load(getClass().getResource("DBPrompt.fxml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//    	TreeItem<String> user = new TreeItem<>("SH", new ImageView (new Image(getClass().getResourceAsStream("/resource/user.png"))));
//		rootItem.getChildren().add(user);
		
	}

	@FXML public void runOnClick(MouseEvent event) throws SQLException {
		dbmsTextArea.appendText(textArea.getSelectedText()+"\n\n");
		String dml = textArea.getSelectedText();
		if(!dml.isEmpty()) {
			log.info("runSelect "+dml);
			try {
				DataResult rows = (DataResult) client.getStub().runSelect(dml);
				createTableView(rows);
				displayDataResult(rows);
			} catch (RemoteException e) {
				log.error(" Fehler: "+ e.fillInStackTrace());                               // e.printStackTrace();
			} 
		}
	}

	private void createTableView(DataResult rows) {
		
		TableView<List<Object>> table = new TableView<>();

		for (int i = 0 ; i < rows.getMetaData().size() ; i++) {
		    TableColumn<List<Object>, Object> column = new TableColumn<>(rows.getMetaData().get(i).getColumnName());
		    int columnIndex = i ;
		    column.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get(columnIndex)));
		    table.getColumns().add(column);
		}

		table.getItems().setAll(rows.getData());
		Tab tab = new Tab("SELECT "+tabPane.getTabs().size());
		tab.setContent(table);
		tabPane.getTabs().add(tab);
		
	}

	public void displayDataResult(DataResult rows) {
		for(MetaData metaData : rows.getMetaData()) {
			dbmsTextArea.appendText(metaData.getColumnName()+"\t\t");
		}
		dbmsTextArea.appendText("\n");
		printListObject(rows);
	}

	public void printListObject(DataResult rows) {
		for(List<Object> data : rows.getData()) {
			for(Object elem : data) {
				String value = (elem == null) ? "" : elem.toString();
				
				dbmsTextArea.appendText(value+"\t\t");
			}
			dbmsTextArea.appendText("\n");
		}
	}

	@FXML public void stopOnClick(MouseEvent event) {}

	@FXML public void createButtonOnClick(ActionEvent event) {
		boolean exist = false;
		for(TreeItem<String> elem : rootItem.getChildren()) {
			if(elem.getValue().compareTo(benutzer.getText())==0) {
				exist = true;
				break;
			}
		}
//System.out.println(exist);
//System.out.println("createButtonOnClick: "+rootItem.getChildren().stream().map(u-> u.getValue().compareTo(benutzer.getText())).count());
//if(rootItem.getChildren().stream().map(u-> u.getValue().compareTo(benutzer.getText())).count()==0) {
			if(!exist) {
System.out.println("usuario nuevo");
			DataResult rows = null;			
			try {
				String dml = "SELECT * FROM USER_TABLES";
				boolean ok = client.getStub().createConnection(benutzer.getText(), password.getText());
				if(ok) {
					System.out.println("ok = true");
					rows = (DataResult) client.getStub().runSelect(benutzer.getText(), dml);
					TreeItem<String> user = new TreeItem<>(benutzer.getText(), new ImageView (new Image(getClass().getResourceAsStream("/resource/user.png"))));
					rootItem.getChildren().add(user);
					printListObject(rows);
				}
			} catch (RemoteException e) {
				log.error(" Fehler: "+ e.fillInStackTrace());                               // e.printStackTrace();
			} 

		}
		
	}

	@FXML public void cancelButtonOnClick(ActionEvent event) {
		datenbank.setText("");
		benutzer.setText("");
		password.setText("");

	}

	
}
