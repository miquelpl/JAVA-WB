package ui;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import server.ServiceServer;
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
	private ServiceServer serviceServer;

	// Server und Client erzeugen	// Listener für die empfangene Nachricht	// Nachricht an die Liste hängen
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info(this.getClass().getName()+": gestartet");
		Platform.runLater( ()-> {
			server = new Server();
			messageService = server.getService();
			messageTableView.setItems(messageList);
			serviceServer = new ServiceServer();
			
			messageService.valueProperty().addListener( (a, oldValue, newValue)->{
				log.info(this.getClass().getName()+": messageService.valueProperty().addListener");
				if(newValue != null) {
					newValue.setName(nameTextField.getText());
					messageList.add(newValue);
				}
			} );
			
			serviceServer.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override public void handle(WorkerStateEvent event) {
					log.info(this.getClass().getName()+": serviceServer.setOnSucceeded");
				}
			});

			messageService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override public void handle(WorkerStateEvent event) {
					log.info(this.getClass().getName()+": messageService.setOnSucceeded");
					messageList.add(messageService.valueProperty().getValue());
					serviceServer.restart();
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
		serviceServer.setServer(server);
		serviceServer.restart();
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
