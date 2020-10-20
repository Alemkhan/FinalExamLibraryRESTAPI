package com.project.library.services;

import com.project.library.model.Book;
import com.project.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooksFromRepo();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooksFromRepo();
    }

    public Book getBookById(int bookID) {
        return bookRepository.getBookByID(bookID);
    }

    public void addBook(Book book) {
        bookRepository.insertBook(book);
    }

    public void deleteById(int bookID) {
        bookRepository.deleteById(bookID);
    }
}
