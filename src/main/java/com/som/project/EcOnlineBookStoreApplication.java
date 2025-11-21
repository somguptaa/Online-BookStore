package com.som.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(
        info = @Info(
        title = "E-Commerce Book Store Management",
        version = "1.0",
        description = "Welcome to My E-Commerce Book Store Management Project",
        contact = @Contact(name ="Som Gupta",email = "somgupta0011@gmail.com")))

@SpringBootApplication
@EnableCaching
public class EcOnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcOnlineBookStoreApplication.class, args);
	}

}
