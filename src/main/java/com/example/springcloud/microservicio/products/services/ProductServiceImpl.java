package com.example.springcloud.microservicio.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libs.microservicio.commons.entities.Product;
import com.example.springcloud.microservicio.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Environment environment;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>)repository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    
}
