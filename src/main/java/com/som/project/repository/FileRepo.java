package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.project.entity.FilesEntity;

@Repository
public interface FileRepo extends JpaRepository<FilesEntity, Long>{

}
