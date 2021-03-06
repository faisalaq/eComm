package com.springlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springlearning.domain.User;
import com.springlearning.repository.UserRepository;
import com.springlearning.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		if(user == null)
			throw new UsernameNotFoundException("User with email "+email+" not found");
		
		return new CustomUserDetails(user);
	}

}
