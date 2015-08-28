package com.capgemini.bookservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	public Author(){}

	public Author(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	

}
