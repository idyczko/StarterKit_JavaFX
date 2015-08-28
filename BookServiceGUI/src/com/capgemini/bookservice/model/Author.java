package com.capgemini.bookservice.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author {
private StringProperty firstName;
private StringProperty lastName;

public Author(String firstName, String lastName) {
	this.firstName= new SimpleStringProperty(firstName);
	this.lastName= new SimpleStringProperty(lastName);
}

public String getFirstName() {
	return firstName.get();
}

public String getLastName() {
	return lastName.get();
}

public StringProperty getFirstNameProperty() {
	return firstName;
}

public StringProperty getLastNameProperty() {
	return lastName;
}

}
