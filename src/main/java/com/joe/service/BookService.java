package com.joe.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.joe.model.Book;
import com.joe.model.Library;
import com.joe.server.Server;

public class BookService {
	
	private Library library = Library.initLibraryFromFile(new File(Server.LIBRARY_PATH));
	
	public BookService() {
		super();
	}
	
	public Book getBookById(Integer bookId) {
		return this.library.getBookrack().get(bookId);
	}
	
	public List<Book> getAllBook() {
		List<Book> bookList = new ArrayList<>();
		Map<Integer, Book> br = this.library.getBookrack();
		Iterator<Map.Entry<Integer, Book>> it = br.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Book> entry = it.next();
			bookList.add(entry.getValue());
		}
		return bookList;
	}

	public int addBook(Book book) {
		return library.addBook(book);
	}
	
	public int editBook(Book book) {
		return library.editBook(book);
	}
	
	public int delBook(Integer bookId) {
		return library.delBook(bookId);
	}
}
