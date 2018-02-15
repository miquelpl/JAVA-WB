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
	Datenbank meinDB = Datenbank.getInstance();

	@FXML private TableView<Designpattern> tableView;

	@FXML private Button saveButton;
	@FXML private Button deleteButton;
	@FXML private TextField nameTextField;
	@FXML private TextField beschreibungTextField;

	// Event Listener on Button.onAction
	@FXML
	private void save(ActionEvent event) {
		dList.add(new Designpattern(nameTextField.getText(), beschreibungTextField.getText()));
		meinDB.insert(nameTextField.getText(), beschreibungTextField.getText());
	}
	
	@FXML
	private void delete(ActionEvent event) {
		meinDB.delete(tableView.getSelectionModel().getSelectedItem().getName());
		dList.remove(tableView.getSelectionModel().getSelectedIndex());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dList.addAll(meinDB.select());
		tableView.setItems(dList);
		tableView.getSelectionModel().selectedItemProperty().addListener((a,b,c)->{
			System.out.println(c.getName());
		});
	}
	
}
