package com.capgemini.bookservice;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.capgemini.bookservice.model.Book;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviderImpl implements DataProvider {
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Boolean deleteBook(Long id) throws JsonParseException, JsonMappingException, IOException {
		return sendDeleteRequest(id);
	}
	

	@Override
	public Collection<Book> findBooks(String phrase) throws IOException {
		List<Book> books = mapper.readValue(sendSearchRequest(phrase), new TypeReference<List<Book>>(){});
		return books;
	}

	@Override
	public Book saveBook(Book book) throws IOException {
		return mapper.readValue(sendSaveRequest(book), Book.class);
	}

	private String sendSearchRequest(String phrase) throws IOException {
		if (phrase == null) {
			phrase = "";
		}
		String url = "http://localhost:9721/workshop/rest/books/books-by-title?titlePrefix=" + phrase;
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			return responseString;
		} catch (Exception ex) {
			return null;
		}
	}
	
	private Boolean sendDeleteRequest(Long id) {

		String url = "http://localhost:9721/workshop/rest/books/book/" + id;
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpDelete request = new HttpDelete(url);
			HttpResponse response = httpClient.execute(request);
			return response.getStatusLine().getStatusCode()==200;
		} catch (Exception ex) {
			return null;
		}
	}

	private String sendSaveRequest(Book book) throws IOException {
		String url = "http://localhost:9721/workshop/rest/books/book";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(url);
			StringEntity bookJSON = new StringEntity(mapper.writeValueAsString(book), "UTF-8");
			bookJSON.setContentType("application/json");
			request.setEntity(bookJSON);
			
			HttpResponse response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");

			return responseString;
		} catch (Exception ex) {
			return null;
		}
	}



}
