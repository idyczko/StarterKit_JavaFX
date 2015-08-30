package com.capgemini.bookservice;

import java.io.IOException;
import java.util.Collection;

import com.capgemini.bookservice.model.BookVO;

public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	Collection<BookVO> findBooks(String phrase) throws IOException;

	BookVO saveBook(BookVO book) throws IOException;

	Boolean deleteBook(Long id) throws IOException;
}
