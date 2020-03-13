package com.scb.test.SCBTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@ComponentScan(basePackages = {"com.scb.test.SCBTest"})
@EntityScan("com.scb.test.SCBTest.model")
@EnableJpaRepositories("com.scb.test.SCBTest.repository")
@Import({SpringSecurityConfig.class})
public class ScbTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScbTestApplication.class, args);
	}

}
