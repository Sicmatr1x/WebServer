package com.joe.server;

import org.junit.Before;
import org.junit.Test;


public class ServletLoaderTest {

	private ServletLoader servletLoader;

	@Before
	public void beforeAction() {
		this.servletLoader = new ServletLoader();
	}

	@Test
	public void test() {
		this.servletLoader.init();
		this.servletLoader.load();
	}

}
