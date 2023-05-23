package com.isvaso.springbootmongodbexpensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for starting the Spring Boot MongoDB application.
 */
@SpringBootApplication
public class SpringBootMongodbApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}

}
