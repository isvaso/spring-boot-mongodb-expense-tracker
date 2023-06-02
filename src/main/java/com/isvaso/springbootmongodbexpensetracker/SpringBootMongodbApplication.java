package com.isvaso.springbootmongodbexpensetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for starting the Spring Boot MongoDB application.
 */
@SpringBootApplication
public class SpringBootMongodbApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootMongodbApplication.class);

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		logger.info("Starting Spring Boot MongoDB application");
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}

}
