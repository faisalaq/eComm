package com.springlearning.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.springlearning.domain.User;

@Entity
public class Authority implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3828453036436191561L;
	private String role;
	private User user;
	private Long id;
	
	public Authority(){
		
	}
	
	public Authority(String role, User user) {
		super();
		this.role = role;
		this.user = user;
	}


	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

	
	public void setAuthority(String role) {
		this.role = role;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
