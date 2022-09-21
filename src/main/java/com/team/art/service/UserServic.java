package com.team.art.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.art.error.RecordNotFoundException;
import com.team.art.model.User;
import com.team.art.repository.UserReopsitory;


@Service
public class UserServic {
	
	@Autowired
	private UserReopsitory userReopsitory;
	
	public User getUser(Long id) {
	return	userReopsitory.findById(id).orElseThrow(
			()->new RecordNotFoundException("sorry! user  ->"+id+" not found"));
	}
	public List<User>findArtist(){
		return userReopsitory.findByType("artist");
	}
	public void saveEdit(User user) {
		userReopsitory.save(user);
	}
	
}
