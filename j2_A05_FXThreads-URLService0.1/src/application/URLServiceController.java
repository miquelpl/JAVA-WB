package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;

public class URLServiceController implements Initializable {

	@FXML TextArea textArea;
	@FXML ListView<Hyperlink> listView;
	@FXML TextField textFieldURL;
	@FXML Button buttonStart;
	@FXML ProgressBar progressBar;
	@FXML WebView webView;
	@FXML Tab tabSourceCode;
	@FXML Tab tabLinks;
	@FXML Tab tabBrowser;

	@Override public void initialize(URL location, ResourceBundle resources) {

		textFieldURL.setText("https://www.gutenberg.org/files/2701/2701-h/2701-h.htm"); 
		//textFieldURL.setText("https://www.oracle.com"); 
		ServiceSourceCode serviceSourceCode = new ServiceSourceCode();
		textArea.textProperty().bind(serviceSourceCode.valueProperty());
		progressBar.progressProperty().bind(serviceSourceCode.progressProperty());

		ServiceLinks serviceLinks = new ServiceLinks();

		serviceLinks.valueProperty().addListener( (a, oldValue, newValue)->{
			listView.setItems(FXCollections.observableList(newValue));
			listView.getItems().forEach(t-> t.setOnAction(u->webView.getEngine().load(t.getText())));
		} );
		
		buttonStart.setOnAction(e->{
			webView.getEngine().load(textFieldURL.getText());
			serviceSourceCode.setUrl(textFieldURL.getText());
			serviceSourceCode.restart();
		});

		serviceSourceCode.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override public void handle(WorkerStateEvent event) {
				listView.setItems(null);
				serviceLinks.setHTML(textArea.getText());
				serviceLinks.restart();
			}
		});

	}

	@FXML public void handle(MouseEvent event) {
		System.out.println("ListView");
	}


	
}
