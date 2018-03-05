package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppController  implements Initializable {

	@FXML TreeView<String> treeView;

//	 private final ImageView dbIcon = new ImageView (new Image(getClass().getResourceAsStream("../database.png")));
//	 private final ImageView userIcon = new ImageView (new Image(getClass().getResourceAsStream("../user.png")));
//	 private final ImageView tableIcon = new ImageView (new Image(getClass().getResourceAsStream("../table.png")));
//	 private final ImageView tablesIcon = new ImageView (new Image(getClass().getResourceAsStream("../table_and_list.png")));
//	 private final ImageView viewsIcon = new ImageView (new Image(getClass().getResourceAsStream("../tablebinding.png")));
//	 private final ImageView viewIcon = new ImageView (new Image(getClass().getResourceAsStream("../view.png")));

	 @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
    	TreeItem<String> rootItem = new TreeItem<>("ORCL", new ImageView (new Image(getClass().getResourceAsStream("../database.png"))));
    	rootItem.setExpanded(true);
    	TreeItem<String> user = new TreeItem<>("HR", new ImageView (new Image(getClass().getResourceAsStream("../user.png"))));
    	user.setExpanded(true);
    	TreeItem<String> tables = new TreeItem<>("Tabellen", new ImageView (new Image(getClass().getResourceAsStream("../table_and_list.png"))));
    	tables.setExpanded(true);
    	TreeItem<String> views = new TreeItem<>("Views", new ImageView (new Image(getClass().getResourceAsStream("../tablebinding.png"))));
    	tables.getChildren().addAll(
    			new TreeItem<>("CUSTOMERS", new ImageView (new Image(getClass().getResourceAsStream("../table.png")))), 
    			new TreeItem<>("EMPLOYEES", new ImageView (new Image(getClass().getResourceAsStream("../table.png")))), 
    			new TreeItem<>("DEPARTMENTS", new ImageView (new Image(getClass().getResourceAsStream("../table.png")))));

    	rootItem.getChildren().add(user);
    	user.getChildren().add(tables);
    	user.getChildren().add(views);
    	
    	treeView.setRoot(rootItem);
		
	}
	
}
