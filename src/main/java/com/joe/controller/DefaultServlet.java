package com.joe.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.joe.dao.FileReader;
import com.joe.servlet.Request;
import com.joe.servlet.Response;
import com.joe.servlet.Servlet;
import com.joe.util.PropertiesLoader;

public class DefaultServlet implements Servlet {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doGet(Request request, Response response) {
		// TODO Auto-generated method stub
		Properties fileList = PropertiesLoader.getFileListProperties("fileList.properties");
		FileReader fileReader = new FileReader(request.getUri(), fileList);

		StringBuilder sBuilder = new StringBuilder();
		BufferedReader reader;
		try {
			reader = fileReader.getFileBufferedReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);

			}
			reader.close();

			response.setBody(sBuilder.toString());
			String type = fileReader.getFileType();
			response.setType(type);
			response.init();
			response.sendResponse();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void doPost(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPut(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub

	}

}
