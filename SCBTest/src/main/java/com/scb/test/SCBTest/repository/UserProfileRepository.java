package com.scb.test.SCBTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scb.test.SCBTest.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	
	@Query(value ="SELECT * FROM user_profile * WHERE username = ?1"
			,nativeQuery = true)
	UserProfile Authentication(String username);
}
