package com.demo.TDDExample;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.TDDExample.entity.Product;
import com.demo.TDDExample.repository.ProductRepository;
import com.demo.TDDExample.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;
	
	@BeforeEach
	public void setUp() {
		// Mocking repository behavior
		List<Product> prodList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProd_id(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0);
        
        Product product2 = new Product();
        product2.setProd_id(2L);
        product2.setName("Product 2");
        product2.setPrice(100.0);
        
        prodList.add(product1);
        prodList.add(product2);
        
        productRepository.saveAll(prodList);
        /*
         For below line of code it gives error :
         Unnecessary stubbing detected.
         */
        //Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        //Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        
        Mockito.lenient().when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty()); 
	}
	
	@AfterEach
	public void cleanUp() {
		productRepository.deleteAll();
	}
	
	@Test
	public void testAddProduct() {
		//Arrange
		Product product = new Product();
		product.setName("New_ProductTest_1");
		product.setPrice(150.0);
		
		//Act
		productService.addProduct(product);
		
		//Assert
		
		/*
		 The verify() method from Mockito is typically used to verify that certain interactions with mocked objects occurred during the test
		 In this case, verify(productRepository, times(1)).save(product) checks that the save() method of the productRepository mock object 
		 was called exactly once with the product object as an argument.
		 */
		verify(productRepository, times(1)).save(product);
		
	}
	
	@Test
	public void testGetProductById() {
		
		//Arrange
		long productId = 1L;
		
		//Act
		Product product = productService.getProductById(productId);
		
		//Assert
		assertNotNull(product);
		assertEquals(productId, product.getProd_id());
		  
	}
	
	@Test
	public void testGetAllProducts() {
		//Act
		List<Product> products = productService.getAllProducts();
		
		//Assert
		assertNotNull(products);
		assertEquals(2, products.size());
		
	}
	
	@Test
	public void testDeleteProductById() {
		//Arrange
		long productId = 1L;
		
		//Act
		productService.deleteProductById(productId);

		//Assert
		verify(productRepository, times(1)).deleteById(productId);
	}
	
	@Test
	public void testCalculateDiscount() {
		//Arrange
		long productId = 1L;
		int quantity = 5;
		
		//Act
		double discount = productService.calculateDiscount(productId, quantity);
		
		//Assert
		/*
		 In this case, the assertion checks if the discount value is approximately equal to 0.0 with a maximum difference of 0.01. 
		 This is useful when comparing floating-point numbers, where exact equality might not be possible due to rounding errors or precision differences.
		 */
		assertEquals(0.0, discount, 0.01);
	}
}
