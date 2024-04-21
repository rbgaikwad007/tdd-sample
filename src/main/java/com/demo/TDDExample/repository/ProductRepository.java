package com.demo.TDDExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.TDDExample.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
