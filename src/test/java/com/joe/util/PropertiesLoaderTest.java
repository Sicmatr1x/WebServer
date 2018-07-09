package com.joe.util;

import java.util.Properties;

import org.junit.Test;


public class PropertiesLoaderTest {

	@Test
	public void test() {
		Properties p = PropertiesLoader.getFileListProperties(System.getProperty("user.dir")+"/fileList.properties");
		System.out.println(p);
	}
}
