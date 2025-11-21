package com.som.project.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.som.project.entity.mongo.UserRegisterMongo;

@Repository  //it is a optional
public interface UserRegisterMongoRepo extends MongoRepository<UserRegisterMongo,String>{

	


	
	
}
