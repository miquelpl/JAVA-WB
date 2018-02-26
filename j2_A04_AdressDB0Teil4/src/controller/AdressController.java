package controller;

import org.omg.Messaging.SyncScopeHelper;

import dao.MySQLPersonDAO;
import dao.PersonDAO;
import exception.DBConnectException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import model.Person;

public class AdressController {

	@FXML
	private TableView<Person> adressTableView;

	@FXML
	private TableColumn<Person, Integer> idCol;

	@FXML
	private TableColumn<Person, String> vornameCol;

	@FXML
	private TableColumn<Person, String> nachnameCol;

	@FXML
	private TableColumn<Person, String> plzCol;

	@FXML
	private TableColumn<Person, String> ortCol;

	@FXML
	private TableColumn<Person, String> strasseCol;

	@FXML
	private TableColumn<Person, String> telefonCol;

	@FXML
	private TableColumn<Person, String> mobilCol;

	@FXML
	private TableColumn<Person, String> emailCol;

	@FXML
	private TextField vornameFiled;

	@FXML
	private TextField nachnameField;

	@FXML
	private TextField plzField;

	@FXML
	private TextField ortField;

	@FXML
	private TextField strasseField;

	@FXML
	private TextField telfonField;

	@FXML
	private TextField mobilField;

	@FXML
	private TextField emailField;

	@FXML
	private Label infoLabel;
	// -----------------Member

	private PersonDAO dao;

	@FXML
	void saveAction(ActionEvent event) {
		boolean saved = dao.savePerson(
				new Person(vornameFiled.getText(), nachnameField.getText(), ortField.getText(), plzField.getText(),
						strasseField.getText(), telfonField.getText(), mobilField.getText(), emailField.getText()));

		System.out.println("saved? " + saved);
		if (saved) {
			setInfoText("Datensatz gespeichert!");
		}
	}

	@FXML
	void initialize() {
		try {
			System.out.println("Controller.initialize");//log:debug
			dao = new MySQLPersonDAO();
			
			System.out.println("Controller.new MySQLPersonDAO: "+dao);//log:debug
			
			setupPersonTableView();
			setupTabelViewContextMenu();
			refresh();
		} catch (DBConnectException e) {
			Alert alert = new  Alert(AlertType.ERROR);
			alert.setContentText("Fehler bei der Verbindung mit der Datenbank, bitte DB erbinden und Neustart");
			alert.showAndWait();
			Platform.exit();
		}
	}

	/*
	 * Events
	 */
	@FXML
	public void refreshAction(ActionEvent event) {
		refresh();

	}

	/*
	 * update
	 */
	@FXML
	public void onEditCommit(CellEditEvent<Person, String> c) {
		String newValue = c.getNewValue();
		System.out.println("commit.newValue: " + newValue);
		Person p = c.getRowValue();
		String dbField = c.getTableColumn().getId();

		System.out.println("commit.dbfield: " + dbField);
		boolean updated = dao.updatePerson(p.getId(), dbField, newValue);
		if (updated) {
			setInfoText("Upadate erfolgreich: " + c.getTableColumn().getText() + ", id: " + p.getId());
		}
	}

	private void setupTabelViewContextMenu() {
		// ContextMenu
		ContextMenu cm = new ContextMenu();

		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(e -> {// TODO evtl. auslagern
			Person p = adressTableView.getSelectionModel().getSelectedItem();// .getSelectedItems();
			System.out.println(p);
			boolean deleted = dao.deletePerson(p.getId());
			if (deleted) {
				setInfoText("Datensatz mit der Id " + p.getId() + " gelöscht!");
				adressTableView.getItems().remove(p);
			}

		});
		cm.getItems().add(deleteItem);
		adressTableView.setContextMenu(cm);
	}

	private void setupPersonTableView() {
		// oder im FXML
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		plzCol.setCellValueFactory(new PropertyValueFactory<>("plz"));
		ortCol.setCellValueFactory(new PropertyValueFactory<>("ort"));
		strasseCol.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		telefonCol.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		mobilCol.setCellValueFactory(new PropertyValueFactory<>("mobil"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		telefonCol.setCellFactory(TextFieldTableCell.forTableColumn());
		mobilCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	private void setInfoText(String msg) {
		FadeTransition ft = new FadeTransition(Duration.seconds(3), infoLabel);
		ft.setFromValue(1);// 100%
		ft.setToValue(0);
		ft.setDelay(Duration.seconds(1));
		ft.play();
		infoLabel.setText(msg);
	}

	private void refresh() {
		adressTableView.setItems(FXCollections.observableArrayList(dao.findAllPersons()));// ArrayList zu ObservableList
	}
}
