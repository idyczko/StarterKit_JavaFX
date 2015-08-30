package com.capgemini.bookservice.model;

public class AuthorVO {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	public AuthorVO(){}

	public AuthorVO(Long id, String firstName, String lastName) {
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
