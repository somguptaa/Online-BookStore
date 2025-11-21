package com.som.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.project.entity.BooksModule;
import com.som.project.entity.CartModule;
import com.som.project.entity.Customer;
import com.som.project.exceptions.BookIdNotFoundException;
import com.som.project.exceptions.CustmerIDNotFoundException;
import com.som.project.repository.BooksModuleRepo;
import com.som.project.repository.CartModuleRepo;
import com.som.project.repository.CustmerRepoo;
import com.som.project.service.CartModuleService;

@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CartModuleRepo cartModuleRepository;
	@Autowired 
	private  CustmerRepoo custmerRepoo;
	@Autowired 
	private BooksModuleRepo booksModuleRepo;
	
	@Override
	public CartModule addToCart(Long custemerId, Long bookId, int quantity) {
	    
	    //  Step 1: Check if customer exists in DB using ID
	    // If not found, throw custom exception "Customer Id Not Found"
	    Customer customer = custmerRepoo.findById(custemerId)
	        .orElseThrow(() -> new CustmerIDNotFoundException("Custmer Id Not found"));
	    
	    
	    //  Step 2: Check if book exists in DB using ID
	    // If not found, throw custom exception "Book Id not Found"
	    BooksModule booksModule = booksModuleRepo.findById(bookId)
	        .orElseThrow(() -> new BookIdNotFoundException("Book Id not Found"));
	    
	    
	    //  Step 3: Check whether this customer already added this book in their cart
	    // It helps to avoid duplicate entries for the same book
	    CartModule cartItem = cartModuleRepository.findByCustomerAndBooksModule(customer, booksModule);
	    
	    
	    //  Step 4: If cartItem already exists, update quantity
	    if (cartItem != null) {
	        // Add the new quantity to existing one
	        cartItem.setQuantity(cartItem.getQuantity() + quantity);
	    } 
	    //  Step 5: If cartItem not found, create new cart record
	    else {
	        // Create new CartModule object with quantity, book, and customer
	        cartItem = new CartModule(quantity, booksModule, customer);
	    }
	    
	    
	    //  Step 6: Calculate total price for that book (quantity × book price)
	    cartItem.setTotalPrice(cartItem.getQuantity() * booksModule.getPrice());
	    
	    
	    //  Step 7: Save updated/created cart record to DB
	    // This ensures cart data is persistent in database
	    return cartModuleRepository.save(cartItem);
	}

	@Override
	public void deleteToCart(Long id) {
		
		cartModuleRepository.deleteById(id);
		
	}

}
