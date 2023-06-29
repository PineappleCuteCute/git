package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class gui33Error {

    @FXML
    private Button error;

    @FXML
    void error(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
            Parent gui33Parent = loader.load();
            Scene gui33Scene = new Scene(gui33Parent);
            Stage stage = (Stage) error.getScene().getWindow();
            stage.setScene(gui33Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
