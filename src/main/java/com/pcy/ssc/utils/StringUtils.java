package com.pcy.ssc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	    public static String getMatcher(String regex, String source) {  
	        String result = "";  
	        Pattern pattern = Pattern.compile(regex);  
	        Matcher matcher = pattern.matcher(source);  
	        while (matcher.find()) {  
	            result = matcher.group(1);
	        }  
	        return result;  
	    }  
	    
	    public static void main(String[] args) {
	        String url = "http://172.12.1.123/test.txt";
	        
	       String regex="<p>[d]*-[d]*┊万┊<font color=\"#f00\">&lt;[d]*&gt;</font>┊[d]* 期 [d]*┊中</p>";
	     //   String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
//	        String regex = "(\\d{1,3}\\.){1,3}(\\d{1,3})";
	        System.out.println(getMatcher(regex,url));
	    }
	    

}
