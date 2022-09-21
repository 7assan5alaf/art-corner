package com.team.art.dto;

import org.springframework.lang.NonNull;

public class UserSginIn {
	
	private @NonNull String email;
	private @NonNull String password;
	
	public UserSginIn() {
		super();
	}
	public UserSginIn(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
