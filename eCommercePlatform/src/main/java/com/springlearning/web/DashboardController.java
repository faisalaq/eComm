package com.springlearning.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlearning.domain.Product;
import com.springlearning.domain.User;
import com.springlearning.repository.ProductRepository;

@Controller
public class DashboardController {
	
	private ProductRepository productRepo;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String rootGet(){
		return "redirect:/dashboard";
	}

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboardGet(@AuthenticationPrincipal User user ,ModelMap model){
		List<Product> products = productRepo.findByUser(user);
		model.put("products", products);
		return "dashboard";
		
	}

	@Autowired
	public void setProductRepo(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	
}
