package com.team.art.dto.cart;

import java.util.List;

public class CartDto {
	
	private List<CartItems>items;
	private double total;
	
	public CartDto() {
		// TODO Auto-generated constructor stub
	}
	public CartDto(List<CartItems> items, double total) {
		super();
		this.items = items;
		this.total = total;
	}

	public List<CartItems> getItems() {
		return items;
	}

	public void setItems(List<CartItems> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

}
