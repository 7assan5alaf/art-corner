package com.team.art.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.team.art.dto.cart.CartDto;
import com.team.art.dto.cart.CartItems;
import com.team.art.model.Cart;
import com.team.art.model.Product;
import com.team.art.model.User;
import com.team.art.repository.CartRepository;
import com.team.art.response.ApiResponse;

@Service
public class CartService {
	@Autowired
	private ProductService productService;
    @Autowired
    private CartRepository cartRepository;
	public void addToCart(Long productId, User user) {
		Product product=productService.findByid(productId);
		Cart cart=new Cart();
		cart.setCreateDate(new Date());
		cart.setPhoto(product);
		cart.setUser(user);
		cartRepository.save(cart);
	}
	
	public CartDto listCartItems(User user) {
		List<Cart>carts=cartRepository.findAllByUserOrderByCreateDateDesc(user);
		List<CartItems>cartItems=new ArrayList<>();
		double total=0;
		for(Cart cart:carts) {
			CartItems items=new CartItems(cart);
			total+=cart.getPhoto().getPrice();
			cartItems.add(items);
		}
		CartDto cartDto=new CartDto();
		cartDto.setItems(cartItems);
		cartDto.setTotal(total);
		return cartDto;
	}

	public ResponseEntity<ApiResponse> deleteCartItems(Long cartItemId, User user) {
		Optional<Cart>optionalCart=cartRepository.findById(cartItemId);
		if(optionalCart.isEmpty()) {
			ApiResponse apiResponse=new ApiResponse("invalid", "item not found");
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		}
		
		Cart cart=optionalCart.get();
		if(cart.getUser()!=user) {
			ApiResponse apiResponse=new ApiResponse("invalid", "cart item does not belong to user "+cartItemId);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		}
		
		cartRepository.delete(cart);
		ApiResponse apiResponse=new ApiResponse("valid", "delete item for a user");
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

}
