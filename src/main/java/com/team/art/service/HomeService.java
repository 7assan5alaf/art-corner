package com.team.art.service;

import java.util.Objects;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team.art.dto.UserSginIn;
import com.team.art.model.AuthToken;
import com.team.art.model.Role;
import com.team.art.model.User;
import com.team.art.repository.RoleRepository;
import com.team.art.repository.UserReopsitory;
import com.team.art.response.ResponseDto;
@Service
public class HomeService {
	@Autowired
	private UserReopsitory userReopsitory;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired 
	private TokenService tokenService;
	
	public void saveUser(User user) throws MessagingException {
		
		if(user.getType().toString().equals("artist")) {
			Role role=roleRepository.findById(3L).get();
			user.setRoles(role);
		}
		if(user.getType().toString().equals("user")) {
			Role role=roleRepository.findById(2L).get();
			user.setRoles(role);
		}
		if(user.getType().toString().equals("admin")) {
			Role role=roleRepository.findById(1L).get();
			user.setRoles(role);
		}
		    user.setPassword(passwordEncoder.encode(user.getPassword()));
		    userReopsitory.save(user);
		    //generate token from user
		    AuthToken token=new AuthToken(user);
		    tokenService.saveToken(token);
	}
	public ResponseDto login(UserSginIn sginIn) {
		User user=userReopsitory.findByEmail(sginIn.getEmail());
		if(Objects.isNull(user)) {
			ResponseDto responseDto=new ResponseDto("inavlid","Invalid username and password",null);
			return responseDto;
		}
		if(!passwordEncoder.matches(sginIn.getPassword(),user.getPassword())) 
		{
			ResponseDto responseDto=new ResponseDto("inavlid","Invalid username and password",null);
			return responseDto;
		}
		AuthToken token=tokenService.getToken(user);
		if(Objects.isNull(token)) {
			ResponseDto responseDto=new ResponseDto("inavlid","Token is not present",null);
			return responseDto;
		}
		
		ResponseDto responseDto=new ResponseDto("success",token.getToken(),token.getUser());
		return responseDto;
		
		}

}
