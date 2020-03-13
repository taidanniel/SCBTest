package com.scb.test.SCBTest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.test.SCBTest.model.BookOrder;
import com.scb.test.SCBTest.model.LogIn;
import com.scb.test.SCBTest.model.UserProfile;
import com.scb.test.SCBTest.service.OrderService;
import com.scb.test.SCBTest.service.UserProfileService;
import com.scb.test.SCBTest.model.Order;
import com.scb.test.SCBTest.model.UserOrderHistory;
@RestController
@RequestMapping("/users")
public class UserProfileController {

	@Autowired
	UserProfileService user_sv;

	@Autowired
	OrderService order_sv;

	@ResponseBody()
	@PostMapping(value = "/login")
	public ResponseEntity<?> Login(@Valid @RequestBody LogIn login) {
		UserProfile auten = user_sv.Authentication(login);
		if (auten == null) {
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	@ResponseBody()
	public ResponseEntity<?> GetUserProfile(Authentication authen) {
		
		//Prepare Variable
		UserOrderHistory user_order = new UserOrderHistory();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		//Get User Profile
		Optional<UserProfile> user = user_sv.GetUserHistory(authen.getName());
		
		
		//Merge Data		
		user_order.setName(user.get().getName());
		user_order.setSurename(user.get().getSurename());
		user_order.setDate_of_birth(formatter.format(user.get().getDate_of_birth()));
		user_order.setBooks(order_sv.GetOrderByUsername(authen.getName()));
		
		return ResponseEntity.status(HttpStatus.OK).body(user_order);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public ResponseEntity<?> Register(@Valid @RequestBody UserProfile new_user) {
		UserProfile sav_user = user_sv.Register(new_user);
		if (sav_user == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public ResponseEntity<?> CreateOrder(Authentication authen, @Valid @RequestBody BookOrder bookorder) {

		double price = order_sv.CreateOrder(authen.getName(), bookorder);

		return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("price", price));
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody()
	public ResponseEntity<?> DeleteOrderHistory(Authentication authen) {

		if (!order_sv.DeleteOrder(authen.getName())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

}
