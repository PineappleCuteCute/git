package com.example.btl_35;

import com.example.btl_35.viewController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(gui11.class.getResource("gui11.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 861, 667);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}
