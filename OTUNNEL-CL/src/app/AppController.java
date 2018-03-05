package app;

import java.rmi.RemoteException;
import java.util.List;

import clsv.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.UserTabColumns;
import model.UserTables;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeCell;

public class AppController {

	@FXML TreeView<String> treeView;
	@FXML TableView<String> tableView;
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
        	
        	//System.out.println(elem.getTableName());
        }
    
    	rootItem.setExpanded(true);
    	user.setExpanded(true);
    	tables.setExpanded(true);

    	rootItem.getChildren().add(user);
    	user.getChildren().add(tables);
    	user.getChildren().add(views);
    	
    	treeView.setRoot(rootItem);

    	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
    	    handleMouseClicked(event);
    	};
    	
    	treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
	}

	 private void handleMouseClicked(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    // Accept clicks only on node cells, and not on empty spaces of the TreeView
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
	        String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
	        System.out.println("Node click: " + name);
	    }
	}

	
}
