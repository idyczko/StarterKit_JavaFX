package com.capgemini.bookservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.capgemini.bookservice.model.Book;

public class DataProviderImpl implements DataProvider {

	@Override
	public Collection<Book> findBooks(String phrase) throws IOException {
		return CustomJSONParser.parse(sendSearchRequest(phrase));
	}

	@Override
	public Book saveBook(Book book) throws IOException {
		return CustomJSONParser.parse(sendSaveRequest(book)).get(0);
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

	private String sendSaveRequest(Book book) throws IOException {
		String url = "http://localhost:9721/workshop/rest/books/book";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(url);
			StringEntity bookJSON = new StringEntity(CustomJSONParser.parse(book), "UTF-8");
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
