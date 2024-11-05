package com.typingTitans.lobotomy_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class LobotomyLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobotomyLabApplication.class, args);
	}

}
