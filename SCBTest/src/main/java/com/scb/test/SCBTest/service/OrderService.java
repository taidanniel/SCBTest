package com.scb.test.SCBTest.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.scb.test.SCBTest.model.BookOrder;
import com.scb.test.SCBTest.model.ExternalBook;
import com.scb.test.SCBTest.model.Order;
import com.scb.test.SCBTest.repository.OrderRepository;;

@Service
public class OrderService {

	@Autowired
	OrderRepository order_dao;
	
	@Autowired
	ExternalBookService book_sv;

	public double CreateOrder(String username,BookOrder bookorder) {
		//Sum price
		double price = 0;
		
		//Get All book
		List<ExternalBook> book_all = book_sv.GetBooksAll();
		
		//List Order Book
		List<Order> list_order = new ArrayList<Order>();
		Order order = null;

		//Find Book Detail
		for (int i = 0; i < bookorder.getOrders().length; i++) {
			
			int order_id = (bookorder.getOrders())[i];
			List<ExternalBook> search_book = book_all.stream().filter(a -> Objects.equals(a.getId(), order_id))
					.collect(Collectors.toList());

			if (search_book.size() > 0) {
				order = new Order();
				order.setUsername(username);
				order.setBook_id(search_book.get(0).getId());
				order.setBook_name(search_book.get(0).getBook_name());
				order.setBook_author(search_book.get(0).getAuthor_name());
				order.setBook_price(search_book.get(0).getPrice());
				list_order.add(order);
				price = price + search_book.get(0).getPrice();
			}
		}
		
		//Save Order
		List<Order> save_order = order_dao.saveAll(list_order);
		if(save_order == null)
		{
			price = -1;
		}
		
		return price;
	}

	public boolean DeleteOrder(String username)
	{
		try {
			order_dao.DeleteByUsername(username);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
		
	}

	public int[] GetOrderByUsername(String username) {
		return order_dao.GetOrderByUsername(username);
	}

}
