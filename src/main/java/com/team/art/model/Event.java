package com.team.art.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class Event {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private String email;
	 private String location;
	 private String phone;
     @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	 private Date date;
	 private String proces="processing";
	 @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
     @JoinColumn(name = "user_id",referencedColumnName = "id")
	 private User user;
	 public Event() {

	}
	  
	public Event(String email, String location, Date date, String proces, User user,String phone) {
		super();
		this.email = email;
		this.location = location;
		this.date = date;
		this.proces = proces;
		this.user = user;
		this.phone=phone;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getProces() {
		return proces;
	}
	public void setProces(String proces) {
		this.proces = proces;
	}
//	public User getUser() {
//		return user;
//	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	 
	 
	 
}
