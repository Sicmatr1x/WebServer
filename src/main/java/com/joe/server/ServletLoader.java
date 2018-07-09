package com.joe.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.joe.servlet.Servlet;
import com.joe.util.PropertiesLoader;

public class ServletLoader {
	
	public static String SERVLET_FLODER_PATH = "com.joe.controller.";
	
	private Properties mapper;
	
	public void init() {
		this.mapper = PropertiesLoader.getFileListProperties("servletMapper.properties");
	}
	
	public Map<String, Class<Servlet>> load() {
		Map<String, Class<Servlet>> servletMap = new HashMap<>();
		Class<Servlet> servletClass = null;
		
		Iterator<Entry<Object, Object>> it = this.mapper.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			try {
				// 一般尽量采用这种形式
				servletClass = (Class<Servlet>) Class.forName(SERVLET_FLODER_PATH + key + "Servlet");
				servletMap.put(value, servletClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("ServletLoader:load():key:" + key + ",value :" + value);
		}
		
		return servletMap;
	}
}
