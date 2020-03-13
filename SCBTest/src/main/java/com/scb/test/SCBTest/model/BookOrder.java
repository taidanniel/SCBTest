package com.scb.test.SCBTest.model;

import io.swagger.annotations.ApiModelProperty;

public class BookOrder {
	
	@ApiModelProperty(example = "[1,2]" , required = true)
	private int[] orders;

	public int[] getOrders() {
		return orders;
	}

	public void setOrders(int[] orders) {
		this.orders = orders;
	}
	
}
