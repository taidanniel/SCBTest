package com.scb.test.SCBTest.model;


public class UserOrderHistory {
	
	private String name;
	private String surename;
	private String  date_of_birth;
	private int [] books;
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
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public int[] getBooks() {
		return books;
	}
	public void setBooks(int[] books) {
		this.books = books;
	}
	
}
