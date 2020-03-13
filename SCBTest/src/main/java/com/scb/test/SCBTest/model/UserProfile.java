package com.scb.test.SCBTest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "user_profile")
public class UserProfile {

	@ApiModelProperty(example = "UserName" , required = true)
	@Column(name = "username")
	@Id
	private String username;

	@ApiModelProperty(example = "FistName" , required = true)
	@Column(name = "name")
	private String name;

	@ApiModelProperty(example = "SureName" , required = true)
	@Column(name = "surename")
	private String surename;
	
	@ApiModelProperty(example = "password", required = true)
	@Column(name = "password")
	@JsonIgnoreProperties(allowSetters = true)
	private String password;

	@ApiModelProperty(example = "15/01/1985")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "date_of_birth")
	private Date  date_of_birth;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

}
