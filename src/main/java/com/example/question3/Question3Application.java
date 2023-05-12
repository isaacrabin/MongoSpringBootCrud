package com.example.question3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Question3Application {

	public static void main(String[] args) {
		SpringApplication.run(Question3Application.class, args);
	}

}
