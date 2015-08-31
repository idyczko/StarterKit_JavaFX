package com.capgemini.bookservice.view;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.capgemini.bookservice.DataProvider;
import com.capgemini.bookservice.Main;
import com.capgemini.bookservice.model.AuthorVO;
import com.capgemini.bookservice.model.BookServiceModel;
import com.capgemini.bookservice.model.BookVO;

import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookServiceController {

	@FXML
	ResourceBundle resources;

	@FXML
	private TableView<BookVO> bookTable;

	@FXML
	private TableColumn<BookVO, String> titleColumn;

	@FXML
	private TableColumn<BookVO, String> authorColumn;

	@FXML
	private Label titleLabel;

	@FXML
	private Label authorLabel;

	@FXML
	private TableView<AuthorVO> authorTable;

	@FXML
	private TableColumn<AuthorVO, String> firstNameColumn;

	@FXML
	private TableColumn<AuthorVO, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Button searchButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button addAuthorButton;

	@FXML
	private Button removeAuthorButton;

	@FXML
	private TextField phraseField;

	@FXML
	private TextField titleField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private final BookServiceModel model = new BookServiceModel();
	private Main mainApp;

	public BookServiceController() {
	}

	@FXML
	private void addNewBook(ActionEvent event) {
		try {
			Task<BookVO> backgroundTask = new Task<BookVO>() {

				@Override
				protected BookVO call() throws Exception {

					BookVO returnedBook = dataProvider
							.saveBook(new BookVO(null, model.getTitle(), new HashSet<AuthorVO>(model.getAuthors())));
					model.getResult().add(returnedBook);
					titleField.clear();
					firstNameField.clear();
					lastNameField.clear();
					model.authorsProperty().clear();
					return returnedBook;
				}
			};

			new Thread(backgroundTask).start();
		} catch (Exception e) {
			showAlert("Exception encountered!", "Please perform action later, as the server is not available.");
		}
	}

	@FXML
	private void deleteBook(ActionEvent event) {
		if (bookTable.getSelectionModel().getSelectedIndex() >= 0) {
			try {
				Task<Boolean> backgroundTask = new Task<Boolean>() {

					@Override
					protected Boolean call() throws Exception {
						Boolean bookIsDeleted = false;
						bookIsDeleted = dataProvider
								.deleteBook(bookTable.getSelectionModel().getSelectedItem().getId());
						if (bookIsDeleted) {
							model.getResult().remove(bookTable.getSelectionModel().getSelectedItem());
						}
						return bookIsDeleted;
					}
				};

				new Thread(backgroundTask).start();
			} catch (Exception e) {
				showAlert("Exception encountered!", "Please perform action later, as the server is not available.");
			}
		} else {
			showAlert("Warning", "Please select book to be deleted from table!");
		}
	}

	@FXML
	private void addAuthor(ActionEvent event) {
		model.getAuthors().add(new AuthorVO(null, model.getFirstName(), model.getLastName()));
		firstNameField.clear();
		lastNameField.clear();
	}

	@FXML
	private void removeAuthor(ActionEvent event) {
		if (authorTable.getSelectionModel().getSelectedIndex() >= 0) {
			model.getAuthors().remove(authorTable.getSelectionModel().getSelectedItem());
		} else {
			showAlert("Warning", "Please select author to be deleted from table!");
		}
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {

		try {
			Task<Collection<BookVO>> backgroundTask = new Task<Collection<BookVO>>() {

				@Override
				protected Collection<BookVO> call() throws IOException {
					Collection<BookVO> books = dataProvider.findBooks(model.getPhrase());
					model.setResult(books);
					return books;
				}
			};

			new Thread(backgroundTask).start();
		} catch (Exception e) {
			showAlert("Exception encountered!", "Please perform action later, as the server is not available.");
		}
	}

	@FXML
	private void initialize() {

		titleColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getTitle());
		});
		authorColumn.setCellValueFactory(cellData -> {
			SimpleStringProperty simpleString = new SimpleStringProperty("");
			for (AuthorVO author : cellData.getValue().getAuthors()) {
				simpleString.set(simpleString.get() + author.getFirstName() + " " + author.getLastName() + ", ");
			}
			return simpleString;
		});
		firstNameColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getFirstName());
		});
		lastNameColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getLastName());
		});

		bookTable.setPlaceholder(new Label(resources.getString("booktable.emptyText")));
		bookTable.itemsProperty().bind(model.resultProperty());

		authorTable.setPlaceholder(new Label(resources.getString("authortable.emptyText")));
		authorTable.itemsProperty().bind(model.authorsProperty());

		phraseField.textProperty().bindBidirectional(model.phraseProperty());
		titleField.textProperty().bindBidirectional(model.titleProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());

		saveButton.disableProperty()
				.bind(titleField.textProperty().isEmpty().or(model.authorsProperty().emptyProperty()));
		addAuthorButton.disableProperty()
				.bind(firstNameField.textProperty().isEmpty().or(lastNameField.textProperty().isEmpty()));
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle(title);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
