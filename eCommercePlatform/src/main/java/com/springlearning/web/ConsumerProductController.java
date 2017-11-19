package com.springlearning.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springlearning.domain.Cart;
import com.springlearning.domain.Product;
import com.springlearning.domain.User;
import com.springlearning.repository.ProductRepository;
import com.springlearning.repository.UserRepository;

@Controller
@RequestMapping("products")
public class ConsumerProductController {

	private ProductRepository productRepo;
	private UserRepository userRepo;
	
	@RequestMapping(value="{productId}", method=RequestMethod.GET)
	public String viewProduct(@PathVariable Long productId, ModelMap model){
		Product product = productRepo.findOne(productId);
		model.put("product", product);
		return "product";
	}
	
	@RequestMapping(value="{productId}", method=RequestMethod.POST)
	public @ResponseBody Cart addProductToCart(@AuthenticationPrincipal User user, HttpServletRequest request,  
			@PathVariable Long productId, ModelMap model){
		
		//if user isnt logged in then have them create an account
		Cart cart = new Cart();
		if(user == null){
			return null;
		}
		else{
		
			//store cart info n the user domain object
			user = userRepo.findOne(user.getId());
			
			//create shopping cart object and populate it with the product
			cart.setUser(user);
			cart.setUserId(user.getId());
			
			Set<Product> products = new HashSet<>();
			Set<Cart> carts = new HashSet<>();
			
			Product product = productRepo.findOne(productId);
			product.setCart(carts);
			products.add(product);
			
			cart.setProducts(products);
			cart.setDateAdded(new Date());
			carts.add(cart);
			
			productRepo.save(product);
		}
		return cart;
	}
	
	@Autowired
	public void setProductRepo(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	
}
