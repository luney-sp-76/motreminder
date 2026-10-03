package com.motbookingreminder.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
     classes = RestServiceApplication.class,
     properties = {
        "email=test@example.com",
        "app.url=http://localhost:8081",
	"api.key=test-api-key"
    }
)
class RestServiceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();

	}

}
