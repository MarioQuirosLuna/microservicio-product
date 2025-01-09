package com.example.springcloud.microservicio.products.services;

import java.util.List;
import java.util.Optional;

import com.example.springcloud.microservicio.products.entities.Product;

public interface ProductService {
    
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
