package com.team.art.dto.cart;

import com.team.art.model.Cart;
import com.team.art.model.Product;

public class CartItems {

	private Long id;
	private Product product;
	
	public CartItems() {
	
	}
	public CartItems(Cart cart) {
		this.id=cart.getId();
		this.setProduct(cart.getPhoto());
	}
	
	public CartItems(Long id, Product product) {
		super();
		this.id = id;
		this.product = product;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
