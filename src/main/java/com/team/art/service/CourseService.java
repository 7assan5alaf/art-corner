package com.team.art.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.team.art.error.RecordNotFoundException;
import com.team.art.model.Course;
import com.team.art.model.User;
import com.team.art.repository.CourseRepository;
import com.team.art.response.ApiResponse;
import com.team.art.response.CourseResponse;


@Service
public class CourseService {
     @Autowired
     private CourseRepository courseRepository;
	public void addCourse(String courseTitle, String courseDesc, MultipartFile file, String link, int price,
			User user) throws IOException{
		 Course course=new Course();
		 String fileName=StringUtils.cleanPath(file.getOriginalFilename()); 
		 try {
			 if(fileName.contains("..")) {
				 throw new RecordNotFoundException("file size is bigger");
			 }
				 course.setThumbnail(file.getBytes());
				 course.setCourseDesc(courseDesc);
				 course.setCourseTitle(courseTitle);
				 course.setLinks(link);
				 course.setUser(user);
				 course.setPrice(price);
			 	courseRepository.save(course);
			 } catch (RecordNotFoundException e) {
					 e.getMessage();
				}
	}
	public ResponseEntity<List<Course>> findAll() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Course>>(courseRepository.findAll(),HttpStatus.ACCEPTED);
	}
	public CourseResponse findById(Long id) {
		Course course=new Course();
		try {
			course=courseRepository.findById(id).orElseThrow(
					()->new RecordNotFoundException("this course not found"));
			List<String>links=Arrays.asList(course.getLinks().split(","));
			return new CourseResponse(course.getCourseTitle(),course.getCourseDesc(),course.getThumbnail(),links,course.getPrice()
					,course.getUser()
					);
		} catch (RecordNotFoundException e) {
			e.getMessage();
		}
		return null;
	}
	public ApiResponse editCourse(Long id, MultipartFile thumbnail, String courseTitle, 
			String courseDesc, String links,
			int price) throws IOException {
		Course course=new Course();
		try {
			course=courseRepository.findById(id).orElseThrow(
					()->new RecordNotFoundException("this course not found"));
			course.setCourseDesc(courseDesc);
			course.setCourseTitle(courseTitle);
			course.setLinks(links);
			course.setPrice(price);
			course.setThumbnail(thumbnail.getBytes());
			courseRepository.save(course);
			return new ApiResponse("success", "edit successfully");
			
		} catch (RecordNotFoundException e) {
			e.getMessage();
		}
		return null;
	}
	public ApiResponse deleteCourse(Long id) {
			courseRepository.deleteById(id);
			return new ApiResponse("success","delete course");
	}
	public Course findCourse(Long id) {
	  
		try {
			Course course=courseRepository.findById(id).orElseThrow(
					()->new RecordNotFoundException("this course not found"));
			return course;
		} catch (RecordNotFoundException e) {
			e.getMessage();
		}
		return null;
	}
}
