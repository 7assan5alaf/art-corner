package com.team.art.response;

import com.team.art.model.User;

public class ResponseDto {
	
	private String status;
	private String token;
	private User user;
	
	public ResponseDto(String status, String message,User user) {
		super();
		this.status = status;
		this.token = message;
		this.user=user;
	}
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String message) {
		this.token = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	 
	
}
