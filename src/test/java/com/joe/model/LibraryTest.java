package com.joe.model;

import org.junit.Before;
import org.junit.Test;

import com.joe.server.ServletLoader;

public class LibraryTest {
	private Library library;

	@Before
	public void beforeAction() {
		this.library = new Library();
	}

	@Test
	public void test() {
		this.library.createEmptyBookMap();
		this.library.addBook(new Book("aaa", "bbb", 123.3));
		this.library.addBook(new Book("qwer", "poiu", 123.3));
		this.library.delBook(1);
		Book book = new Book("tgbnh", "ujm", 1.3);
		book.setId(2);
		this.library.editBook(book);
	}
}
