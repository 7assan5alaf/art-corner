package com.team.art.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	@Column(name = "role_name")
	private String name;
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Set<User>users=new HashSet<User>();
	public Role() {
		
	}

	public Role(String name) {
		this.name=name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(User user) {
		this.users.add(user);
	}
	
	
}
