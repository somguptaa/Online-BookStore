package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.project.entity.BooksModule;

public interface BooksModuleRepo extends JpaRepository<BooksModule, Long>{

}
