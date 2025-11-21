package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.project.entity.BooksExcelFile;

public interface BooksExcelFileRepo  extends JpaRepository<BooksExcelFile, Long>{

}
