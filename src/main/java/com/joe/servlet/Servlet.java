package com.joe.servlet;

import java.io.IOException;

public interface Servlet {
	public void init();
	public void doGet(Request request, Response response) throws IOException;
	public void doPost(Request request, Response response) throws IOException;
	public void doPut(Request request, Response response) throws IOException;
	public void doDelete(Request request, Response response) throws IOException;
}
