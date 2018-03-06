package app;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;

import clsv.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Countries;
import model.UserTabColumns;
import model.UserTables;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AppController {

	@FXML TreeView<String> treeView;
	@FXML TableView<?> tableView;
	@FXML TextArea textArea;

	 @FXML public void initialize() throws RemoteException {
		 
    	Client client = new Client();

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
    	
    	treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> treeViewOnClick(newValue));

    	List<Countries> countries = (List<Countries>) client.getStub().getRows("COUNTRIES");
    	
    	for(Countries country : countries) {
    		System.out.println(country.toString());
    	}

    	
	 }

	 private void treeViewOnClick(Object newValue) {      
		    System.out.println(newValue);
	}
	 
	
}
