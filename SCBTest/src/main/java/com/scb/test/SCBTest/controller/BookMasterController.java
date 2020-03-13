package com.scb.test.SCBTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.test.SCBTest.model.ExternalBook;
import com.scb.test.SCBTest.service.ExternalBookService;;

@RestController
@RequestMapping("/books")
public class BookMasterController {
	@Autowired
	ExternalBookService book_sv;

	@GetMapping
	@ResponseBody()
	public ResponseEntity<?> GetBooksAll() {
		List<ExternalBook> book_all = book_sv.GetBooksSortByRecommendation();
		return ResponseEntity.status(HttpStatus.OK).body(book_all);
	}

}
