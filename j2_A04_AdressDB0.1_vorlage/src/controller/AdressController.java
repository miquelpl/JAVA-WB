package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MySQLPersonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Person;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdressController implements Initializable {
	
	ObservableList<Person> dList = FXCollections.observableArrayList();

	@FXML private TableView<Person> tableView;
	@FXML	private TableColumn<Person, Integer> id;
	@FXML	private TableColumn<Person, String> vorname;
	@FXML	private TableColumn<Person, String> nachname;
	@FXML	private TableColumn<Person, String> plz;
	@FXML	private TableColumn<Person, String> ort;
	@FXML	private TableColumn<Person, String> strasse;
	@FXML	private TableColumn<Person, String> telefon;
	@FXML	private TableColumn<Person, String> mobil;
	@FXML	private TableColumn<Person, String> email;

	@FXML TextField nd_vorname;
	@FXML TextField nd_plz;
	@FXML TextField nd_nachname;
	@FXML TextField nd_strasse;
	@FXML TextField nd_ort;
	@FXML TextField nd_telefon;
	@FXML TextField nd_mobil;
	@FXML TextField nd_email;
	@FXML Button speichern;

	public void initialize(URL location, ResourceBundle resources) {
		MySQLPersonDAO personDAO = new MySQLPersonDAO(); 
		dList.addAll(personDAO.findAllPersons());
		tableView.setItems(dList);
		tableView.getSelectionModel().selectedItemProperty().addListener((a,b,c)->{
			System.out.println(a.getValue());
			System.out.println(c.getNachname());
			System.out.println(c.getVorname());
		});
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		plz.setCellValueFactory(new PropertyValueFactory<>("plz"));
		ort.setCellValueFactory(new PropertyValueFactory<>("ort"));
		strasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		mobil.setCellValueFactory(new PropertyValueFactory<>("mobil"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		email.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	@FXML private void save(ActionEvent event) {

		Person p = new Person();
		p.setId(null);
		p.setVorname(nd_vorname.getText());
		p.setNachname(nd_nachname.getText());
		p.setOrt(nd_ort.getText());
		p.setPlz(nd_plz.getText());
		p.setStrasse(nd_strasse.getText());
		p.setTelefon(nd_telefon.getText());
		p.setMobil(nd_mobil.getText());
		p.setEmail(nd_email.getText());
		MySQLPersonDAO personDAO = new MySQLPersonDAO();
		if(personDAO.savePerson(p)) {
			p.setId(personDAO.getLastInsertId());
			dList.add(p);
		}
		
	}
}
