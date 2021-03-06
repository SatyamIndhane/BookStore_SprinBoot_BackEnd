package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.dto.CustomerDto;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.entity.CustomerInformation;
import com.bridgelabz.bookstore.response.BookResponse;
import com.bridgelabz.bookstore.service.Customerservice;

@CrossOrigin("http://localhost:4200")
@RestController
public class CustomerController {

	@Autowired
	Customerservice service;
	
	@PostMapping( value = "carts/addcart/{bookId}/{userId}")
	public ResponseEntity<BookResponse> addCart( @PathVariable( value = "bookId") long bookId,
			                                            @PathVariable( value = "userId") long userId) {
		List<BookInformation> bookinfo = service.getBookfromCart(bookId, userId);

			 return ResponseEntity.status(HttpStatus.OK).body( new BookResponse("Book Details Found..", bookinfo));
	}
	   

	@PostMapping( value = "customers/addcustomer")
	public ResponseEntity<BookResponse> addCustomerDetails( @RequestBody CustomerDto dto) {
		CustomerInformation is_created = service.addCustomerDetails(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body( new BookResponse("Added Customer Details is:", is_created));
	
}
}
