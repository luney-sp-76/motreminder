package com.motbookingreminder.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main class for the REST service application.
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = { "com.motbookingreminder" })
public class RestServiceApplication {

	/**
	 * The main method that starts the REST service application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
