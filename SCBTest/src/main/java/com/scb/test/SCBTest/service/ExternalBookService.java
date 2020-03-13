package com.scb.test.SCBTest.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.scb.test.SCBTest.model.ExternalBook;

@Service
public class ExternalBookService {
	private final RestTemplate restTemplate;

	public ExternalBookService(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public List<ExternalBook> GetBooksSortByRecommendation() {

		// Get All Book
		ResponseEntity<ExternalBook[]> response_all = this.restTemplate
				.getForEntity("https://scb-test-book-publisher.herokuapp.com/books", ExternalBook[].class);
		ExternalBook[] book_all = response_all.getBody();

		// Get Recommend Book
		ResponseEntity<ExternalBook[]> response_rec = this.restTemplate.getForEntity(
				"https://scb-test-book-publisher.herokuapp.com/books/recommendation", ExternalBook[].class);
		ExternalBook[] book_recommend = response_rec.getBody();

		// Merge Data
		for (int i = 0; i < book_recommend.length; i++) {
			for (int j = 0; j < book_all.length; j++) {
				if (book_recommend[i].getId() == book_all[j].getId()) {
					book_all[j].setIs_recommended(true);
					break;
				}
			}
		}

		// Sort By Recommend
		List<ExternalBook> list = Arrays.asList(book_all);
		List<ExternalBook> sort_recommend = list.stream()
				.sorted(Comparator.comparing(ExternalBook::isIs_recommended).reversed()).collect(Collectors.toList());
		return sort_recommend;
	}

	public List<ExternalBook> GetBooksAll() {
		ResponseEntity<ExternalBook[]> response_all = this.restTemplate
				.getForEntity("https://scb-test-book-publisher.herokuapp.com/books", ExternalBook[].class);
		List<ExternalBook> book_all = Arrays.asList(response_all.getBody());
		return book_all;
	}

}
