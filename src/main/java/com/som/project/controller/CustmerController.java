package com.som.project.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.som.project.entity.Customer;
import com.som.project.model.ResponseMessage;
import com.som.project.service.CustmerService;
import com.som.project.utility.Constants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class CustmerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustmerController.class);
	
	
	
	@Autowired CustmerService custmerService;
	

	 @Operation(summary = "Create User Custmers",description = "e commerece online books store  register the users")
	    @ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user register successfully"),
	     @ApiResponse(responseCode = "400",description = "user register failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	@PostMapping("/custmersave")
	public ResponseEntity<ResponseMessage> createCustmers(@RequestBody Customer custmer) {
		 
		try {
		if(custmer.getEmail()==null || custmer.getEmail().isEmpty() || custmer.getName() ==null || custmer.getName().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
		}
	  
		Customer insertCustmers = custmerService.insertCustmers(custmer);
		
		if(insertCustmers!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "custmer save successfully", insertCustmers));
		} else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer Failed", insertCustmers));
  
	       }

		 }catch (Exception e) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	 
	    @PutMapping("/custmerupadte")
		public ResponseEntity<ResponseMessage> custmerUpdates(@RequestBody Customer custmer) {
		   
			try {
			if(custmer.getEmail()==null || custmer.getEmail().isEmpty() || custmer.getName() ==null || custmer.getName().isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
			}
			if(custmer.getId()==null) {
			
				Customer insertCustmers = custmerService.UpdatessCustmers(custmer);
		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "custmer update successfully", insertCustmers));
			
			 }else {
				 Customer insertCustmers = custmerService.insertCustmers(custmer);
				       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer updated successfully", insertCustmers));

			 }}catch (Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
			}
		}
	 
	 
	    @PostMapping("/custmersaveOrupdates")
		public ResponseEntity<ResponseMessage> custmerORUpdates(@RequestBody Customer custmer) {
		   
			try {
			if(custmer.getEmail()==null || custmer.getEmail().isEmpty() || custmer.getName() ==null || custmer.getName().isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and name cannot be empty"));
			}
			if(custmer.getId()==null) {
			
				Customer createdOrUpdatessCustmers = custmerService.createdOrUpdatessCustmers(custmer);
		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "custmer saved successfully", createdOrUpdatessCustmers));
			
			 }else {
				 Customer createdOrUpdatessCustmers = custmerService.createdOrUpdatessCustmers(custmer);
				       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer updated successfully", createdOrUpdatessCustmers));

			 }}catch (Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
			}
		}
	 
	        @GetMapping("/getByCustmerId/{id}")
	  		public ResponseEntity<ResponseMessage> custmerORUpdates(@PathVariable Long id) {
	  		   
	        	Customer byCustmersId = custmerService.getByCustmersId(id);
	  			if(byCustmersId!=null) {
	  		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer id getting successfully", byCustmersId));
	  			
	  			 }else {
	  				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", byCustmersId));

	  			 }
	  		}
	 

	        @GetMapping("/getAllCustmers")
	  		public ResponseEntity<ResponseMessage> getAllCustmerss() {
	  		   
	  			 List<Customer> byAllCustmers = custmerService.getByAllCustm();
	  			if(byAllCustmers!=null) {
	  		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer  getting all  successfully", byAllCustmers));
	  			
	  			 }else {
	  				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", byAllCustmers));

	  			 }
	  		}  
	        

	        
	        @GetMapping("/getAllCustmerswithpagination")
	  		public ResponseEntity<ResponseMessage> getByAllCustmerpagination(@RequestParam int page , 
	  				                                                         @RequestParam int size,
	  				                                                         @RequestParam String sortField,
	  				                                                         @RequestParam String pageDir) {
	  	    
	  	    Page<Customer> byAllCustmersWithPaginations = custmerService.getByAllCustmersWithPaginations(page,size,sortField,pageDir);
	  	     	    	 
	  	     if(byAllCustmersWithPaginations!=null) {
	  			
	  	     	  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "All Custemers getting with pagination successfully", byAllCustmersWithPaginations));
	  		    }else { 
	  		    	
	  		    	return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "All Custemers getting  pagination Failure", byAllCustmersWithPaginations));
	  		    	
	  		    }
	  			
	  			}
	        
	        
	 @GetMapping("/getCustmer")
	 @CircuitBreaker(name = "showData" ,fallbackMethod = "fallBackgetData")
	 public String showData() throws Exception {
		 
		 throw new Exception();
	 
	 }
	 
	 public String fallBackgetData(Throwable th) {
		 
		return "Payment service temporarily unavailable";
	 }
	        
	        
	  		}  
	        

      














