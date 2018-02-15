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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SampleController implements Initializable{

	ObservableList<Designpattern> dList = FXCollections.observableArrayList();
	Connection con = null;

	@FXML private TableView<Designpattern> tableView;

	@FXML private Button saveButton;
	@FXML private Button deleteButton;
	@FXML private TextField nameTextField;
	@FXML private TextField beschreibungTextField;

	// Event Listener on Button.onAction
	@FXML
	private void save(ActionEvent event) {
		dList.add(new Designpattern(nameTextField.getText(), beschreibungTextField.getText()));

		try {
	        Statement insertStatement = con.createStatement();
	        int n = insertStatement.executeUpdate("INSERT INTO designpattern (name, beschreibung) VALUES ('"+
	        		nameTextField.getText()+"', '"+beschreibungTextField.getText()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void delete(ActionEvent event) {
		try {
	        Statement insertStatement = con.createStatement();
	        int n = insertStatement.executeUpdate("DELETE FROM designpattern WHERE name='"+tableView.getSelectionModel().getSelectedItem().getName()+"'");
//	        System.out.printf("%s borrados: %d\n", "DELETE FROM designpattern WHERE name='"+tableView.getSelectionModel().getSelectedItem().getName()+"'", n);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dList.remove(tableView.getSelectionModel().getSelectedIndex());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//ObservableList<Designpattern> dList = FXCollections.observableArrayList(NORMALE_COLLECTION); z.B. ArrayList
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java2", "root", "");
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
