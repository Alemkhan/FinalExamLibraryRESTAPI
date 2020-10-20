package com.project.library.controllers;

import com.project.library.model.Admin;
import com.project.library.model.Book;
import com.project.library.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/available_books")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping(path="/books/{bookID}")
    public Book getAdminById(@PathVariable int bookID){
        return bookService.getBookById(bookID);
    }

    @PostMapping(path="/books")
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "A book " + book.getBook_title() + " has been succesfully added";
    }

    @PutMapping(path="/books/update/{bookID}")
    public String updateBookData(@PathVariable int bookID,
                                 @RequestParam(name = "author_id") String author_name,
                                 @RequestParam(name = "genre_id") String genre_name,
                                 @RequestParam(name = "book_title") String book_title,
                                 @RequestParam(name = "isbookavailable") boolean available){
        return "The data of admin with ID " + bookID +" has been updated";
    }

    @DeleteMapping(path="/books/delete/{bookID}")
    public String deleteBook(@PathVariable int bookID){
        try {
            bookService.deleteById(bookID);
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "Admin with ID " + bookID + "deleted";
    }
}
