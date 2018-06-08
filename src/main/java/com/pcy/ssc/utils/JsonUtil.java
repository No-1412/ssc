package com.pcy.ssc.utils;

import org.json.JSONObject;

public class JsonUtil {

	public static String get(String jsonStr,String key) {
		try {
			JSONObject jo = new JSONObject(jsonStr);
			Object val = jo.get(key);
			return val == null ? null : val.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
