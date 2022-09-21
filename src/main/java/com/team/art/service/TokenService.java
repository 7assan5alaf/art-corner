package com.team.art.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.art.error.RecordNotFoundException;
import com.team.art.model.AuthToken;
import com.team.art.model.User;
import com.team.art.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository repository;
	public void saveToken(AuthToken token) {
		repository.save(token);
		
	}
	public AuthToken getToken(User user) {
		return repository.findByUser(user);
	}
	public User getUser(String token) {
		AuthToken authToken=repository.findByToken(token);
		if(Objects.isNull(authToken)) {
			return null;
		}
		return authToken.getUser();
	}
	public void authintecation(String token) throws RecordNotFoundException {
		
		if(Objects.isNull(token)) {
			throw new RecordNotFoundException("token not present");
		}
		if(Objects.isNull(getUser(token))) {
			throw new RecordNotFoundException("token not valid");
		}
	}

}
