package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class gui32b {

	@FXML
	private Button cancel2;

	@FXML
	private Button savechanges2;

	@FXML
	private Button savechangesandcontinue2;
	@FXML
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void cancel2(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
			Parent gui21Parent = loader.load();
			Scene gui21Scene = new Scene(gui21Parent);

			Stage stage = (Stage) cancel2.getScene().getWindow();
			stage.setScene(gui21Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void savechanges2(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
			Parent gui21Parent = loader.load();
			Scene gui21Scene = new Scene(gui21Parent);

			Stage stage = (Stage) savechanges2.getScene().getWindow();
			stage.setScene(gui21Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void savechangesandcontinue2(ActionEvent event) {

	}

}
