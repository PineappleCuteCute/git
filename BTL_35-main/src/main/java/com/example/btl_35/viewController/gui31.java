package com.example.btl_35.viewController;

import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class gui31 {

	@FXML
	private CheckBox showall;
	@FXML
	private Button categoriestogui33;
	@FXML
	private Button create;
	@FXML
	private Button importtogui34;
	@FXML
	private Label show1;
	@FXML
	private Label show2;
	@FXML
	private TableView<Question> showquestion;
	@FXML
	private TextField selectcategory;
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
	void showall(ActionEvent event) {

	}

	@FXML
	void categoriestogui33(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
			Parent gui33Parent = loader.load();
			Scene gui33Scene = new Scene(gui33Parent);

			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui33Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void importtogui34(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui34.fxml"));
			Parent gui34Parent = loader.load();
			Scene gui34Scene = new Scene(gui34Parent);

			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui34Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showgui32(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32.fxml"));
			Parent gui32Parent = loader.load();
			Scene gui32Scene = new Scene(gui32Parent);

			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui32Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setCategoryName(String categoryName) {
		selectcategory.setText(categoryName);
	}
	public void setCheck(boolean checked) {
		showall.setSelected(checked);
	}
	public void initialize(Category selectedCategory, List<Question> questionList) {
		showall.setDisable(true);
		// Set up the columns in the TableView (assumed column names: "Name", "Category", etc.)
		TableColumn<Question, String> questionColumn = new TableColumn<>("");
		// Định dạng hiển thị của cột nameColumn
		questionColumn.setCellFactory(column -> {
			TableCell<Question, String> cell = new TableCell<Question, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						SVGPath icon = new SVGPath();
						icon.setContent("M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z");
						// Thiết lập kích thước của biểu tượng
						icon.setScaleX(1);
						icon.setScaleY(1);
						// Đặt biểu tượng vào ô cell
						setGraphic(icon);
						icon.setStyle("-fx-fill: #3399FF;");
						// Đặt text cho ô cell
						setText(item);
						setFont(Font.font("Arial", FontWeight.NORMAL,16));
						setStyle("-fx-padding: 10,0,0,10");

					} else {
						setText(null);
					}
				}
			};
			return cell;
		});
		questionColumn.setPrefWidth(826);
		questionColumn.setStyle("-fx-background-color: white;-fx-font-size:14;-fx-font-weight:normal;");
		questionColumn.setCellValueFactory(cellData -> {
			Question question = cellData.getValue();
			Integer questionId = question.getId();
			String questionName = question.getName();
			String displayText = questionId + ": " + questionName;
			return new SimpleStringProperty(displayText);
		});
		// Set up column header
		// Add the column to the TableView
		showquestion.getColumns().clear();
		showquestion.getColumns().add(questionColumn);
		showquestion.setStyle("-fx-background-color: white");
		// Populate the TableView with the questionList
		showquestion.getItems().addAll(questionList);
	}

}
