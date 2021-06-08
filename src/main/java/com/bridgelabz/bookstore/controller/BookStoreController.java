package com.bridgelabz.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.response.BookResponse;
import com.bridgelabz.bookstore.service.IBookService;

@CrossOrigin("http://localhost:4200")
@RestController
public class BookStoreController {

	@Autowired
	IBookService bookservice;

	@PostMapping("books/addbook")
	public ResponseEntity<BookResponse> addBook(@RequestBody BookDto information) {
		bookservice.addBooks(information);

		return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse("The Book details are", information));
	}


	
	@GetMapping("books")
	public ResponseEntity<BookResponse> getBooks() {
		List<BookInformation> books = bookservice.getBookInfo();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Book details are", books ));
 
	}
	@GetMapping("books/unsorting")
	public ResponseEntity<BookResponse> sort(){
		List<BookInformation> list=bookservice.sortGetAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
	}


	
	@GetMapping("books/sorting")
	public ResponseEntity<BookResponse> sorting(@RequestParam("value") boolean value){
		List<BookInformation> list=bookservice.sorting( value);
		if (value==true) {
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
		}

	}
	@GetMapping( value = "books/pagewise/{pagenumber}")
	public ResponseEntity<BookResponse> getBookPagewise( @PathVariable( value = "pagenumber") int pagenumber) {
		List<BookInformation> pageList = bookservice.findAllPageBySize( pagenumber);
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("Successfull", pageList));
	}
	
	@GetMapping( value = "books/{bookId}")
 public ResponseEntity<BookResponse> getBookbyId( @PathVariable("bookId") long bookId) {
		BookInformation info = bookservice.getBookbyId(bookId);	
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",info));
	}
	
}
