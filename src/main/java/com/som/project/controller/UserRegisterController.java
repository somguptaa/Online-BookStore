package com.som.project.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.som.project.entity.UserRegister;
import com.som.project.model.ResponseMessage;
import com.som.project.service.UserRegisterService;
import com.som.project.utility.Constants;


@RestController
public class UserRegisterController {
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	//For Customer register
	@PostMapping(value = "/userregisters", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRegister userRegister) {
		
		String insertUserRegister = userRegisterService.insertUserRegister(userRegister);
		
		//User friendly response msg
    return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", insertUserRegister));
		
	}
	

	
	
	
	

}
