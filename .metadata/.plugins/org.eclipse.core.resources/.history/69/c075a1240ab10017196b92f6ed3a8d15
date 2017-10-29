package com.springlearning.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearning.domain.User;
import com.springlearning.repository.UserRepository;
import com.springlearning.security.Authority;

@Service
public class UserService {

	private UserRepository userRepo;
	
	public User saveUser(User user){
		
//		assign default authority to newly registered user on the register.html page
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER", user));
		authorities.add(new Authority("ROLE_DASHBOARD", user));
		user.setAuthorities(authorities);
		
		return userRepo.save(user);
	}

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
}
