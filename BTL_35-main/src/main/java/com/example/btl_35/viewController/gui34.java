package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;

public class gui34 {

	@FXML
	private Button categoriesgui33;
	@FXML
	private Button questiongui31;
	@FXML
	private Button importfile;
	@FXML
	private AnchorPane importfile2;
	@FXML
	private Button importquestion;
	@FXML
	private Button returngui11;
	@FXML
	void returngui11(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui11.fxml"));
			Parent gui11Parent = loader.load();
			Scene gui11Scene = new Scene(gui11Parent);
			Stage stage = (Stage) returngui11.getScene().getWindow();
			stage.setScene(gui11Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void importfile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) importfile.getScene().getWindow();
		// Thiết lập tiêu đề cho hộp thoại chọn tệp tin
		fileChooser.setTitle("Chọn tệp tin");
		// Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			String filePath = selectedFile.getAbsolutePath();
			// Đường dẫn tệp tin đã chọn được lưu trong biến filePath
			System.out.println("Đường dẫn tệp tin: " + filePath);
		}
	}
	@FXML
	void initialize() {
		importfile2.setOnDragOver(this::handleDragOver);
		importfile2.setOnDragDropped(this::handleDragDropped);
	}

	private void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	private void handleDragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		boolean success = false;
		if (dragboard.hasFiles()) {
			success = true;
			for (File file : dragboard.getFiles()) {
				String filePath = file.getAbsolutePath();
				System.out.println("Đường dẫn tệp tin: " + filePath);
				// Thực hiện xử lý với tệp tin đã kéo và thả vào AnchorPane
			}
		}
		event.setDropCompleted(success);
		event.consume();
	}


	@FXML
	void Category(ActionEvent event) {

	}

	@FXML
	void categoriesgui33(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
			Parent gui33Parent = loader.load();
			Scene gui33Scene = new Scene(gui33Parent);

			Stage stage = (Stage) categoriesgui33.getScene().getWindow();
			stage.setScene(gui33Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void questiongui31(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
			Parent gui21Parent = loader.load();
			Scene gui21Scene = new Scene(gui21Parent);

			Stage currentStage = (Stage) questiongui31.getScene().getWindow();
			currentStage.setScene(gui21Scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void importquestion(ActionEvent event) {

	}
}
