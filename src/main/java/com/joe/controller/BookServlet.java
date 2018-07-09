package com.joe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.joe.model.Book;
import com.joe.service.BookService;
import com.joe.servlet.Request;
import com.joe.servlet.Response;
import com.joe.servlet.Servlet;

public class BookServlet implements Servlet {

	static BookService bookService = new BookService();

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doGet(Request request, Response response) throws IOException {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"utf-8\">\n");

		List<Book> bookList = bookService.getAllBook();
		
		html.append("<title>Book List</title></head>\\n");
		html.append("<body>");
		html.append("<table border=\"1\">\n");
		
		for(Book book : bookList) {
			html.append("<tr>\n<td>\n" + book.getId() + "</td>\n<td>" + book.getName() + "</td>\n<td>"
					+ book.getAuther() + "</td>\n<td>" + book.getPrise() + "</td>\n</tr>\n");
			System.out.println("BookServlet:doGet():" + book);
		}
		
		html.append("</table>\n");
		html.append("</body>\n</html>");

		response.setBody(html.toString());
		response.init();
		response.sendResponse();
	}
	
	/**
	 * get args from request body
	 * @return
	 */
	private Map<String, String> getArgsMap(Request request){
		if(request.getBody() == null || "".equals(request.getBody())) {
			return null;
		}
		Map<String, String> argsMap = new HashMap<>();
		String[] work = request.getBody().split("&");
		for(int i = 0; i < work.length; i++) {
			String[] args = work[i].split("=");
			argsMap.put(args[0], args[1]);
			System.out.println("BookService:getArgsMap():" + args[0] + "=" + args[1]);
		}
		return argsMap;
	}

	@Override
	public void doPost(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> argsMap = this.getArgsMap(request);
		Book book = new Book();
		book.setName(argsMap.get("name").replaceAll("%20", " "));
		book.setAuther(argsMap.get("auther").replaceAll("%20", " "));
		if(argsMap.get("prise") != null) {
			book.setPrise(Double.valueOf(argsMap.get("prise")));
		}
		
		this.bookService.addBook(book);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<body>\n"+"<p>"+ "add:" + book + " successful" +"</p>"+"</body>");
		stringBuilder.append("</html>\n");
		response.setBody(stringBuilder.toString());
		response.init();
		response.sendResponse();
	}

	@Override
	public void doPut(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> argsMap = this.getArgsMap(request);
		Book book = new Book();
		if(argsMap.get("id") != null) {
			book.setId(Integer.valueOf(argsMap.get("id")));
		}
		book.setName(argsMap.get("name").replaceAll("%20", " "));
		book.setAuther(argsMap.get("auther").replaceAll("%20", " "));
		if(argsMap.get("prise") != null) {
			book.setPrise(Double.valueOf(argsMap.get("prise")));
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if(this.bookService.editBook(book) == -1) {
			stringBuilder.append("<body>\n"+"<p>"+ "edit:" + book + " fail" +"</p>"+"</body>");
			stringBuilder.append("</html>\n");
			response.setBody(stringBuilder.toString());
			response.init();
			response.sendResponse();
		}
		stringBuilder.append("<body>\n"+"<p>"+ "edit:" + book + " successful" +"</p>"+"</body>");
		stringBuilder.append("</html>\n");
		response.setBody(stringBuilder.toString());
		response.init();
		response.sendResponse();
	}

	@Override
	public void doDelete(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> argsMap = this.getArgsMap(request);
		Integer bookId = null;
		if(argsMap.get("id") != null) {
			bookId = Integer.valueOf(argsMap.get("id"));
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if(this.bookService.delBook(bookId) == -1) {
			stringBuilder.append("<body>\n"+"<p>"+ "del:" + bookId + " fail" +"</p>"+"</body>");
			stringBuilder.append("</html>\n");
			response.setBody(stringBuilder.toString());
			response.init();
			response.sendResponse();
		}
		stringBuilder.append("<body>\n"+"<p>"+ "del:" + bookId + " successful" +"</p>"+"</body>");
		stringBuilder.append("</html>\n");
		response.setBody(stringBuilder.toString());
		response.init();
		response.sendResponse();
	}

}
