package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.dao.QuizDao;
import com.example.btl_35.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import org.hibernate.type.TrueFalseType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javafx.scene.media.MediaView;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
public class gui32v2 {
    @FXML
    private Button cancel;
    @FXML
    private TextField choice1;
    @FXML
    private TextField choice2;
    @FXML
    private TextField choice3;
    @FXML
    private TextField defaultmark32;
    @FXML
    private ComboBox<String> grade1;
    @FXML
    private ComboBox<String> grade2;
    @FXML
    private ComboBox<String> grade3;
    @FXML
    private Button image32;
    @FXML
    private TextField questionName32;
    @FXML
    private TextArea questionText32;
    @FXML
    private Button savechanges;
    @FXML
    private Button savechangesandcontinue;
    @FXML
    private Button saveimage;
    @FXML
    private Button deleteimage;
    @FXML
    private TextField imagepath;
    @FXML
    private TextField selectedcategory2;
    @FXML
    private MediaView preview;
    @FXML
    private ImageView imageview;
    @FXML
    private WebView gifview;
    private File selectedImageFile;
    private String newImagepath;
    public void setCategoryName2(String categoryName2) {
        selectedcategory2.setText(categoryName2);
    }
    int id;
    public int  setCategory(int selectedId) {
        id =selectedId;

        return selectedId;
    }
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
    void cancelclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);

            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void image32click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image32.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        selectedImageFile = fileChooser.showOpenDialog(stage);
        if (selectedImageFile != null) {
            String filePath = selectedImageFile.getAbsolutePath();
            // Kiểm tra định dạng file
            String fileExtension = getFileExtension(selectedImageFile);
            if (fileExtension.equalsIgnoreCase("mp4")) {
                System.out.println("Đường dẫn tệp tin: " + filePath);
                try {
                    // Use FFprobe to get the video duration
                    ProcessBuilder processBuilder = new ProcessBuilder("src/main/java/com/example/btl_35/media/ffprobe.exe", "-v", "error", "-show_entries", "format=duration", "-of", "default=noprint_wrappers=1:nokey=1", filePath);
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String durationString = reader.readLine();
                    reader.close();
                    process.waitFor();
                    if (durationString != null) {
                        double durationSeconds = Double.parseDouble(durationString);
                        if (durationSeconds <= 10) {
                            System.out.println("Độ dài video hợp lệ: " + durationSeconds + " giây");
                            imagepath.setText(filePath);
                        } else {
                            System.out.println("Độ dài video lớn hơn 10 giây.");
                        }
                    } else {
                        System.out.println("Không thể lấy được độ dài video.");
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println("Lỗi khi kiểm tra độ dài video.");
                    e.printStackTrace();
                }
            }
            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("gif")) {
                // Đường dẫn tệp tin đã chọn có định dạng đúng
                System.out.println("Đường dẫn tệp tin: " + filePath);
                imagepath.setText(filePath);
            }
        }
    }

    @FXML
    void savechangesandcontinueclick(ActionEvent event) {

    }

    @FXML
    void savechangesclick(ActionEvent event) {

    }
    @FXML
    void saveimage1(ActionEvent event) {
        if (selectedImageFile != null) {
            String fileName = selectedImageFile.getName();
            File destinationDir = new File("src/main/java/com/example/btl_35/media");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Path destinationPath = new File(destinationDir, fileName).toPath();
            try {
                // Kiểm tra định dạng file
                String fileExtension = getFileExtension(selectedImageFile);
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath = newimagepath.toString();
                if(fileExtension.equalsIgnoreCase("mp4")) {
                    // Tạo đối tượng MediaPlayer
                    javafx.scene.media.Media media = new javafx.scene.media.Media(selectedImageFile.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    // Gán MediaPlayer vào MediaView để hiển thị video
                    preview.setMediaPlayer(mediaPlayer);
                    mediaPlayer.setAutoPlay(true);
                }
                else if (fileExtension.equalsIgnoreCase("gif")){
                    WebEngine webEngine = gifview.getEngine();
                    webEngine.loadContent("<img src=\"" + selectedImageFile.toURI().toString() + "\">");
                }
                else{
                    // Hiển thị hình ảnh trong ImageView
                    ImageView image = new ImageView(selectedImageFile.toURI().toString());
                    imageview.setImage(image.getImage());
                }
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void deleteimage1(ActionEvent event) {
        imagepath.clear();
        selectedImageFile = null;
        // Xóa video ở địa chỉ mới (nếu có)
        if (newImagepath != null) {
            File videoFile = new File(newImagepath);
            if (videoFile.exists()) {
                boolean deleted = videoFile.delete();
                if (deleted) {
                    System.out.println("Image đã được xóa: " + newImagepath);
                    preview.setMediaPlayer(null); // Remove media player from MediaView
                    imageview.setImage(null); // Clear the image in ImageView
                    gifview.getEngine().loadContent(""); // Clear the content in WebView
                    newImagepath = null;
                } else {
                    System.out.println("Không thể xóa Image: " + newImagepath);
                }
            }
            newImagepath = null;
        }
    }

    public void initialize() {
        // Tạo danh sách các mức điểm từ 100% đến -83.33%
        ObservableList<String> gradeList = FXCollections.observableArrayList(
                "100%", "90%","83.33333%" ,"80%","75%" ,"70%","66.66667%" ,"60%", "50%", "40%", "33.33333%","30%","25%" ,"20%","16.66667%","14.28571%","12.5%","11.11111%" ,"10%",
                "5%", "0%", "-5%", "-10%","-11.11111%","-12.5%","-14.28571%","-15%","-16.66667%", "-20%", "-25%", "-30%","-33.33333%", "-40%",
                "-50%", "-60%","-70%", "-75%", "-80%", "-83.33333%"
        );
        // Gán danh sách mức điểm vào ComboBox
        grade1.setItems(gradeList);
        grade2.setItems(gradeList);
        grade3.setItems(gradeList);
        // Mặc định ComboBox là "None"
        grade1.setValue("None");
        grade2.setValue("None");
        grade3.setValue("None");
        savechangesandcontinue.setOnMouseClicked(event -> {
            try {


                Question question = new Question();
                Category category = CategoryDao.getInstance().selectById(id);
                question.setCategory(category);
                question.setName(questionName32.getText());
                question.setText(questionText32.getText());
                int int_value = Integer.parseInt(defaultmark32.getText());
                question.setMark(int_value);

//                Set<Answer> answers = new HashSet<>();
//                Answer da1 = new Answer();
//                da1.setChoice(choice1.getText());
//                String input = grade1.getValue();
//                System.out.println(input);
//                String numberString = input.replaceAll("%", "");
//                System.out.println(numberString);
//                double number = (Double.parseDouble(numberString))/100.0;
//                da1.setGrade(number);
//                boolean boolValue = (number > 0);
//                da1.setIs_choice(boolValue);
//                answers.add(da1);
//                da1.setQuestion(question);

                Media media = new Media();
                media.setUrl(newImagepath);
                System.out.println("ok : "+newImagepath);
                Set<Answer> answers = new HashSet<>();
                if (!choice1.getText().isEmpty()) {
                    Answer da1 = createAnswer(choice1.getText(), grade1.getValue());
                    da1.setQuestion(question);
                    answers.add(da1);
                }
                if (!choice2.getText().isEmpty()) {
                    Answer da2 = createAnswer(choice2.getText(), grade2.getValue());
                    da2.setQuestion(question);
                    answers.add(da2);
                }
                if (!choice3.getText().isEmpty()) {
                    Answer da3 = createAnswer(choice3.getText(), grade3.getValue());
                    da3.setQuestion(question);
                    answers.add(da3);
                }
                question.setMedia(media);
                question.setAnswers(answers);
                QuestionDao.getInstance().insert(question);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return extension;
    }
    private void showAllNodes(TreeItem<String> item) {
        item.setExpanded(true);
        for (TreeItem<String> child : item.getChildren()) {
            showAllNodes(child);
        }
    }
    public static Answer createAnswer(String choiceText, String gradeInput) {
        Answer answer = new Answer();
        answer.setChoice(choiceText);

        String numberString = gradeInput.replaceAll("%", "");
        double number = Double.parseDouble(numberString) / 100.0;
        answer.setGrade(number);

        boolean boolValue = (number > 0);
        answer.setIs_choice(boolValue);

        return answer;
    }

}
