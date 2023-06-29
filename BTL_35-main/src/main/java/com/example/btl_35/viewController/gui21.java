package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gui21 {

	@FXML
	private CheckBox alsoquestion1;
	@FXML
	private Button categoriestogui33;
	@FXML
	private TreeView<String> showcategory;
	@FXML
	private Button create;
	@FXML
	private Button editcheckbox1;
	@FXML
	private Button editcheckbox2;
	@FXML
	private Button editcheckbox3;
	@FXML
	private Button importtogui34;
	@FXML
	private Label show1;
	@FXML
	private Label show2;
	@FXML
	private GridPane showQuestionGridPane;
	@FXML
	private TextField selectcategory;
	private boolean alsoquestion1Checked;
	private List<Question> questionList; // Updated variable
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
	void initialize() {
		List<Category> categories = CategoryDao.getInstance().selectALl();
		Map<Integer, TreeItem<String>> idToTree = new HashMap<>();
		TreeItem<String> root = new TreeItem<>("Top for IT");
		for (Category category : categories) {
			int id = category.getId();
			String name = category.getName();
			String displayName = name;
			int questionCount = category.getQuestionCount();
			int parentID;
			if (questionCount > 0) {
				displayName += "(" + questionCount + ")";
			}
			if (category.getParent() != null) {
				parentID = category.getParent().getId();
			} else {
				parentID = 0;
			}
			TreeItem<String> item = new TreeItem<>(displayName);
			idToTree.put(id, item);
			if (parentID == 0) {
				root.getChildren().add(item);
			} else {
				TreeItem<String> parentItem = idToTree.get(parentID);
				parentItem.getChildren().add(item);
			}
		}
		showcategory.setRoot(root);

		showAllNodes(root);
		showcategory.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("gui31.fxml"));
				Parent gui31Parent = loader.load();
				Scene gui31Scene = new Scene(gui31Parent);
				gui31 controller = loader.getController();
				// Pass the selected category and its questions to the gui31 controller
				TreeItem<String> selected = showcategory.getSelectionModel().getSelectedItem();
				if (selected != null) {
					String categoryName = selected.getValue();
					System.out.println("Selected category: " + categoryName);
					controller.setCategoryName(categoryName);
					int selectedId = 0;
					for (Map.Entry<Integer, TreeItem<String>> entry : idToTree.entrySet()) {
						if (entry.getValue() == selected) {
							selectedId = entry.getKey();
							break;
						}
					}
					Category selectedCategory = CategoryDao.getInstance().selectById(selectedId);
					alsoquestion1Checked = alsoquestion1.isSelected();
					controller.setCheck(alsoquestion1Checked);
					// Move the contents of the alsoquestion method here
					if (alsoquestion1.isSelected()) {
						// Use getAllQuestionsByCategoryId
						 questionList = CategoryDao.getInstance().getAllQuestionsByCategoryId(selectedId);
					} else {
						// Use getQuestionByCategoryID
						questionList = CategoryDao.getInstance().getQuestionByCategoryID(selectedId);
					}

					controller.initialize(selectedCategory, questionList);
				}

				Stage stage = (Stage) showcategory.getScene().getWindow();
				stage.setScene(gui31Scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// Thêm CSS để item có font chữ Arial
		showcategory.setCellFactory(treeView -> {
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

	}

	@FXML
	void Category(ActionEvent event) {
	}

	@FXML
	void alsoquestion(ActionEvent event) {

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

	@FXML
	void showgui32b(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32b.fxml"));
			Parent gui32bParent = loader.load();
			Scene gui32bScene = new Scene(gui32bParent);

			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui32bScene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAllNodes(TreeItem<String> item) {
		item.setExpanded(true);
		for (TreeItem<String> child : item.getChildren()) {
			showAllNodes(child);
		}
	}

}
