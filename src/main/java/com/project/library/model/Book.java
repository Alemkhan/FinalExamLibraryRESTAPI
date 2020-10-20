package com.project.library.model;

public class Book {

    private int id;
    private String book_title;
    private String author_firstname;
    private String author_lastname;
    private String genre_name;
    private boolean isBookAvailable;

    public Book(int id, String book_title, String author_fname, String author_lname, String genre) {
        this.id = id;
        this.book_title = book_title;
        this.author_firstname = author_fname;
        this.author_lastname = author_lname;
        this.genre_name = genre;
        this.isBookAvailable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor_firstname() {
        return author_firstname;
    }

    public void setAuthor_firstname(String author_firstname) {
        this.author_firstname = author_firstname;
    }

    public String getAuthor_lastname() {
        return author_lastname;
    }

    public void setAuthor_lastname(String author_lastname) {
        this.author_lastname = author_lastname;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public boolean isBookAvailable() {
        return isBookAvailable;
    }

    public void setBookAvailable(boolean bookAvailable) {
        isBookAvailable = bookAvailable;
    }
}
