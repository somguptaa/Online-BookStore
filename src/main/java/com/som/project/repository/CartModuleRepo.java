package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.project.entity.BooksModule;
import com.som.project.entity.CartModule;
import com.som.project.entity.Customer;

public interface CartModuleRepo extends JpaRepository<CartModule, Long>{

	CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);


}
