package com.capgemini.bookservice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class BookSearchModel {
	private final LongProperty id = new SimpleLongProperty();
	private final StringProperty phrase = new SimpleStringProperty();
	
	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	
	private final ListProperty<Book> result = new SimpleListProperty<Book>(
			FXCollections.observableList(new ArrayList<Book>()));
	
	private final ListProperty<Author> authors = new SimpleListProperty<Author>(
			FXCollections.observableList(new ArrayList<Author>()));
	
	
	public final String getPhrase() {
		return phrase.get();
	}

	public final void setPhrase(String value) {
		phrase.set(value);
	}

	public StringProperty phraseProperty() {
		return phrase;
	}
	public final String getTitle() {
		return title.get();
	}
	
	public final void setTitle(String value) {
		title.set(value);
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	public final String getFirstName() {
		return firstName.get();
	}
	
	public final void setFirstName(String value) {
		firstName.set(value);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	public final String getLastName() {
		return lastName.get();
	}
	
	public final void setLastName(String value) {
		lastName.set(value);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public final List<Book> getResult() {
		return result.get();
	}
	
	public final void setResult(Collection<Book> collection) {
		result.setAll(collection);
	}
	
	public final void setId(Long id) {
		this.id.set(id);
	}

	public ListProperty<Book> resultProperty() {
		return result;
	}
	
	public final List<Author> getAuthors() {
		return authors.get();
	}
	
	public ListProperty<Author> authorsProperty() {
		return authors;
	}

	public Long getId() {
		return id.get();
	}
}
