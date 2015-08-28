package com.capgemini.bookservice.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	private Long id;
	private String title;
	private Set<Author> authors = new HashSet<Author>();
	
	public Book(){}

	public Book(Long id, String title, Set<Author> authors) {
		this.id = id;
		this.title= title;
		this.authors=authors;
	}
	

	public String getTitle() {
		return title;
	}



	public Set<Author> getAuthors() {
		return authors;
	}


	public Long getId() {
		return id;
	}



}
