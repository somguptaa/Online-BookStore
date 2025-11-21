package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.project.entity.Customer;

@Repository  // its a optional
public interface CustmerRepoo  extends JpaRepository<Customer, Long>{

}
