package com.scb.test.SCBTest.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scb.test.SCBTest.model.LogIn;
import com.scb.test.SCBTest.model.UserProfile;
import com.scb.test.SCBTest.model.UserRole;
import com.scb.test.SCBTest.repository.UserProfileRepository;
import com.scb.test.SCBTest.repository.UserRoleRepository;

@Service
public class UserProfileService {

	@Autowired
	UserProfileRepository user_dao;
	
	@Autowired
	UserRoleRepository role_dao;
	
	public UserProfile Register(UserProfile new_user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(new_user.getPassword());
		new_user.setPassword(hashedPassword);
		UserProfile save_user = user_dao.save(new_user);
		if(save_user != null)
		{
			UserRole role = new UserRole();
			role.setUsername(new_user.getUsername());
			role.setRole("ROLE_USER");
			role_dao.save(role);
		}
		
		return save_user;
	}
	
	public Optional<UserProfile> GetUserHistory(String username)
	{
		return user_dao.findById(username);	
	}
	
	public UserProfile Authentication(LogIn login)
	{
		UserProfile user_data = user_dao.Authentication(login.getUsername());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("Input Password : "+login.getPassword());
		if(user_data == null)
		{
			return null;
		}
		else
		{
			if(!passwordEncoder.matches(login.getPassword(), user_data.getPassword()))
			{
				return null;
			}
		}
		
		return user_data;	
	}
	
	
}
