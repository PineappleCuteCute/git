package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gui32 {


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
    private TreeView<String> showcategory1;
    private File selectedImageFile;
    private String newImagepath;
    @FXML
    private TextField selectedcategory2;
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
//        FileChooser fileChooser = new FileChooser();
//        Stage stage = (Stage) image32.getScene().getWindow();
//        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
//        fileChooser.setTitle("Chọn tệp tin");
//        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
//        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
//        fileChooser.getExtensionFilters().add(imageFilter);
//        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
//        selectedImageFile = fileChooser.showOpenDialog(stage);
//        if (selectedImageFile != null) {
//            String filePath = selectedImageFile.getAbsolutePath();
//            // Kiểm tra định dạng file
//            String fileExtension = getFileExtension(selectedImageFile);
//            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("mp4")|| fileExtension.equalsIgnoreCase("gif")) {
//                // Đường dẫn tệp tin đã chọn có định dạng đúng
//                System.out.println("Đường dẫn tệp tin: " + filePath);
//                imagepath.setText(filePath);
//            } else {
//                // Đường dẫn tệp tin không có định dạng đúng
//                System.out.println("Đường dẫn tệp tin không hợp lệ. Vui lòng chọn file PNG hoặc IMG.");
//            }
//        }
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
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath = newimagepath.toString();
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
                } else {
                    System.out.println("Không thể xóa Image: " + newImagepath);
                }
            }
            newImagepath = null;
        }
    }

    public void initialize() {
//        // Tạo danh sách các mức điểm từ 100% đến -83.33%
//        ObservableList<String> gradeList = FXCollections.observableArrayList(
//                "100%", "90%","83.33333%" ,"80%","75%" ,"70%","66,.66667%" ,"60%", "50%", "40%", "33.33333%","30%","25%" ,"20%","16.66667%","14.28571%","12.5%","11.11111%" ,"10%",
//                "5%", "0%", "-5%", "-10%","-11.11111%","-12.5%","-14.28571%","-15%","-16.66667%", "-20%", "-25%", "-30%","-33.33333%", "-40%",
//                "-50%", "-60%","-70%", "-75%", "-80%", "-83.33333%"
//        );
//
//        // Gán danh sách mức điểm vào ComboBox
//        grade1.setItems(gradeList);
//        grade2.setItems(gradeList);
//        grade3.setItems(gradeList);
//        // Mặc định ComboBox là "None"
//        grade1.setValue("None");
//        grade2.setValue("None");
//        grade3.setValue("None");
        List<Category> categories = CategoryDao.getInstance().selectALl();
        Map<Integer, TreeItem<String>> idToTree = new HashMap<>();
        TreeItem<String> root = new TreeItem<>("Top for IT");
        for(Category category : categories){
            int id = category.getId();
            String name =category.getName();
            String displayName = name;
            int questionCount = category.getQuestionCount();
            int parentID;
            if(questionCount>0){
                displayName+="("+questionCount+")";
            }
            if (category.getParent() != null){
                parentID = category.getParent().getId();
            }
            else{
                parentID = 0;
            }
            TreeItem<String> item = new TreeItem<>(displayName);
            idToTree.put(id, item);
            if(parentID == 0){
                root.getChildren().add(item);
            } else {
                TreeItem<String> parentItem = idToTree.get(parentID);
                parentItem.getChildren().add(item);
            }
        }
        showcategory1.setRoot(root);
        showAllNodes(root);
        // Thêm CSS để item có font chữ Arial
        showcategory1.setCellFactory(treeView -> {
            TreeCell<String> cell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                        setStyle("-fx-font-family: Arial;-fx-font-size: 16");
                    } else {
                        setText(null);
                        setStyle(null);
                    }
                }
            };
            return cell;
        });
        showcategory1.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32v2.fxml"));
                Parent gui32v2Parent = loader.load();
                Scene gui32v2Scene = new Scene(gui32v2Parent);
                gui32v2 controller = loader.getController();
                TreeItem<String> selected = showcategory1.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    String categoryName2 = selected.getValue();
                    System.out.println("Selected category: " + categoryName2);
                    controller.setCategoryName2(categoryName2);
                    int selectedId = 0;
                    for (Map.Entry<Integer, TreeItem<String>> entry : idToTree.entrySet()) {
                        if (entry.getValue() == selected) {
                            selectedId = entry.getKey();
                            System.out.println(selectedId);
                            controller.setCategory(selectedId);

                            break;
                        }
                    }
                }
                Stage stage = (Stage) showcategory1.getScene().getWindow();
                stage.setScene(gui32v2Scene);
                stage.show();
            } catch (IOException e) {
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
}
