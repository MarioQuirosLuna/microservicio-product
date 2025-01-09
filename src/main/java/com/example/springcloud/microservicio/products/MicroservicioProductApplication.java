package com.example.springcloud.microservicio.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.example.libs.microservicio.commons.entities"})//Se pueden agregar varios separados por comas
public class MicroservicioProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProductApplication.class, args);
	}

}
