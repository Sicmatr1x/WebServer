package com.joe.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Response {

	private Socket socket;
	
	private String type = "text/html";
	private String state = "200";
	private String location = null;
	
	private String head;
	private String body;
	private StringBuilder stringBuilder;

	public Response(Socket socket) {
		this.socket = socket;
		this.stringBuilder = new StringBuilder();
	}
	
	public Response(Socket socket, String type, String state, String body) {
		this.type = type;
		this.state = state;
		this.body = body;
		this.socket = socket;
	}
	
	public void initHead() {
		this.stringBuilder.append("HTTP/1.1 " + this.state + " OK\n");
		this.stringBuilder.append("Content-Type: " + this.type + ";charset=utf-8\n");
		if(this.location != null)
			this.stringBuilder.append("Location:" + this.location + "\n");
		this.stringBuilder.append("\n");
	}
	
	public void initBody() {
		this.stringBuilder.append(this.body);
	}
	
	public void init() {
		this.initHead();
		this.initBody();
	}

	public void sendResponse() throws IOException {
		System.out.println("sendResponse");
		OutputStream os = socket.getOutputStream();
		os.write((this.head + this.body).getBytes());
		os.close();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
