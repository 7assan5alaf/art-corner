package com.team.art.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "book_name")
	private String bookTitle;
	@Column(name="description")
    private	String bookDesc;
	
	private String author;
    @Lob
    private byte[] data;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String cover;
    
	public Book() {
		super();
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public byte[] getBook() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Book(String bookTitle, String bookDesc, byte[] data,String cover) {
		this.bookTitle = bookTitle;
		this.bookDesc = bookDesc;
		this.data = data;
		this.cover=cover;
	}
	
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
    
    
}
