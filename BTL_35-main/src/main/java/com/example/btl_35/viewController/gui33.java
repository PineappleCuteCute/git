package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.entity.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;

public class gui33 {

	@FXML
	private Button addCategory;
	@FXML
	private TextArea categoryInfo;
	@FXML
	private TextField categoryName;
	@FXML
	private TextField idNumber;
	@FXML
	private Button importtogui34;
	@FXML
	private Button questiongui31;
	@FXML
	private Label show1;
	@FXML
	private TreeView<String> parentcategory;

	@FXML
	private TextField parentcategory2;
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
	void Category(ActionEvent event) {

	}

	@FXML
	void importtogui34(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui34.fxml"));
			Parent gui34Parent = loader.load();
			Scene gui34Scene = new Scene(gui34Parent);

			Stage currentStage = (Stage) importtogui34.getScene().getWindow();
			currentStage.setScene(gui34Scene);
			currentStage.show();
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
	void addCategory(ActionEvent event) {

	}
	@FXML
	void initialize() {
		addCategory.setOnMouseClicked(event -> {
			Category category = new Category();
			// category.setParent();
			category.setQuestionCount(0);
			String input = idNumber.getText();
			if (!input.isEmpty()) {
				try {
					int id = Integer.parseInt(input);
					if (isCategoryIdExists(id)) {
						// Xử lý khi ID đã tồn tại
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33Error.fxml"));
							Parent gui33Error = loader.load();
							Scene gui33ErrorScene = new Scene(gui33Error);
							Stage stage = (Stage) addCategory.getScene().getWindow();
							stage.setScene(gui33ErrorScene);
							stage.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						category.setId(id);
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33Success.fxml"));
							Parent gui33Success = loader.load();
							Scene gui33SuccessScene = new Scene(gui33Success);
							Stage stage = (Stage) addCategory.getScene().getWindow();
							stage.setScene(gui33SuccessScene);
							stage.show();
						} catch (IOException a) {
							a.printStackTrace();
						}
					}
				} catch (NumberFormatException e) {
					// Xử lý khi chuỗi nhập không phải là số nguyên hợp lệ
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33Error.fxml"));
						Parent gui33Error = loader.load();
						Scene gui33ErrorScene = new Scene(gui33Error);
						Stage stage = (Stage) addCategory.getScene().getWindow();
						stage.setScene(gui33ErrorScene);
						stage.show();
					} catch (IOException d) {
						d.printStackTrace();
					}
				}
			} else {
				// Xử lý khi chuỗi nhập rỗng
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33Error.fxml"));
					Parent gui33Error = loader.load();
					Scene gui33ErrorScene = new Scene(gui33Error);
					Stage stage = (Stage) addCategory.getScene().getWindow();
					stage.setScene(gui33ErrorScene);
					stage.show();
				} catch (IOException d) {
					d.printStackTrace();
				}
			}
			category.setInfo(categoryInfo.getText());
			category.setName(categoryName.getText());
			if (category != null) {
				CategoryDao.getInstance().insert(category);
			}
		});
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
		parentcategory.setRoot(root);
		showAllNodes(root);
		// Thêm CSS để item có font chữ Arial
		parentcategory.setCellFactory(treeView -> {
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
		parentcategory.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33v2.fxml"));
				Parent gui33v2Parent = loader.load();
				Scene gui33v2Scene = new Scene(gui33v2Parent);
				gui33v2 controller = loader.getController();
				TreeItem<String> selected = parentcategory.getSelectionModel().getSelectedItem();
				if (selected != null) {
					String parentcategory2 = selected.getValue();
					System.out.println("Selected category: " + parentcategory2);
					controller.setParentName2(parentcategory2);
					int selectedId = 0;
					for (Map.Entry<Integer, TreeItem<String>> entry : idToTree.entrySet()) {
						if (entry.getValue() == selected) {
							selectedId = entry.getKey();
							System.out.println(selectedId);
							controller.setParentCategory(selectedId);
							break;
						}
					}
				}
				Stage stage = (Stage) parentcategory.getScene().getWindow();
				stage.setScene(gui33v2Scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
	private void showAllNodes(TreeItem<String> item) {
		item.setExpanded(true);
		for (TreeItem<String> child : item.getChildren()) {
			showAllNodes(child);
		}
	}
	private boolean isCategoryIdExists(int id) {
		List<Category> categories = CategoryDao.getInstance().selectALl();
		for (Category category : categories) {
			if (category.getId() == id) {
				return true;
			}
		}
		return false;
	}

}