package com.som.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.som.project.entity.UserRegister;
import com.som.project.model.UserRequest;
import com.som.project.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister  insertUserRegister(UserRequestDto userRequestDto);

	public UserRequest getUserRegisterDetails(Long  id);

//	public UserRegister checkUserDetails(UserRequestDto userRequestDto);

	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files);

	public List<UserRegister> getAllUsersRegisterDetails();

	public UserRegister userLoginCreate(UserRequestDto userRequestDto);

	
	

}
