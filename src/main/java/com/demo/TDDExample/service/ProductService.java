package com.demo.TDDExample.service;

import java.util.List;

import com.demo.TDDExample.entity.Product;

public interface ProductService {
	
	void addProduct(Product product);
	
	Product getProductById(long Id);
	
	List<Product> getAllProducts();
	
	void deleteProductById(long Id);
	
	double calculateDiscount(long productId, int quantity);
}
