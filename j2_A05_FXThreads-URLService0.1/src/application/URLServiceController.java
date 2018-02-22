package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class URLServiceController implements Initializable {

	@FXML TextArea textArea;
	@FXML ListView<String> listView;
	@FXML TextField textFieldURL;
	@FXML Button buttonStart;
	@FXML ProgressBar progressBar;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

//		ObservableList<String> names = FXCollections.observableArrayList("Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
//		listView.setItems(names);
				
		ServiceSourceCode serviceSourceCode = new ServiceSourceCode();
		textArea.textProperty().bind(serviceSourceCode.valueProperty());
		progressBar.progressProperty().bind(serviceSourceCode.progressProperty());

		ServiceLinks serviceLinks = new ServiceLinks();
		listView.itemsProperty().bind((ObservableValue<? extends ObservableList<String>>) serviceLinks.getValue());
		
		buttonStart.setOnAction(e->{
			serviceSourceCode.setUrl(textFieldURL.getText());
			serviceSourceCode.restart();
		});

		
		serviceSourceCode.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override public void handle(WorkerStateEvent event) {
				serviceLinks.setHTML(textArea.getText());
				serviceLinks.restart();
			}
		});

		
		serviceLinks.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override public void handle(WorkerStateEvent event) {
//System.out.println(serviceLinks.valueProperty());
			}
		});
	
	}
	
}
