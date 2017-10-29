package com.springlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springlearning.domain.Product;
import com.springlearning.domain.User;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByUser(User user);
}
