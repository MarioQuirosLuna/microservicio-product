package com.example.springcloud.microservicio.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springcloud.microservicio.products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
