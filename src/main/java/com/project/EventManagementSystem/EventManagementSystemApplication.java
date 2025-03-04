package com.project.EventManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EventManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemApplication.class, args);
	}

}
