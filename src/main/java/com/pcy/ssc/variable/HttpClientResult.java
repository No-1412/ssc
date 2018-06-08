package com.pcy.ssc.variable;

import java.util.List;

import org.apache.http.cookie.Cookie;

public class HttpClientResult {
	private List<Cookie> cookies;
	private String jsonResult;
	private int status;
	public List<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
	public String getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public HttpClientResult(List<Cookie> cookies, String jsonResult, int status) {
		this.cookies = cookies;
		this.jsonResult = jsonResult;
		this.status = status;
	}
	
	public HttpClientResult() {
	}
	
	
}
