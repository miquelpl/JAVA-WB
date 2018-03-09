package app;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DBPromptController extends DialogPane {
	
	@FXML private TextField server;
	@FXML private TextField datenbank;
	@FXML private TextField benutzer;
	@FXML private TextField port;
	@FXML private TextField pwd;
	@FXML private Button cancel;
	@FXML private Button speichern;
	@FXML DialogPane dbPrompt;
	
    public DBPromptController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DBPrompt.fxml"));
        fxmlLoader.setRoot(this.getParent());
        //fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	@FXML public void cancelOnClick(MouseEvent event) {
		System.out.println("cancel");
	}

	@FXML public void speichernOnClick(MouseEvent event) {
		System.out.println("start");
	}

	public TextField getServer() {
		return server;
	}

	public void setServer(TextField server) {
		this.server = server;
	}

	public TextField getDatenbank() {
		return datenbank;
	}

	public void setDatenbank(TextField datenbank) {
		this.datenbank = datenbank;
	}

	public TextField getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(TextField benutzer) {
		this.benutzer = benutzer;
	}

	public TextField getPort() {
		return port;
	}

	public void setPort(TextField port) {
		this.port = port;
	}

	public TextField getPwd() {
		return pwd;
	}

	public void setPwd(TextField pwd) {
		this.pwd = pwd;
	}


}
