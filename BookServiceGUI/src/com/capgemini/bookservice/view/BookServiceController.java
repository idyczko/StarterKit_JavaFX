package com.capgemini.bookservice.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.capgemini.bookservice.DataProvider;
import com.capgemini.bookservice.model.Author;
import com.capgemini.bookservice.model.Book;
import com.capgemini.bookservice.model.BookSearchModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookServiceController {

	@FXML
	ResourceBundle resources;

	@FXML
	private TableView<Book> bookTable;
	
	@FXML
	private TableColumn<Book, String> titleColumn;
	
	@FXML
	private TableColumn<Book, String> authorColumn;

	@FXML
	private Label titleLabel;
	
	@FXML
	private Label authorLabel;

	@FXML
	Button searchButton;

	@FXML
	Button saveButton;

	@FXML
	TextField phraseField;

	@FXML
	TextField titleField;

	@FXML
	TextField firstNameField;

	@FXML
	TextField lastNameField;

	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private final BookSearchModel model = new BookSearchModel();

	public BookServiceController() {
	}

	@FXML
	private void addNewBook(ActionEvent event) {
		Book book = new Book(null, model.getTitle(),
				new HashSet<Author>(Arrays.asList(new Author(null, model.getFirstName(), model.getLastName()))));
		Task<Book> backgroundTask = new Task<Book>() {

			@Override
			protected Book call() throws Exception {

				Book returnedBook = dataProvider.saveBook(book);
				model.getResult().add(returnedBook);
				titleField.clear();
				firstNameField.clear();
				lastNameField.clear();
				return returnedBook;
			}
		};

		new Thread(backgroundTask).start();
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {

		Task<Collection<Book>> backgroundTask = new Task<Collection<Book>>() {

			@Override
			protected Collection<Book> call() throws Exception {

				Collection<Book> books = dataProvider.findBooks(model.getPhrase());
				model.setResult(books);
				return books;
			}
		};

		new Thread(backgroundTask).start();
	}

	@FXML
	private void initialize() {
		
		titleColumn.setCellValueFactory(cellData -> {return new SimpleStringProperty(cellData.getValue().getTitle());});
		authorColumn.setCellValueFactory(cellData -> {
			SimpleStringProperty simpleString = new SimpleStringProperty("");
			for (Author author : cellData.getValue().getAuthors()) {
				simpleString.set(simpleString.get() + author.getFirstName() + " " + author.getLastName() + ", ");
			}
			return simpleString;
		});
		
		bookTable.setPlaceholder(new Label(resources.getString("table.emptyText")));
		bookTable.itemsProperty().bind(model.resultProperty());
		phraseField.textProperty().bindBidirectional(model.phraseProperty());
		titleField.textProperty().bindBidirectional(model.titleProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());
		saveButton.disableProperty().bind(titleField.textProperty().isEmpty()
				.or(firstNameField.textProperty().isEmpty()).or(lastNameField.textProperty().isEmpty()));
	}

}
