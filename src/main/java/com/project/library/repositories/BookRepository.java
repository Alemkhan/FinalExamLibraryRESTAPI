package com.project.library.repositories;

import com.project.library.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository implements Deletable{

    private JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> getAllBooksFromRepo() {
        String sql = "SELECT books.book_id, books.book_title, authors.author_firstname, authors.author_lastname, genres.genre_name, books.isbookavailable " +
                "FROM books" +
                " LEFT JOIN book_authors as authors ON books.author_id = authors.author_id" +
                " LEFT JOIN book_genres as genres ON books.author_id = genres.genre_id; ";
        return jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book existingBook = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                        );
                existingBook.setBookAvailable(resultSet.getBoolean(6));
                return existingBook;
            }
        });
    }

    public List<Book> getAvailableBooksFromRepo() {
        String sql = "SELECT * FROM books WHERE isbookavailable = true";
        return jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book availableBook = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                availableBook.setBookAvailable(resultSet.getBoolean(6));
                return availableBook;
            }
        });
    }

    public Book getBookByID(int bookID) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{bookID}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO books VALUES (?," +
                "(SELECT author_id FROM book_authors WHERE author_firstname = ? AND author_lastname = ?) ," +
                "(SELECT genre_id FROM book_genres WHERE genre_name = ?)," +
                "?, true);";
        try {
            jdbcTemplate.update(sql,
                    book.getId(),
                    book.getAuthor_firstname(),
                    book.getAuthor_lastname(),
                    book.getGenre_name(),
                    book.getBook_title()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteById(int bookID) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        jdbcTemplate.update(sql, bookID);
    }
}
