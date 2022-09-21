package com.team.art.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class EventDto {
	
	private String email;
	private String phone;
	private String location;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	private Date date;
	
	public EventDto() {
		// TODO Auto-generated constructor stub
	}

	
	public EventDto(String email, String phone, String location, Date date) {
		super();
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
