package com.team.art.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team.art.error.RecordNotFoundException;
import com.team.art.model.User;
import com.team.art.repository.UserReopsitory;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserReopsitory reopsitory;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=reopsitory.findByEmail(email);
		if(user==null)
			throw new RecordNotFoundException("User not exist in database");
		return new CustomUser(user);
	}

}
