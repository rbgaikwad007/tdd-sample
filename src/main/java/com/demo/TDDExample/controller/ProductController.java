package com.demo.TDDExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.TDDExample.entity.Product;
import com.demo.TDDExample.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Void> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);

	}

	
	@GetMapping("/{id}/discount")
	public ResponseEntity<Double> getProductDiscount(@PathVariable("id") long id,
				@RequestParam("quantity") int quantity) {
		double discount = productService.calculateDiscount(id, quantity);
		return ResponseEntity.ok(discount);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable("id") long id) {
		productService.deleteProductById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
