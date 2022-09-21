package com.team.art.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
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

import com.team.art.model.Categories;
import com.team.art.model.Event;
import com.team.art.model.Product;
import com.team.art.model.User;
import com.team.art.response.ApiResponse;
import com.team.art.service.BookService;
import com.team.art.service.CategoryService;
import com.team.art.service.EventService;
import com.team.art.service.ProductService;
import com.team.art.service.UserServic;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BookService bookService;
	@Autowired
	private EventService eventService;
	@Autowired
	private UserServic servic;
	@InitBinder
	public void intiBinder(WebDataBinder binder) {
		StringTrimmerEditor editor=new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, editor);
	}
	//Manage Category
	@GetMapping("/cate")
	public ResponseEntity<?>findAll(){
		return ResponseEntity.ok(categoryService.getAll());
	}
	@GetMapping("/artist")
	public ResponseEntity<List<User>>getArtist(){
		return new ResponseEntity<List<User>>(servic.findArtist(),HttpStatus.ACCEPTED);
	}
	 
	
	@PostMapping("/add/cate")
	public ResponseEntity<?>addCate(@Valid @RequestBody Categories categories){
		return ResponseEntity.ok(categoryService.saveCate(categories));
	}
	@DeleteMapping("/delete/cate")
	public ResponseEntity<?>delete(@RequestParam Long id){
		categoryService.deleteCate(id);
		return ResponseEntity.ok("delete successfully");
	}
	@PutMapping("/edit/cate/{id}")
	public ResponseEntity<?>update(@PathVariable("id") Long id,@RequestBody Categories categories){
		
		return ResponseEntity.ok(categoryService.update(id, categories));
	}
	// Manage Product
	@DeleteMapping("/delete/product")
	public Map<String,Boolean> deletePaint(@RequestParam Long id){
		Product p=productService.findByid(id);
		productService.deleteProduct(p.getId());
		Map<String, Boolean>response=new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	// manage book
	@PostMapping("/upload/book")
	public ApiResponse uploadBook(@RequestParam MultipartFile book,
			@RequestParam MultipartFile cover,@RequestParam String bookTitel
			,@RequestParam String bookDesc, @RequestParam String author) throws IOException {
		return bookService.uploadBook(book, bookDesc, bookTitel, cover,author);
	}
	
	// Manage Event
	@GetMapping("/event")
	public List<Event>getAll(){
		return eventService.findAll();
	}
	@PutMapping("/acceptEvent/{id}")
	public ApiResponse accept(@PathVariable("id")Long id ) {
		Event event=eventService.findById(id);
		event.setProces("Accept");
		eventService.save(event);
		return new ApiResponse("success", "accept of work of event");
	}
	@PutMapping("/rejectEvent/{id}")
	public ApiResponse reject(@PathVariable("id")Long id ) {
		Event event=eventService.findById(id);
		event.setProces("Reject");
		eventService.save(event);
		return new ApiResponse("success", "reject of work of event");
	}
	
	@DeleteMapping("deleteEvent/{id}")
	public ApiResponse deleteEvent(@PathVariable Long id) {
		return eventService.deleteEvent(id);
	}
   
}
