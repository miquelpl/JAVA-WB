package ui;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MessageObject;
import server.MessageService;
import server.Server;

public class MessengerController implements Initializable{
	@FXML private TextField localServerTextField;
	@FXML private TextField nameTextField;
	@FXML private TextField messageTextField;
	@FXML private TextField toServerTextField;
	@FXML private TableView<MessageObject> messageTableView;
	
	private static Logger log = LogManager.getRootLogger();
	public static final int PORT = 1234;
	
	//----------------------- Member---------------------------
	private Client client;
	private Server server;
	private MessageService messageService;
	private ObservableList<MessageObject> messageList = FXCollections.observableArrayList();
	private Thread t1;
	private Task<Long> task1;

	// Server und Client erzeugen	// Listener für die empfangene Nachricht	// Nachricht an die Liste hängen
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info(this.getClass().getName()+": gestartet");
		Platform.runLater( ()-> {
			server = new Server();
			messageService = server.getService();
			messageTableView.setItems(messageList);

			messageService.valueProperty().addListener( (a, oldValue, newValue)->{
				log.info(this.getClass().getName()+": messageService.valueProperty().addListener");
				newValue.setName(nameTextField.getText());
				messageList.add(newValue);
			} );
			
			messageService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override public void handle(WorkerStateEvent event) {
					log.info(this.getClass().getName()+": messageService.setOnSucceeded");
					task1 = createTask1();
					t1 = new Thread(task1);
					t1.start();
				}
			});

		} );
	}

	// Event Listener on Button.onAction
	@FXML
	public void serverStartAction(ActionEvent event) throws IOException {
		log.info(this.getClass().getName()+": serverStartAction");
		// Server starten in einem  Task-Thread
		server.serverStart(localServerTextField.getText());
		task1 = createTask1();
		t1 = new Thread(task1);
		t1.start();
	}
	
	public Task<Long> createTask1() {
		return new Task<Long>() {
			@Override 
			protected Long call() throws Exception {
				log.info(this.getClass().getName()+": call to serverStart");
				server.go();
				return Long.valueOf(2);
			}
		};
	}

	// Event Listener on Button.onAction
	@FXML // Client setzt Serverhost
	public void connectToServerAction(ActionEvent event) {
		client = new Client();
		client.setServerhost(localServerTextField.getText());
	}

	// Event Listener on Button.onAction
	@FXML// nachricht senden und der Liste anhängen
	public void messageSendAction(ActionEvent event) {
		try {
			client.writeMSG(new MessageObject(nameTextField.getText(), messageTextField.getText(), LocalTime.now()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
