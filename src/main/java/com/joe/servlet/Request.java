package com.joe.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Request {
    private String request;
    
    private String uri;
    private String type;
    
    private String head;
    private String body;

    public Request(String request) {
        this.request = request;
    }
    
    public void initHead() {
    	 parseRequestType(request);
         System.out.println("type:" +type);
         
         this.uri = parseUri(this.request);
         System.out.println("uri:" + this.uri);
    }
    
    public void initBody() {
    	this.parseRequestBody(this.request);
        System.out.println("body:" + this.body);
    }
    
    public void init() {
    	this.initHead();
    	this.initBody();
    }

    //从InputStream中读取request信息，并从request中获取uri值
    public static String parse(InputStream input) {
    	String request;
        StringBuffer stringBuffer = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
        	stringBuffer.append((char) buffer[j]);
        }
        
        request = stringBuffer.toString();
        System.out.println(request);
        return request;
    }
    
    public String parseRequestType(String request) {
    	String[] work = request.split("\n");
        System.out.println("Request.parseRequestType=" + work[0]);
        String[] args = work[0].split(" ");
        this.type = args[0];
        return this.type;
    }
    
    public String parseRequestBody(String request) {
    	byte[] b = {13};
    	String[] work = request.split("\n");
    	
    	for(int i = 0; i < work.length; i++) {
//    		System.out.println("work[" + i + "]=" + work[i]);
    		if((work[i].toUpperCase()).contains("Content-Length".toUpperCase())) {
    			int length = 0;
				try {
					String[] temp = work[i].replaceAll(new String(b, "utf-8"), "").split(" ");
					length = Integer.valueOf(temp[1]);
					System.out.println("Request:parseRequestBody:Content-Length=" + length);
				} catch (NumberFormatException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			this.body = request.substring(request.length() - length, request.length());
    			break;
    		}
    	}
//        if(work.length == 1) {
//        	System.out.println("parseRequestBody0=" + work[0]);
//        }
//        System.out.println("parseRequestBody1=" + work[1]);
//        String[] args = work[1].split("&");
        
        return this.body;
    }
    
//    public void parsePlus() throws IOException {
//    	
//    	byte[] b = new byte[8];
//    	String result = "";
//    	int length = -1;
//    	while(input.available() != 0) {
//    		length = input.read(b);
//    		result += new String(b, 0, length);
//    	}
//    	
//    	uri = parseUri(result.toString());
//    }

    
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }

    public String getUri() {
        return uri;
    }

	public String getType() {
		return type;
	}

	public String getBody() {
		return body;
	}

}
