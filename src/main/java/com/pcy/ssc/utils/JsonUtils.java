package com.pcy.ssc.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	public static String get(String json,String attr){
		JSONObject jsonObject=JSONObject.parseObject(json);
	 	return jsonObject.getString(attr);
	}
}
