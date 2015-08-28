package com.capgemini.bookservice.model;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	private StringProperty title;
	private Set<Author> authors = new HashSet<Author>();

	public Book(String title, Set<Author> authors) {
		this.title= new SimpleStringProperty(title);;
		this.authors=authors;
	}
	

	public String getTitle() {
		return title.get();
	}

	public StringProperty getTitleProperty() {
		return title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

}
