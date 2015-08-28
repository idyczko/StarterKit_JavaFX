package com.capgemini.bookservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.entity.StringEntity;

import com.capgemini.bookservice.model.Author;
import com.capgemini.bookservice.model.Book;

public class CustomJSONParser {

	public static List<Book> parse(String json) {
		List<Book> books = new ArrayList<Book>();
		json=json.substring(1, json.length()-2);
		String[] separatedObjects = json.split("]");
		for (String object : separatedObjects) {
			Set<Author> authors = new HashSet<Author>();
			String[] titleAndAuthors = object.split("\\[");
			String title = titleAndAuthors[0].split("\"title\":\"")[1].split("\",\"")[0].replaceAll("\"", "");
			String[] authorsString = titleAndAuthors[1].split("\\},\\{");
			for (String author : authorsString) {
				String firstName = author.split("\"firstName\":\"")[1].split("\",\"")[0].replaceAll("\"", "");
				String lastName = author.split("\"lastName\":\"")[1].split("\\},\\{")[0].replaceAll("[\"\\}]", "");
				authors.add(new Author(null, firstName, lastName));
			}
			books.add(new Book(null, title, authors));
		}
		return books;
	}

	public static String parse(Book book) {
		String bookJSON= new String("{ \"title\":\""+book.getTitle()+"\", \"authors\":[");
		for(Author author: book.getAuthors()){
			bookJSON+="{\"firstName\":\""+author.getFirstName()+"\", \"lastName\":\""+author.getLastName()+"\"}";
		}
		bookJSON+="]}";
		return bookJSON;
	}	
}
