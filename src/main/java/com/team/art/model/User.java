package com.team.art.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Enter your name")
	@Column(name = "name")
	private String fullName;
	@Column(unique = true)
	@NotBlank(message = "Enter your email")
	@Email(message = "Enter a valaid email")
	private String email;
	@NotBlank(message = "Enter your password")
	@Length(min = 6,message = "password at least 6 characters ")
	private String password;
	@NotBlank(message = "Enter your phone")
	@Length(max = 11,min = 9,message = "phone number must at least 9 nums and maxmim 11 nums")
	@Column(name = "phone_number")
	private String number;
	@NotBlank(message = "Enter account_type")
	@Column(name = "account_type")
	private String type;
    private int age;
    @NotBlank(message = "Entre Address")
    private String address;
    @Column(columnDefinition = "LONGTEXT")
    private String bio;
    @Lob
	@Column(columnDefinition = "MEDIUMBLOB",name = "image")
    private String image;
    
   // private boolean enable=true;
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	   inverseJoinColumns = @JoinColumn(columnDefinition = "role_id",referencedColumnName = "id"),
	   joinColumns=@JoinColumn(columnDefinition = "user_id",referencedColumnName = "id")
			)
	private Set<Role>roles=new HashSet<Role>();
 	@OneToMany(mappedBy = "user")
	private Set<Product>products=new HashSet<Product>();
	public User(String fullName, String email, String password, String number,
			Set<Role> roles,String account_type,int age,String address) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.number = number;
		this.roles = roles;
		this.type=account_type;
		this.age=age;
		this.address=address;
	}

	public User() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Role role) {
		this.roles.add(role);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
//	public boolean isEnable() {
//		return enable;
//	}
//
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}
//	
	
	
}
