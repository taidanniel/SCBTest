package com.scb.test.SCBTest.model;


public class ExternalBook{
	
	private int id;
	private String book_name;
	private String author_name;
	private double price;
	private boolean is_recommended;
	

	public boolean isIs_recommended() {
		return is_recommended;
	}
	public void setIs_recommended(boolean is_recommended) {
		this.is_recommended = is_recommended;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}
