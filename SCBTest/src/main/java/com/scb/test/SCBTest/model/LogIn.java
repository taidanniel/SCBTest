package com.scb.test.SCBTest.model;

import io.swagger.annotations.ApiModelProperty;

public class LogIn {
	
	@ApiModelProperty(example = "UserName" , required = true)
	private String username;
	
	@ApiModelProperty(example = "password" , required = true)
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
