package com.scb.test.SCBTest.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

import com.scb.test.SCBTest.model.BookOrder;
import com.scb.test.SCBTest.model.LogIn;
import com.scb.test.SCBTest.model.UserProfile;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserProfileControllerTest {

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void Regiter() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/users";
		URI uri = new URI(baseUrl);
		UserProfile user_profile = new UserProfile();

		LocalDateTime now = LocalDateTime.now().minusYears(23);

		user_profile.setName("Test Name");
		user_profile.setSurename("Test Sure Name");
		user_profile.setUsername("test");
		user_profile.setPassword("test");
		user_profile.setDate_of_birth(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

		HttpEntity<UserProfile> requestEntity = new HttpEntity<>(user_profile);
		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				String.class);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
	
	@Test
	public void Login() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/users/login";
		URI uri = new URI(baseUrl);
		LogIn login = new LogIn();
		login.setUsername("test");
		login.setPassword("test");

		HttpEntity<LogIn> requestEntity = new HttpEntity<>(login);
		ResponseEntity<LogIn> responseEntity = restTemplate.withBasicAuth(login.getUsername(), login.getPassword())
				.exchange(uri, HttpMethod.POST, requestEntity, LogIn.class);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
	
	@Test
	public void GetUserProfile() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/users";
		URI uri = new URI(baseUrl);

	    ResponseEntity<String> responseEntity = restTemplate
	    		.withBasicAuth("test", "test")
	    		.getForEntity(uri, String.class);
	     
	    //Verify request succeed
	    assertEquals(200, responseEntity.getStatusCodeValue());
	    System.out.println("");
		System.out.println("============= Response Body ===============");
		System.out.println(responseEntity.getBody());
		System.out.println("============= Response Body ===============");
		System.out.println("");
	}
	
	@Test
	public void CreateOrder() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/users/orders";
		URI uri = new URI(baseUrl);
		BookOrder order = new BookOrder();
		
		//Mock Book Id
		int[] book_id = new int[] {1,2};
		order.setOrders(book_id);
		
		HttpEntity<BookOrder> requestEntity = new HttpEntity<>(order);
		ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("test", "test")
				.exchange(uri, HttpMethod.POST, requestEntity, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    System.out.println("");
		System.out.println("============= Response Body ===============");
		System.out.println(responseEntity.getBody());
		System.out.println("============= Response Body ===============");
		System.out.println("");

	}
	
	@Test
	public void DeleteOrderHistory() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/users/";
		URI uri = new URI(baseUrl);
		

		ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("test", "test")
				.exchange(uri, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


	}

	
}
