package com.capgemini.bookservice.model;

import java.util.HashSet;
import java.util.Set;

public class BookVO {
	private Long id;
	private String title;
	private Set<AuthorVO> authors = new HashSet<AuthorVO>();
	
	public BookVO(){}

	public BookVO(Long id, String title, Set<AuthorVO> authors) {
		this.id = id;
		this.title= title;
		this.authors=authors;
	}
	

	public String getTitle() {
		return title;
	}



	public Set<AuthorVO> getAuthors() {
		return authors;
	}


	public Long getId() {
		return id;
	}



}
