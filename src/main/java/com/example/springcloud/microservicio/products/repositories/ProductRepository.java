package com.example.springcloud.microservicio.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.libs.microservicio.commons.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
