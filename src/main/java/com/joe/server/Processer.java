package com.joe.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

import com.joe.controller.DefaultServlet;
import com.joe.servlet.Request;
import com.joe.servlet.Response;
import com.joe.servlet.Servlet;


public class Processer implements Runnable {
	
	private Socket socket;
	
	private Map<String, Class<Servlet>> servletMap;

	public Processer(Socket socket, Map<String, Class<Servlet>> servletMap) {
		super();
		this.socket = socket;
		this.servletMap = servletMap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String req = Request.parse(this.socket.getInputStream());
			Request request = new Request(req);
			request.init();
			Response response = new Response(socket);
			
			Class<Servlet> servletClass = null;
			
			String uri = request.getUri();
			String[] args = uri.split("/");
			System.out.println("Processer:run():args[1]=" + args[1]);
			
			// find file
			Servlet defaultServlet = new DefaultServlet();
			defaultServlet.init();
			defaultServlet.doGet();
			
			// find servlet
			if((servletClass = this.servletMap.get("/" + args[1])) != null) {
				Class[] methodArgs = {Request.class, Response.class};
				String str = request.getType().toLowerCase();
				str = str.substring(0, 1).toUpperCase() + str.substring(1);
				String methodName = "do" + str;
				System.out.println("Processer:run():methodName=" + methodName);
				Method initMethod = servletClass.getDeclaredMethod("init");
				Method method = servletClass.getDeclaredMethod(methodName, methodArgs);
				Object[] methodArgList = new Object[2];
				methodArgList[0] = request;
				methodArgList[1] = response;
				try {
					initMethod.invoke(servletClass);
					method.invoke(servletClass, methodArgList);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else { // 404
				// TODO
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) { // unsupposed request type
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
