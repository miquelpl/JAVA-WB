package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class SampleController implements Initializable{

	@FXML private TableView<Designpattern> tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Designpattern> dList = FXCollections.observableArrayList();
		//ObservableList<Designpattern> dList = FXCollections.observableArrayList(NORMALE_COLLECTION); z.B. ArrayList
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java2", "root", "");
	        Statement selectAllStatement = con.createStatement();
	        ResultSet rs = selectAllStatement.executeQuery("SELECT * FROM designpattern");

	        while (rs.next()) {
	    		dList.add(new Designpattern(rs.getString("name"), rs.getString("beschreibung")));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		tableView.setItems(dList);
		tableView.getSelectionModel().selectedItemProperty().addListener((a,b,c)->{
			System.out.println(c.getName());
		});
		
	}
	
	
}
