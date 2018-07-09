package com.joe.service;

import org.junit.Before;
import org.junit.Test;

import com.joe.model.Book;
import com.joe.server.ServletLoader;

public class BookServiceTest {
	private BookService bookService;

	@Before
	public void beforeAction() {
		this.bookService = new BookService();
	}

	@Test
	public void test() {
		this.bookService.addBook(new Book("aaa", "bbb", 123.3));
		this.bookService.addBook(new Book("qwer", "poiu", 123.3));
		this.bookService.delBook(7);
		Book book = new Book("tgbnh", "ujm", 1.3);
		book.setId(2);
		this.bookService.editBook(book);
	}
}
