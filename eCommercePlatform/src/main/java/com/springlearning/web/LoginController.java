package com.springlearning.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlearning.domain.User;
import com.springlearning.service.UserService;


@Controller
public class LoginController {
	
	private UserService userService;
	
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap  model){
		return "login";
	}
	

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet(ModelMap  model){
		User user = new User();
		model.put("user", user);
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(@ModelAttribute User user, ModelMap model){
		userService.saveUser(user);
		return "redirect:/login";
		
	}
		
}
