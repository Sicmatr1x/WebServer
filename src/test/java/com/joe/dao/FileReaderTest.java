package com.joe.dao;

import org.junit.Before;
import org.junit.Test;

import com.joe.server.ServletLoader;
import com.joe.util.PropertiesLoader;

public class FileReaderTest {
	private FileReader fileReader;

	@Before
	public void beforeAction() {
		this.fileReader = new FileReader("/baidu.html", PropertiesLoader.getFileListProperties("fileList.properties"));
	}

	@Test
	public void test() {
		System.out.println(this.fileReader.getFileName());
		System.out.println(this.fileReader.getFileType());
		System.out.println(this.fileReader.getFile());
	}
}
