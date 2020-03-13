package com.scb.test.SCBTest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scb.test.SCBTest.model.LogIn;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookMasterControllerTest {

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void GetBooksAll() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/books";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		System.out.println("");
		System.out.println("============= Response Body ===============");
		System.out.println(responseEntity.getBody());
		System.out.println("============= Response Body ===============");
		System.out.println("");

	}

}
