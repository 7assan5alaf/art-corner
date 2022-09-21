package com.team.art.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.art.dto.cart.CartDto;
import com.team.art.model.Order;
import com.team.art.model.User;
import com.team.art.response.ApiResponse;
import com.team.art.service.CartService;
import com.team.art.service.OrderService;
import com.team.art.service.TokenService;



@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CartService cartService;
    @Autowired
    private OrderService orderService;
	@PostMapping("/addtocart")
	public ResponseEntity<ApiResponse>addToCart(@RequestParam("productId") Long productId, @RequestParam("token") String token){
		tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		cartService.addToCart(productId,user);
		return new ResponseEntity<ApiResponse>(new ApiResponse("true","add to cart"), HttpStatus.OK);	
	}
	
	//manage cart items
	
	//get all items for user
	@GetMapping("/cartItems/{token}")
	public ResponseEntity<CartDto>getItemsByUser(@PathVariable ("token") String token){
		tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		return new ResponseEntity<CartDto>(cartService.listCartItems(user),HttpStatus.OK);
	}
	//delete item from cart for a user 
	@DeleteMapping("/deleteItem")
	public ResponseEntity<ApiResponse>deleteItems(@RequestParam("cartItemId") Long cartItemId 
			, @RequestParam("token") String token){
		tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		return cartService.deleteCartItems(cartItemId, user);
	}
	
  // Make order
	@PostMapping("/makeOrder/{token}")
	public ApiResponse makeOrder(@PathVariable String token,@RequestParam String name,@RequestParam String address,
		@RequestParam String size,	@RequestParam MultipartFile photo,@RequestParam String number,@RequestParam String email
		) {

	 return orderService.makeOrder(token,name,address,size,photo,number,email);
		
	}
	@GetMapping("/viewOrder/{token}")
	public List<Order>getAlByUser(@PathVariable String token){
		return orderService.getAllByUser(token);
	}
	
	
	
}
