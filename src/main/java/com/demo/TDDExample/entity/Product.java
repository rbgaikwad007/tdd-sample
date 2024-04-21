package com.demo.TDDExample.entity;

import javax.persistence.*;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long prod_id;
	private String name;
	private double price;
	
	public Product() {};
	
	public Product(long prod_id, String name, double price) {
		super();
		this.prod_id = prod_id;
		this.name = name;
		this.price = price;
	}
	
	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prod_id) {
		this.prod_id = prod_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", name=" + name + ", price=" + price + "]";
	}
	
	
	

}
