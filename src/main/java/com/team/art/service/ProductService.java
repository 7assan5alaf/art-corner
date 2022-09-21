package com.team.art.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.team.art.CurrentUser;
import com.team.art.error.RecordNotFoundException;
import com.team.art.model.Categories;
import com.team.art.model.Product;
import com.team.art.model.User;
import com.team.art.repository.CategoryRepository;
import com.team.art.repository.ProductRepository;



@Service
public class ProductService extends CurrentUser{
    
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TokenService tokenService;
	
	public List<Product>getAll(){
		return productRepository.findAll();
	}
	
	public List<Product>getByUser(String token){
		tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		return productRepository.findByUser_id(user.getId());
	}
	// to add product for a user 
	// authenticate from her token
	// save by user
	public Product addProduct(MultipartFile file,String name ,String desc
			,int pric,Long cate_id,User user) throws IOException {
		Categories cate=categoryRepository.findById(cate_id)
				.orElseThrow(()->new RecordNotFoundException("Category not found")); 
	    Product p=new Product();
		p.setCategories(cate);
		p.setUser(user);
		p.setDescription(desc);
		p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		p.setName(name);
		p.setCreateDate(new Date());
		p.setPrice(pric);
		return productRepository.save(p);
	
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public Product editProduct(Long id,MultipartFile file,String name,String desc,int price,Long cate_id) throws IOException {
		Product p=productRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("product not found"));
	    	p.setCategories(categoryRepository.findById(cate_id).get());
	     	p.setDescription(desc);
		    p.setName(name);
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			p.setPrice(price);
			return productRepository.save(p);
	}
	
	public Product findByid(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new RecordNotFoundException("product not found"));
	}

	public List<Product> findByCategory(Long id) {
		Categories cate=categoryRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("category not found"));
		return productRepository.findByCategories_Id(cate.getId());
	}
	
	public ResponseEntity<Page<Product>>findAll(String keyWord,Pageable pageable){
		return new ResponseEntity<Page<Product>>(productRepository.search(keyWord, pageable),HttpStatus.ACCEPTED);
	}
}
