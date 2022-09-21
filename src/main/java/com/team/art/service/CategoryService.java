package com.team.art.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.art.error.RecordNotFoundException;
import com.team.art.model.Categories;
import com.team.art.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Categories>getAll(){
		return categoryRepository.findAll();
	}
	
	public Categories saveCate(Categories categories) {
		return categoryRepository.save(categories);
	}
	
	public void deleteCate(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public Categories update(Long id,Categories cate) {
		Categories categories=findById(id);
		categories.setName(cate.getName());
	return categoryRepository.save(categories);
		
	}

	public Categories findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("not found"));
	}
}
