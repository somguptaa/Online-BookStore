package com.som.project.serviceImpl;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.som.project.entity.FilesEntity;
import com.som.project.entity.UserRegister;
import com.som.project.entity.mongo.UserRegisterMongo;
import com.som.project.model.UserRequest;
import com.som.project.model.UserRequestDto;
import com.som.project.repository.FileRepo;
import com.som.project.repository.UserRegisterRepo;
import com.som.project.repository.mongo.UserRegisterMongoRepo;
import com.som.project.service.UserRegisterService;

@Service
public class UserRegisterServiceimpl implements UserRegisterService ,UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceimpl.class);

	
	@Autowired
	private UserRegisterRepo userRegisterRepo;
	
	@Autowired
	private UserRegisterMongoRepo userRegisterMongoRepo;
	
	@Autowired FileRepo fileRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	

	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		 logger.info("Registration serive layer calling or started");
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
//		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		   String encodedPassword = pwdEncoder.encode(userRequestDto.getPassword());
		    user.setPassword(encodedPassword);

		user.setContactId(userRequestDto.getContactId());
		 logger.info("Registration serive layer calling or ended");
		userRegisterRepo.save(user);
		
		UserRegisterMongo userMong = new UserRegisterMongo();
		userMong.setFirstName(userRequestDto.getFirstName());
		userMong.setLastName(userRequestDto.getLastName());
		userMong.setEmail(userRequestDto.getEmail());
		userMong.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		userMong.setContactId(userRequestDto.getContactId());
		userRegisterMongoRepo.save(userMong);
		
		}catch (Exception e) {
            logger.error("New user creation process failed in Bookstore-DB . Exception:" +e.getMessage());    
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRequest getUserRegisterDetails(Long id) {
		 Optional<UserRegister> byId = userRegisterRepo.findById(id);
		UserRegister userRegister = byId.get();
		return new UserRequest(userRegister.getFirstName(), userRegister.getLastName(),userRegister.getEmail());
	}

	@Override
	public UserRegister userLoginCreate(UserRequestDto userRequestDto) {

	    // Step 1: Email check
	    UserRegister byEmail = userRegisterRepo.findByEmail(userRequestDto.getEmail());

	    // Step 2: If user exists, then verify password
	    if (byEmail != null) {

	        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

	        //  Correctly check raw password vs encoded password from DB
	        boolean isPasswordMatch = pwdEncoder.matches(
	                userRequestDto.getPassword(),
	                byEmail.getPassword()
	        );

	        if (isPasswordMatch) {
	            //  Password correct → return user
	            return byEmail;
	        } else {
	            //  Password incorrect
	            return null;
	        }
	    }

	    //  User not found
	    return null;
	}


	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserRegister user = userRegisterRepo.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
	}
	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files) {
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactId(userRequestDto.getContactId());
		userRegisterRepo.save(user);
		if(files!=null && files.length>0) {
			for (MultipartFile multipartFile : files) {
				FilesEntity fss = new FilesEntity();
				fss.setFileName(multipartFile.getOriginalFilename());
				fss.setFileType(multipartFile.getContentType());
				fss.setData(multipartFile.getBytes());
				fileRepo.save(fss);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Cacheable(value = "getAllUsers")
	public List<UserRegister> getAllUsersRegisterDetails() {
	 List<UserRegister> list = userRegisterRepo.findAll();
	 System.err.println("get the records from Database...............!");
	 return list;
	}

}
