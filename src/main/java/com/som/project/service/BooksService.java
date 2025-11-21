package com.som.project.service;

import java.util.List;

import com.som.project.entity.BooksModule;

public interface BooksService {

	BooksModule custmerSaveBooks(BooksModule booksModule);

	List<BooksModule> custmergetAllBooks();

	BooksModule getByCustmerBookid(Long id);

}
