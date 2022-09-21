package com.team.art.dto;

public class ProductDto {
	
	private Long id;
	private String name;
	private String desc;
	private Long cate_id;
	//private Long user_id;
	private int price;
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(Long id, String name, String desc, Long cate_id,int price) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.cate_id = cate_id;
		//this.user_id = user_id;
		this.price=price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getCate_id() {
		return cate_id;
	}
	public void setCate_id(Long cate_id) {
		this.cate_id = cate_id;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
