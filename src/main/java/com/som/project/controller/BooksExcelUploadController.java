package com.som.project.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.som.project.model.ResponseMessage;
import com.som.project.service.BooksExcelUploadService;
import com.som.project.utility.Constants;
import com.som.project.utility.Helper;

@RestController
public class BooksExcelUploadController {

	@Autowired
	BooksExcelUploadService booksExcelUploadService;

	@PostMapping("/uploadExcelFile")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestParam MultipartFile file) throws IOException {
	
		if(Helper.checkExcelfile(file)) {
			booksExcelUploadService.uploadExcelintoDB(file);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Excelfile save successfully"));
		
	}else {
		
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Excelfile save failed"));

	}
	
}
}