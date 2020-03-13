package com.scb.test.SCBTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.test.SCBTest.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
