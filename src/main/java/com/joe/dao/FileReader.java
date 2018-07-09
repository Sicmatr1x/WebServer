package com.joe.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileReader {
	
	private Properties fileList;

	private String uri;

	public FileReader() {
		super();
	}

	public FileReader(String uri) {
		super();
		this.uri = uri;
	}

	public FileReader(String uri, Properties fileList) {
		super();
		this.fileList = fileList;
		this.uri = uri;
	}

	public String getFileName() {
		if("/".equals(uri)) {
			return "index.html";
		}
		
		String[] work = uri.split("/");

		if (work == null && work.length < 0) {
			// 500
		}
		System.out.println("FileReader:getFileName():" + work[1]);
		return work[1];
	}

	public String getFileType() {
//		System.out.println("FileReader:getFileType()" + this.getFileName());

		String[] work = this.getFileName().split("[.]");
		switch (work[1]) {
		case "":
			return "text";
		case "jpg":
			return "image";
		case "jpeg":
			return "image";
		}

		return "text";
	}

	public File getFile() {
		File root = new File(System.getProperty("user.dir"));
		File file = new File(root.getAbsoluteFile() + "//webpage//" + this.getFileName());
		System.out.println("FileReader:getFile():" + file.getAbsolutePath());
		return file;
	}

	public BufferedReader getFileBufferedReader() throws FileNotFoundException  {
		File file = this.getFile();
		BufferedReader fileBis = null;
		System.out.println("FileReader:getFileBufferedInputStream():" + file.getAbsolutePath());
		fileBis = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		return fileBis;
	}
}
