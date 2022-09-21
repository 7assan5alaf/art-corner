package com.team.art.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.team.art.error.RecordNotFoundException;
import com.team.art.model.Order;
import com.team.art.model.User;
import com.team.art.repository.OrderRepository;
import com.team.art.response.ApiResponse;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
    @Autowired
    private TokenService tokenService;
    
	public ApiResponse makeOrder(String token, String name, String address, String size, MultipartFile photo,
			String number,String email) {
		Order order=new Order();
		 tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		order.setAddress(address);
		order.setName(name);
		order.setNumber(number);
		order.setUser(user);
		order.setEmail(email);
		try {
			order.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderRepository.save(order);
		return new ApiResponse("success","Make Order Success! Wait for Artist Approval");
	}

	public List<Order> getAllByUser(String token) {
		    tokenService.authintecation(token);
			User user=tokenService.getUser(token);
		return orderRepository.findByUser_Id(user.getId());
	}

	public List<Order> getAllByArtist(String token) {
		   tokenService.authintecation(token);
			User user=tokenService.getUser(token);
		return orderRepository.findByEmail(user.getEmail());
	}

	public Order findById(Long id) {
		
		return orderRepository.findById(id).orElseThrow(()->new RecordNotFoundException("order not found"));
	}

	public void save(Order order) {
		orderRepository.save(order);
	}
	
	
}
