package com.team.art.response;


import java.util.ArrayList;
import java.util.List;

import com.team.art.model.User;

public class CourseResponse {

	private String courseTitle;
	private String courseDesc;
	private byte[] thumbnail;
	private List<String>links=new ArrayList<>();
	private int price;
	private User user;
	
	public CourseResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public CourseResponse(String courseTitle, String courseDesc, byte[] thumbnail, List<String> links, int price,
			User user) {
		super();
		this.courseTitle = courseTitle;
		this.courseDesc = courseDesc;
		this.thumbnail = thumbnail;
		this.links = links;
		this.price = price;
		this.user = user;
	}

	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public byte[] getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<String> getLinks() {
		return links;
	}
	public void setLinks(List<String> links) {
		this.links = links;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
