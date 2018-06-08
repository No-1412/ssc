package com.pcy.ssc.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pcy.ssc.entity.demo.IhPatientBloodListParam;
import com.pcy.ssc.utils.HttpUtil;
import com.pcy.ssc.utils.JsonUtil;

@Component
public class SscJob {
	@Autowired
	private HttpUtil httputil;
	
	@Scheduled(fixedDelay=1000*10)
	public void jssscjob() {
		IhPatientBloodListParam ih = new IhPatientBloodListParam();
		ih.setInpCode("000001");
		Map<String, String> header = new HashMap<>();
		header.put("sid", "999");
		header.put("st", "abc");
		
		String result = httputil.post("http://192.168.0.162:8080/his/ihpatientblood/list",header, ih);
		System.out.println(result);
		String code = JsonUtil.get(result, "code");
		System.out.println(code);
	}
}
