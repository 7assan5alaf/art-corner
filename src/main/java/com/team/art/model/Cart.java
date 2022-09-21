package com.team.art.model;


import java.util.Date;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date createDate;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id",referencedColumnName = "id")
	private Product product;
	
	public Cart(User user, Product product) {
		this.createDate = new Date();
		this.user = user;
		this.product = product;
	}
	public Cart() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getPhoto() {
		return product;
	}
	public void setPhoto(Product product) {
		this.product = product;
	}
	
}
