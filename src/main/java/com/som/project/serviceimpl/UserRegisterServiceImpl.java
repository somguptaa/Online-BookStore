package com.som.project.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.project.entity.UserRegister;
import com.som.project.repository.UserRegisterRepo;
import com.som.project.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
	
	
	@Autowired
	private UserRegisterRepo userRegisterRepo;

	@Override
	public String insertUserRegister(UserRegister userRegister) {
		
		userRegisterRepo.save(userRegister);
		
		return "save successfully";
	}

}
