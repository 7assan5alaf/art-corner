package com.team.art.controller;

import java.util.List;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.art.CurrentUser;
import com.team.art.dto.UserSginIn;
import com.team.art.error.RecordNotFoundException;
import com.team.art.message.EmailSender;
import com.team.art.message.Message;
import com.team.art.model.Book;
import com.team.art.model.Course;
import com.team.art.model.Event;
import com.team.art.model.Product;
import com.team.art.model.User;
import com.team.art.repository.EventRepository;
import com.team.art.repository.UserReopsitory;
import com.team.art.response.ApiResponse;
import com.team.art.response.CourseResponse;
import com.team.art.response.ResponseDto;
import com.team.art.service.BookService;
import com.team.art.service.CourseService;
import com.team.art.service.HomeService;
import com.team.art.service.ProductService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HomeController extends CurrentUser {

	  @Autowired
	  private ProductService productService;
	  @Autowired
	  private HomeService userServic;
	  @Autowired
	  private UserReopsitory userReopsitory;
	  @Autowired 
	  private EmailSender emailSender;
	  @Autowired
	  private BookService bookService;
	  @Autowired
	  private EventRepository eventRepository;
	  @Autowired
	  private CourseService courseService;
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  @InitBinder
		public void intiBinder(WebDataBinder binder) {
			StringTrimmerEditor editor=new StringTrimmerEditor(true);
			binder.registerCustomEditor(String.class, editor);
		}
	  
	  //get all product
	@GetMapping("/home")
	public List<Product> home() {
		return productService.getAll();
	}
	
	@GetMapping("/product/{keyWord}")
	public ResponseEntity<Page<Product>>getAll(@PathVariable("keyWord") String keyWord,Pageable pageable){
		return productService.findAll(keyWord,pageable);
	}
	//view product
	@GetMapping("/viewProduct/{id}")
	public Product viewProduct(@PathVariable("id") Long id) {
		return productService.findByid(id);
	}
	
	//get all products exist in category
	@GetMapping("/products/{id}")
	public List<Product>findAllByCategory(@PathVariable("id") Long id){
		return productService.findByCategory(id);
	}
	
	// register 
	@PostMapping("/sginup")
	public ResponseDto sginUp(@RequestBody User user) throws MessagingException{
		Message message=new Message();
		User user2=userReopsitory.findByEmail(user.getEmail());
		if(Objects.nonNull(user2))
		{
			emailSender.sendEmail(user2.getEmail(),message.getSubject(),
					"Hi "+user2.getFullName()+"\nEmail Adress already in use");
			 ResponseDto responseDto=new ResponseDto("invaild","Email Adress already in use",null);
			return responseDto;
		}else if(Objects.isNull(user2)) {
			 userServic.saveUser(user);
		emailSender.sendEmail(user.getEmail(),message.getSubject(),
				"Hi "+user.getFullName()+"\n"+message.getMessageUser());
	     ResponseDto responseDto=new ResponseDto("success","save user in database",null);
        return responseDto;
      }
		return null;
	}
	// login 
	@PostMapping("/login")
	public ResponseDto login(@Valid @RequestBody UserSginIn sginIn){
		return userServic.login(sginIn);
	}
	
	// Book
	@GetMapping("/books")
	public ResponseEntity<List<Book>>findAll(){
		return new ResponseEntity<List<Book>>(bookService.getAll(),HttpStatus.ACCEPTED);
	}

   //Event
	@GetMapping("/viewEvent")
	 public ApiResponse event() {
		 List<Event>events=eventRepository.findAll();
		 ApiResponse response=new ApiResponse("success", "no event exist");
		 for(Event event:events) {
			 if(event.getProces().equals("Accept")) {
				 User user=userReopsitory.findByEmail(event.getEmail());
				     response.setMessage("Event is already exist  by "+user.getFullName()+
						 "\nAll of them you can attend this event in "+event.getLocation()
						 +" and Event rescheduled day "
						 +event.getDate()+" \nWe wish you a nice day ");    
			 }
		 }
		 return response; 
	 }
	
	//Course
	@GetMapping("/course")
	public ResponseEntity<List<Course>>findCourses(){
		return courseService.findAll();
	}
	//view course
	@GetMapping("/viewCourse/{id}")
	public CourseResponse getCourse(@PathVariable Long id){
		return courseService.findById(id);
	}
	 
	// view profile
	@GetMapping("/user/{id}")
	public User profile(@PathVariable Long id) {
		return userReopsitory.findById(id)
				.orElseThrow(()->new RecordNotFoundException("User not found"));
	}
	// forget password
	@PostMapping("/forgetPassword")
	public ApiResponse forgetPassword(@RequestParam String email) throws MessagingException {
		User user=userReopsitory.findByEmail(email);
		if(user==null) {
			return new ApiResponse("success","Invalid email");
		}else {
	      emailSender.sendEmail(email, "Change your password!", "http://localhost:8081/changePassword/"+user.getId());
	      return new ApiResponse("success", "send link to your gemail to change password");
		}
	}
	@PutMapping("/changePassword/{id}")
	public ApiResponse changePassword( @PathVariable Long id,@RequestParam String password,@RequestParam String newPassword) {
		User user=userReopsitory.findById(id).get();
		if(password.equals(newPassword)) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userReopsitory.save(user);
			return new ApiResponse("success", "change your password successfuly");
		}else {
			return new ApiResponse("success", "Invalid password");
		}
	}
}
