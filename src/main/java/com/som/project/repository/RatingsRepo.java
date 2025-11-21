package com.som.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.project.entity.Ratings;

public interface RatingsRepo extends JpaRepository<Ratings, Long>{

}
