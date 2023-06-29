package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.entity.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class gui33v2 {

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
	private TextField parentcategory;
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
	public void setParentName2(String parentcategory2) {
		parentcategory.setText(parentcategory2);
	}
	int id;
	public int  setParentCategory(int selectedId) {
		id =selectedId;
		System.out.println(id);
		return selectedId;

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
			category.setParent(CategoryDao.getInstance().selectById(id));
			category.setInfo(categoryInfo.getText());
			category.setName(categoryName.getText());
			if (category != null) {
				CategoryDao.getInstance().insert(category);
			}
		});
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