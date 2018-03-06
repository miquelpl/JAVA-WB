package app;

import java.rmi.RemoteException;
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
	
	private Client client;
	
	@FXML public void initialize() throws RemoteException {

		client = new Client();
    	initTree(client);
		tableView.setItems(dList);

//    	List<Countries> countries = (List<Countries>) client.getStub().getRows("COUNTRIES");
    	List<Tabellen> rows = (List<Tabellen>) client.getStub().getRows("COUNTRIES");
    	
    	System.out.println(rows);

		countryId.setCellValueFactory(new PropertyValueFactory<>("countryId"));
		countryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
		regionId.setCellValueFactory(new PropertyValueFactory<>("regionId"));
		regionName.setCellValueFactory(new PropertyValueFactory<>("regionName"));
		departmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
		departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		managerId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
		locationId.setCellValueFactory(new PropertyValueFactory<>("locationId"));
		
		tableView.getColumns().setAll(countryId, countryName, regionId);
    	dList.addAll(rows);    	
	 }

	 private void treeViewOnClick(String table) throws RemoteException {   

		List<Tabellen> rows = (List<Tabellen>) client.getStub().getRows(table); 
    	System.out.println(rows);

		switch(table) {
			case "COUNTRIES":
				tableView.getColumns().setAll(countryId, countryName, regionId);
				break;
			case "DEPARTMENTS":
				tableView.getColumns().setAll(departmentId, departmentName, managerId, locationId);
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
		TreeItem<String> rootItem = new TreeItem<>("ORCL", new ImageView (new Image(getClass().getResourceAsStream("../database.png"))));
    	TreeItem<String> user = new TreeItem<>("HR", new ImageView (new Image(getClass().getResourceAsStream("../user.png"))));
    	TreeItem<String> tables = new TreeItem<>("Tabellen", new ImageView (new Image(getClass().getResourceAsStream("../table_and_list.png"))));
    	TreeItem<String> views = new TreeItem<>("Views", new ImageView (new Image(getClass().getResourceAsStream("../tablebinding.png"))));

    	List<UserTables> userTablesList = (List<UserTables>) client.getStub().getTables();
        for(UserTables table : userTablesList) {
        	TreeItem<String> tableItem = new TreeItem<>(table.getTableName(), new ImageView (new Image(getClass().getResourceAsStream("../table.png")))); 

        	List<UserTabColumns> userTabColumnsList = (List<UserTabColumns>) client.getStub().getTabColumns(table.getTableName());
            for(UserTabColumns column : userTabColumnsList) {
            	TreeItem<String> columnItem = new TreeItem<>(column.getColumnName(), new ImageView (new Image(getClass().getResourceAsStream("../column.png")))); 
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
				treeViewOnClick(newValue.getValue());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private void tableDepartments() {
		
	}
	
}
