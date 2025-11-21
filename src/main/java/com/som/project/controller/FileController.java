package com.som.project.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.som.project.entity.FilesEntity;
import com.som.project.repository.FileRepo;

@RestController
public class FileController {
	
	@Autowired FileRepo fileRepo;
	
	@PostMapping("/uploadfiles")
	public ResponseEntity<String>  uploadFile(@RequestParam MultipartFile file) throws IOException{
		
		FilesEntity fss = new FilesEntity();
		fss.setFileName(file.getOriginalFilename());
		fss.setFileType(file.getContentType());
		fss.setData(file.getBytes());
		fileRepo.save(fss);
		return ResponseEntity.ok("files insert succesfully:" +file.getOriginalFilename());
		
	}
	
	@PostMapping("/uploadmultifiles")
	public ResponseEntity<List<Object>>  uploadFileMulti(@RequestParam MultipartFile[] files) throws IOException{
		
		List<Object> response = Arrays.stream(files).map(s->{
			try {
				return uploadFile(s);
				
			}catch (Exception e) {
				return "files upload failed" +e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);
		
		
		
		
		
		
		
		
	}
	
	
	

}
