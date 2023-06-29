package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class gui12 {

    @FXML
    private Button categoriesgui33;
    @FXML
    private Button importgui34;
    @FXML
    private Button questionsgui21;
    private Stage currentStage; // Thêm biến currentStage để lưu trữ đối tượng Stage của gui11

    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }
    @FXML
    void categoriesgui33(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
            Parent gui33Parent = loader.load();
            Scene gui33Scene = new Scene(gui33Parent);
            Stage stage = new Stage();
            stage.setScene(gui33Scene);
            stage.show();
            // Đóng gui11
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void importgui34(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui34.fxml"));
            Parent gui34Parent = loader.load();
            Scene gui34Scene = new Scene(gui34Parent);
            Stage stage = new Stage();
            stage.setScene(gui34Scene);
            stage.show();
            // Đóng gui11
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void questionsgui21(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);

            Stage stage = new Stage();
            stage.setScene(gui21Scene);
            stage.show();
            // Đóng gui11
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
