package com.team.art.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.art.dto.EventDto;
import com.team.art.model.Course;
import com.team.art.model.Event;
import com.team.art.model.Order;
import com.team.art.model.Product;
import com.team.art.model.User;
import com.team.art.repository.UserReopsitory;
import com.team.art.response.ApiResponse;
import com.team.art.service.CourseService;
import com.team.art.service.EventService;
import com.team.art.service.OrderService;
import com.team.art.service.ProductService;
import com.team.art.service.TokenService;
import com.team.art.service.UserServic;

@RestController
@RequestMapping("api/artist")
@CrossOrigin("*")
public class ArtistController {

	  @Autowired
	  private ProductService productService;
	  @Autowired
	  private TokenService tokenService;
	  @Autowired
	  private EventService eventService;
	  @Autowired
	  private UserReopsitory reopsitory;
	  @Autowired
	  private CourseService courseService;
	  @Autowired
	  private OrderService orderService;
	  @Autowired
	   private UserServic userServic;
	   
	       @InitBinder
	 		public void intiBinder(WebDataBinder binder) {
	 			StringTrimmerEditor editor=new StringTrimmerEditor(true);
	 			binder.registerCustomEditor(String.class, editor);
	 		}
	  
	   //get all product from user by artist token   
		@GetMapping("/{token}")
		public ResponseEntity<Object>findProductsByUser(@PathVariable("token") String token){
			return ResponseEntity.ok(productService.getByUser(token));
		}
		// add product
		@PostMapping("/addProduct")
		public ResponseEntity<?>addPro(@RequestParam MultipartFile file,@RequestParam String name
				,@RequestParam String desc,@RequestParam int price,@RequestParam Long cate_id
				,@RequestParam("token") String token
				) throws IOException{
			tokenService.authintecation(token);
			User user=tokenService.getUser(token);
			return ResponseEntity.ok(productService.addProduct(file, name, desc, price, cate_id, user));
		}
		// delete product by product_id
		@DeleteMapping("/delete/product")
		public Map<String,Boolean> deletePaint(@RequestParam Long id){
			Product p=productService.findByid(id);
			productService.deleteProduct(p.getId());
			Map<String, Boolean>response=new HashMap<String, Boolean>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}

		// update paint 
		@PutMapping("/edit/product")
		public ResponseEntity<?>edit(@RequestParam Long id,@RequestParam MultipartFile file,
				@RequestParam String name, @RequestParam String desc,@RequestParam int price,
				@RequestParam Long cate_id) throws IOException{
			return ResponseEntity.ok(productService.editProduct(id, file, name, desc, price, cate_id));
		}
		
		// add event
		@PostMapping("/createEvent")
		public ApiResponse createEvent(@RequestBody EventDto event) {
			return eventService.createEvent(event);
		}
		//get all event
		@GetMapping("/eventsBy/{token}")
		public List<Event>getAllByUser(@PathVariable("token") String token){
		      return eventService.getAllByUser(token);	
		}
		
		//manage course
		@PostMapping("/uploadCourse")
		public ApiResponse addCourse(@RequestParam MultipartFile thumbnail,@RequestParam String courseTitle,
				@RequestParam String courseDesc,@RequestParam int price,@RequestParam String links,@RequestParam String token
				) throws IOException {
			tokenService.authintecation(token);
		 	User user=tokenService.getUser(token);
			 courseService.addCourse(courseTitle, courseDesc, thumbnail, links, price, user);
	     	return new ApiResponse("success","upload course successfully");
		}
		@PutMapping("/editCourse/{id}")
		public ApiResponse updateCourse(@PathVariable Long id,@RequestParam MultipartFile thumbnail,@RequestParam String courseTitle,
				@RequestParam String courseDesc,@RequestParam int price,@RequestParam String links) throws IOException {
			return courseService.editCourse(id,thumbnail,courseTitle,courseDesc,links,price);
		}
		
		@DeleteMapping("/deleteCourse/{id}")
		public ApiResponse deleteCourse(@PathVariable("id") Long id) {
			 Course course=courseService.findCourse(id);
			return courseService.deleteCourse(course.getId());
		}
	//Manage Profile
	@PutMapping("/editProfile/{id}")	
	public ApiResponse editProfile(@PathVariable Long id,@RequestParam MultipartFile image,
		    @RequestParam String bio ,
			@RequestParam String fullName,@RequestParam String address,@RequestParam String number
			) throws IOException {
	
	 	User user=reopsitory.findById(id).get();
	 	user.setAddress(address);
	 	user.setFullName(fullName);
	 	user.setNumber(number);
	 	user.setBio(bio);
	 	user.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
	 	userServic.saveEdit(user);
	 	return new ApiResponse("success", "save info");
	}
		
	//Manage order
	@GetMapping("/viewOrder/{token}")
	public List<Order>getAllByArtist(@PathVariable String token){
		return orderService.getAllByArtist(token);
	}
	@PutMapping("/acceptOrder/{id}")
	public ApiResponse accept(@PathVariable Long id) {
		Order order=orderService.findById(id);
		order.setProces("Accept");
		orderService.save(order);
		return new ApiResponse("success","Accept Order");
	}
	@PutMapping("/rejectOrder/{id}")
	public ApiResponse reject(@PathVariable Long id) {
		Order order=orderService.findById(id);
		order.setProces("Reject");
		orderService.save(order);
		return new ApiResponse("success","Reject Order");
	}
    
}
