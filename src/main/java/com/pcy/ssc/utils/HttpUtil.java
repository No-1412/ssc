package com.pcy.ssc.utils;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {
	@Autowired
	private RestTemplate restTemplate;
	
	public String post(String url,Map<String, String> header,Object params) {
		HttpHeaders headers = new HttpHeaders();
		if(header != null) {
			for(Map.Entry<String, String> entry : header.entrySet()) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<Object> httpEntity = new HttpEntity<>(params,headers);
		return restTemplate.postForEntity(url,httpEntity, String.class).getBody();
		
	}
	
	public String post(String url,Object params) {
		return post(url, null, params);
	}
}
