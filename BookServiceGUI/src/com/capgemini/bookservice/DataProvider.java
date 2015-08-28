package com.capgemini.bookservice;

import java.io.IOException;
import java.util.Collection;

import com.capgemini.bookservice.model.Book;

public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	Collection<Book> findBooks(String phrase) throws IOException;

	Book saveBook(Book book) throws IOException;
}
