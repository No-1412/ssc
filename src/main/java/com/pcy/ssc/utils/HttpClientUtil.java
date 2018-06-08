package com.pcy.ssc.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import com.pcy.ssc.variable.CookieVariable;
import com.pcy.ssc.variable.HttpClientResult;

public class HttpClientUtil {
	
	 public static HttpClientResult doPost(Map<String, String> paramMap, String url) {
		 return doPost(paramMap, url, null);
	 }
	
	 public static HttpClientResult doPost(Map<String, String> paramMap, String url,Map<String, String> headers) {
	        CloseableHttpClient httpClient = null;
	        HttpPost httpPost = null;
	        try {
	            CookieStore cookieStore = new BasicCookieStore();
	            httpPost = new HttpPost(url);
	            setParamter(paramMap, httpPost,cookieStore,headers);

	           httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	           CloseableHttpResponse response = httpClient.execute(httpPost);
	            
	            /**
	             * 返回结果
	             */
	            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()  
	                    .getContent()));  
	            StringBuffer sb = new StringBuffer("");  
	            String line = "";  
	            String NL = System.getProperty("line.separator");  
	            while ((line = in.readLine()) != null) {  
	                sb.append(line + NL);  
	            }  
	            in.close();  
	            String jsonResult = sb.toString();
	            //System.out.println(jsonResult);
	            
	            List<Cookie> cookies = cookieStore.getCookies();
	            for (Cookie cookie : cookies) {
	    			CookieVariable.domain=cookie.getDomain();
	    			CookieVariable.path=cookie.getPath();
	    			CookieVariable.win_session=cookie.getValue();
	    			CookieVariable.version=cookie.getVersion();
	    		}
	            //返回结果
	            HttpClientResult clientResult=new HttpClientResult(cookies, jsonResult, response.getStatusLine().getStatusCode());
	            return clientResult;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }
	 
	 private static void setParamter(Map<String, String> paramMap,HttpPost httpPost, CookieStore cookieStore,Map<String, String> headers) throws UnsupportedEncodingException{
		 if(paramMap!=null){
			 List<NameValuePair> list = new ArrayList<NameValuePair>();
	         Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
	         while (iterator.hasNext()) {
	             Entry<String, String> elem = (Entry<String, String>) iterator.next();
	             list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
	         }
	         if (list.size() > 0) {
	             UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
	             httpPost.setEntity(entity);
	         }
		 }
         //设置cookie
         if(CookieVariable.win_session!=null){
	         BasicClientCookie cookie = new BasicClientCookie("win_session", CookieVariable.win_session);
	         cookie.setVersion(CookieVariable.version); 
	         cookie.setDomain(CookieVariable.domain);  
	         cookie.setPath(CookieVariable.path);  
	         cookieStore.addCookie(cookie);
         }
         httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0");
         if(headers != null) {
        	 for(Map.Entry<String, String> entry : headers.entrySet()) {
        		 httpPost.addHeader(entry.getKey(), entry.getValue());
        	 }
         }
	 }
}
