package com.pcy.ssc.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pcy.ssc.service.JssscService;

@Component
public class SscJob {
	@Autowired
	private JssscService service;
	
	@Scheduled(fixedDelay=1000*10)
	public void jssscjob() {
		service.jsssc();
	}
}
