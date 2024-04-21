package com.demo.TDDExample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.TDDExample.entity.Product;
import com.demo.TDDExample.exception.ProductNotFoundException;
import com.demo.TDDExample.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);	
	}

	@Override
	public Product getProductById(long Id) {
		
		return productRepository.findById(Id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + Id));
	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public void deleteProductById(long Id) {

		productRepository.deleteById(Id);
	}

	@Override
	public double calculateDiscount(long productId, int quantity) {
		Product product = getProductById(productId);
		double price = product.getPrice();
		double discount = 0.0;
		
		// Apply 10% discount if total price exceeds 1000 and quantity is more than 5
		if(price * quantity > 1000 && quantity > 5) {
			discount = 0.1;
		}
		return discount;
	}
	

}
