package com.example.btl_35.viewController;

import com.example.btl_35.dao.QuizDao;
import com.example.btl_35.entity.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class gui11 {
    @FXML
    private Button addquizgui51;
    @FXML
    private Button turn;
    @FXML
    private TableView<Quiz> tableView;
    @FXML
    private Label tenquiz;
    @FXML
    private SVGPath question_bank;
    @FXML
    void questionClick(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui12.fxml"));
            Parent root = loader.load();
            // Tạo một popup mới
            Popup popup = new Popup();
            popup.getContent().add(root);

            // Đặt vị trí hiển thị của popup
            Node source = (Node) event.getSource();
            double x = source.localToScreen(-9300, 0).getX();
            double y = source.localToScreen(-20000, 0).getY(); // Hiển thị popup phía trên
            popup.setAutoHide(true); // Tự động ẩn popup khi người dùng nhấp chuột ra ngoài
            popup.show(source, x, y);
            gui12 controller = loader.getController();
            controller.setCurrentStage((Stage) question_bank.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addquizgui51(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui51.fxml"));
            Parent gui51Parent = loader.load();
            Scene gui51Scene = new Scene(gui51Parent);
            Stage stage = (Stage) addquizgui51.getScene().getWindow();
            stage.setScene(gui51Scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        // Tạo các cột cho TableView
        TableColumn<Quiz, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Định dạng hiển thị của cột nameColumn
        nameColumn.setCellFactory(column -> {
            TableCell<Quiz, String> cell = new TableCell<Quiz, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        SVGPath icon = new SVGPath();
                        icon.setContent("M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0z M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l7-7z");
                        // Thiết lập kích thước của biểu tượng
                        icon.setScaleX(1);
                        icon.setScaleY(1);
                        // Đặt biểu tượng vào ô cell
                        setGraphic(icon);
                        icon.setStyle("-fx-fill: red;");
                        // Đặt text cho ô cell
                        setText(item);
						setFont(Font.font("Arial", 20));
						setStyle("-fx-background-color: white;-fx-padding: 5,0,0,0");

					} else {
                        setText(null);
                    }
                }
            };
            return cell;
        });
        nameColumn.setPrefWidth(790);
		nameColumn.setStyle("-fx-background-color: white;");

        // Thêm cột vào TableView
        tableView.getColumns().add(nameColumn);
        tableView.setStyle("-fx-background-color: white");

        // Lấy danh sách Quiz từ QuizDao
        List<Quiz> quizList = QuizDao.getInstance().selectALl();

        // Tạo ObservableList và thêm các đối tượng Quiz vào danh sách
        ObservableList<Quiz> data = FXCollections.observableArrayList();
        data.addAll(quizList);

        // Gán danh sách dữ liệu cho TableView
        tableView.setItems(data);

        // Thêm EventListener cho TableView
        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                Quiz selectedQuiz = tableView.getSelectionModel().getSelectedItem();
                if (selectedQuiz != null) {
                    // Thực hiện hành động chuyển đến giao diện 61
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui61.fxml"));
                        Parent root = loader.load();
                        // Thực hiện các thao tác khác tùy ý trên giao diện 61
                        gui61 controller = loader.getController();
                        controller.setQuizName(selectedQuiz.getName());
                        controller.setTimelimit(selectedQuiz.getLimit_Time());
                        // Lấy Stage hiện tại từ TableView
                        Stage stage = (Stage) tableView.getScene().getWindow();

                        // Tạo Scene mới từ giao diện 61
                        Scene scene = new Scene(root);

                        // Đặt Scene mới cho Stage
                        stage.setScene(scene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

}

