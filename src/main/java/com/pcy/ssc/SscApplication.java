package com.pcy.ssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SscApplication {

	public static void main(String[] args) {
		SpringApplication.run(SscApplication.class, args);
	}
}
