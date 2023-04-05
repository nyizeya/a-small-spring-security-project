package com.jdc.security.demo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.security.demo.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
