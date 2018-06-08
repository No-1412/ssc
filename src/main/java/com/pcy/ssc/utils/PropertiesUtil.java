package com.pcy.ssc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private final static Properties properties;
	
	static{
		String configPath="conf.properties";
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(configPath);  
        properties = new Properties();
        try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String  getProperties(String attr) {  
        return properties.getProperty(attr);  
    }
	
	public static String  getProperties(String propertisPath,String attr) {
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertisPath);  
		Properties properties = new Properties();
        try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return properties.getProperty(attr);  
    }
}
