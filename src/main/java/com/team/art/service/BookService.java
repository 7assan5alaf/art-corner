package com.team.art.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.team.art.model.Book;
import com.team.art.repository.BookRepository;
import com.team.art.response.ApiResponse;

@Service
public class BookService {
      @Autowired
      private BookRepository bookRepository;
        	 
	public ApiResponse uploadBook(MultipartFile file, String bookDesc,String bookTitel
			,MultipartFile cover,String author) throws IOException {
		Book book=new Book();
		 String fileName=StringUtils.cleanPath(file.getOriginalFilename()); 
		 try {
			 if(fileName.contains("..")) {
				 return new ApiResponse("invalid","file name contains invalid path sequence");
			 }
				book.setBookTitle(bookTitel);
				book.setData(file.getBytes());
				book.setBookDesc(bookDesc);
				book.setCover(Base64.getEncoder().encodeToString(cover.getBytes()));
				book.setAuthor(author);
				bookRepository.save(book);
			return new ApiResponse("valid","add book successfully");
		} catch (Exception e) {
			return new ApiResponse("invalid", "could not save file "+fileName);
		}
		
	}
	
	public List<Book>getAll(){
		return bookRepository.findAll();
	}
}
