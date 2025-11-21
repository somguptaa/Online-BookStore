package com.som.project.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.som.project.model.ErrorResponseMessage;
import com.som.project.utility.Constants;

@ControllerAdvice
public class RestGlobalException {
	
	
	@ExceptionHandler(CustmerIDNotFoundException.class)
	public ResponseEntity<Object> custmerHandleException(CustmerIDNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add("Error : Customer Id not found");
		details.add("Detailed Message:" + ex.getLocalizedMessage());
		details.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "NOT-FOUND",details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
	
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<Object> custmerHandleBookException(BookIdNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add("Error : Book Id not found");
		details.add("Detailed Message:" + ex.getLocalizedMessage());
		details.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponseMessage error = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "NOT-FOUND",details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
	

}
