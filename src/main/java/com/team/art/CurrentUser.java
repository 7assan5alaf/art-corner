package com.team.art;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.team.art.security.CustomUser;

public abstract class CurrentUser {
	
	public CustomUser getCurrentUser() {
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	CustomUser user=(CustomUser) authentication.getPrincipal();
	return user;
	}
}
