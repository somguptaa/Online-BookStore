package com.som.project.service;

import java.util.List;

import com.som.project.entity.Ratings;
import com.som.project.model.RatingsDto;

public interface RatingService {


	public Ratings createRatingRivews(RatingsDto ratingsDto);

	public List<Ratings> getByAllReview();

}
