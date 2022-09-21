package com.team.art.dto.cart;

import org.springframework.lang.NonNull;

public class AddToCartDto {
	
	private Long id;
	private @NonNull Long productId;
	
	public AddToCartDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AddToCartDto(Long id, Long productId) {
		super();
		this.id = id;
		this.productId = productId;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	

}
