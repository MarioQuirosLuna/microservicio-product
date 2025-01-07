package com.example.springcloud.microservicio.products.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcloud.microservicio.products.entities.Product;
import com.example.springcloud.microservicio.products.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService service;


    @GetMapping("")
    public List<Product> list() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> detailsProduct(@PathVariable Long id) throws InterruptedException {
        if(id.equals(10L)){
            System.out.println("Error simulado con id 10 para pruebas Resilience4J!");
            throw new IllegalStateException("Error simulado con id 10 para pruebas Resilience4J!");
        }

        if(id.equals(7L)){
            System.out.println("Lentitud de respuesta simulada con id 7 para pruebas Resilience4G!");
            TimeUnit.SECONDS.sleep(4L);
        }
        
        Optional<Product> productOptional = service.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
