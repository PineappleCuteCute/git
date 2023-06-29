package com.example.btl_35.viewController;

import com.example.btl_35.dao.QuizDao;
import com.example.btl_35.entity.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class gui51 {
	@FXML
	private Button createQuiz;

	@FXML
	private DatePicker dateClose;


	@FXML
	private DatePicker dateOpen;

	@FXML

	private ComboBox<Integer> hourClose;

	@FXML
	private ComboBox<Integer> hourLimit;

	@FXML
	private ComboBox<Integer> hourOpen;

	@FXML
	private ComboBox<Integer> minuteClose;

	@FXML
	private ComboBox<Integer> minuteLimit;

	@FXML
	private ComboBox<Integer> minuteOpen;



	@FXML
	private TextArea quizDescription;

	@FXML
	private TextField quizName;


	@FXML
	private Button cancelgui11;
	@FXML
	void createQuiz(ActionEvent event) {
		String name = quizName.getText();
	}
	@FXML
	public void initialize() {
		ObservableList<Integer> numbers1 = FXCollections.observableArrayList();
		for (int i = 0; i <= 24; i++) {
			numbers1.add(i);
		}
		hourClose.setItems(numbers1);
		hourOpen.setItems(numbers1);
		hourLimit.setItems(numbers1);

		ObservableList<Integer> numbers2 = FXCollections.observableArrayList();
		for (int i = 0; i <= 59; i++) {
			numbers2.add(i);
		}
		minuteOpen.setItems(numbers2);
		minuteClose.setItems(numbers2);
		minuteLimit.setItems(numbers2);

		// Disable time selection initially
		disableTimeSelection(true);

		// Listen to changes in the quiz name field
		quizName.textProperty().addListener((observable, oldValue, newValue) -> {
			disableTimeSelection(newValue.isEmpty());
		});


		createQuiz.setOnMouseClicked(event -> {
			try {
				Quiz quiz = new Quiz();
				quiz.setName(quizName.getText());
				quiz.setDescription(quizDescription.getText());

				DateTimeUtils dateTimeUtils = new DateTimeUtils();
				LocalDateTime timeClose = dateTimeUtils.combineToDateTime(dateClose, hourClose, minuteClose);
				LocalDateTime timeOpen = dateTimeUtils.combineToDateTime(dateOpen, hourOpen, minuteOpen);

				if (timeClose != null && timeOpen != null) {
					LocalTime timeLimit = LocalTime.of(hourLimit.getValue(), minuteLimit.getValue());

					LocalDateTime endTime = LocalDateTime.of(dateOpen.getValue(), timeOpen.toLocalTime())
							.plusHours(timeLimit.getHour())
							.plusMinutes(timeLimit.getMinute());

					if (!endTime.isAfter(timeClose)) { // Check if endTime is not after timeClose
						quiz.setTimeClose(timeClose);
						quiz.setTimeOpen(timeOpen);
						quiz.setLimit_Time(timeLimit);
						QuizDao.getInstance().insert(quiz);

						System.out.println("Thêm quiz thành công");
						// Display success message and reload GUI 51
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Success");
						alert.setHeaderText("Quiz added successfully");
						alert.setContentText("Reload GUI 51");
						alert.showAndWait();

						try {
							// Close current GUI 51
							Stage currentStage = (Stage) createQuiz.getScene().getWindow();
							currentStage.close();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("gui51.fxml"));
							Parent gui51Parent = loader.load();
							Scene gui51Scene = new Scene(gui51Parent);

							gui51 controller = loader.getController();
							controller.setStage(stage); // Gán giá trị cho stage

							Stage stage = new Stage();
							stage.setScene(gui51Scene);
							stage.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Thời gian mở cửa và thời gian giới hạn phải nhỏ hơn hoặc bằng thời gian đóng cửa");

					}
				} else {
					System.out.println("Vui lòng chọn đầy đủ thời gian đóng cửa và thời gian mở cửa");


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});


	}


	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void cancelgui11(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui11.fxml"));
		Parent gui11Parent = loader.load();
		Scene gui11Scene = new Scene(gui11Parent);
		Stage stage = (Stage) cancelgui11.getScene().getWindow();
		stage.setScene(gui11Scene);
	}





	public class DateTimeUtils {
		public LocalDateTime combineToDateTime(DatePicker datePicker, ComboBox<Integer> hourObj, ComboBox<Integer> minuteObj) {
			LocalDate dateObj = datePicker.getValue();
			Integer hour = hourObj.getValue();
			Integer minute = minuteObj.getValue();

			if (dateObj == null || hour == null || minute == null) {
				return null; // Or handle the case when there is insufficient information selected
			}


			LocalDateTime localDateTime = LocalDateTime.of(dateObj, LocalTime.of(hour, minute));
			LocalDateTime combinedDateTime = convertUtilToSqlDate(localDateTime);

			return combinedDateTime;
		}

		private LocalDateTime convertUtilToSqlDate(LocalDateTime localDateTime) {
			long milliseconds = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
		}
	}


	public class TimeUtils {
		public Time combineToTime(int hour, int minute) {
			LocalTime localTime = LocalTime.of(hour, minute);
			Time combinedTime = Time.valueOf(localTime);

			return combinedTime;
		}

	}
	private void disableTimeSelection(boolean disable) {
		hourClose.setDisable(disable);
		hourOpen.setDisable(disable);
		hourLimit.setDisable(disable);
		minuteOpen.setDisable(disable);
		minuteClose.setDisable(disable);
		minuteLimit.setDisable(disable);

	}
}