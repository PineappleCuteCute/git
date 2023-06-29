package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class gui61 {
	@FXML
	private SVGPath question_bank;
	@FXML
	private Button returngui11;

	@FXML
	private Label quizNameLabel;
	@FXML
	private Label quizNameLabel2;
	private String quizName; // Variable to store the quiz name
	@FXML
	private Label timelimit;
	@FXML
	private MenuButton gui62b;

	@FXML
	private SVGPath gui62b2;
	@FXML
	void gui62b(MouseEvent event) throws IOException{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui62.fxml"));
			Parent gui62Parent = loader.load();
			Scene gui62Scene = new Scene(gui62Parent);

			Stage stage = (Stage) gui62b.getScene().getWindow();
			stage.setScene(gui62Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
		quizNameLabel.setText(quizName);
		quizNameLabel2.setText(quizName);
	}
	public void setTimelimit(LocalTime time) {
		// Định dạng thời gian thành chuỗi theo định dạng "HH:mm:ss"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String timeString = time.format(formatter);

		// Đặt chuỗi thời gian làm nội dung cho label
		timelimit.setText(timeString);
	}

	@FXML
	void previewquiznow(ActionEvent event) {

	}

	@FXML
	void questionClick(MouseEvent event) {

	}

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
}
