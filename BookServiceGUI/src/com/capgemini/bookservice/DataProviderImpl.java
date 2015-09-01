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

import com.capgemini.bookservice.model.BookVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * REV: warstwa dostepu do danych powinna byc zdefiniowana w osobnym pakiecie
 */
public class DataProviderImpl implements DataProvider {
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Boolean deleteBook(Long id) throws IOException {
		return sendDeleteRequest(id);
	}

	@Override
	public Collection<BookVO> findBooks(String phrase) throws IOException {
		return mapper.readValue(sendSearchRequest(phrase), new TypeReference<List<BookVO>>() {
		});
	}

	@Override
	public BookVO saveBook(BookVO book) throws IOException {
		return mapper.readValue(sendSaveRequest(book), BookVO.class);
	}

	private String sendSearchRequest(String phrase) throws IOException {
		if(phrase == null){
			phrase ="";
		}
		/*
		 * REV: adres serwera powinien byc zdefiniowany w pliku konfiguracyjnym
		 */
		String url = "http://localhost:9721/workshop/rest/books/books-by-title?titlePrefix=" + phrase;

		/*
		 * REV: utworzenie obiektu HttpClient jest kosztowne.
		 * Powinien on byc stworzony w konstruktorze, a nie przy kazdym zapytaniu do serwera.
		 */
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity responseEntity = response.getEntity();
		String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		return responseString;
	}

	private Boolean sendDeleteRequest(Long id) throws IOException {
		/*
		 * REV: j.w.
		 */
		String url = "http://localhost:9721/workshop/rest/books/book/" + id;
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);
		HttpResponse response = httpClient.execute(request);
		return response.getStatusLine().getStatusCode() == 200;
	}

	private String sendSaveRequest(BookVO book) throws IOException {
		/*
		 * REV: j.w.
		 */
		String url = "http://localhost:9721/workshop/rest/books/book";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		StringEntity bookJSON = new StringEntity(mapper.writeValueAsString(book), "UTF-8");
		bookJSON.setContentType("application/json");
		request.setEntity(bookJSON);

		HttpResponse response = httpClient.execute(request);
		HttpEntity responseEntity = response.getEntity();
		String responseString = EntityUtils.toString(responseEntity, "UTF-8");

		return responseString;
	}

}
