package com.aichat.aiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AiwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiwebApplication.class, args);
	}

}
