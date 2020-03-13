package com.scb.test.SCBTest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scb.test.SCBTest.model.Order;

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Modifying
	@Transactional
	@Query(value ="delete FROM user_orders  WHERE username = ?1"
			,nativeQuery = true)
	void DeleteByUsername(String username);
	
	@Query(value ="SELECT u.book_id FROM user_orders u WHERE username = ?1"
			,nativeQuery = true)
	int[] GetOrderByUsername(String username);
}
