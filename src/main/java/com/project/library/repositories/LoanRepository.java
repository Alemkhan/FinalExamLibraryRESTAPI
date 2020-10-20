package com.project.library.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Repository
public class LoanRepository {

    private JdbcTemplate jdbcTemplate;
    private CellRepository cellRepository;

    public LoanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createLoan(String book_title, int customer_id) {
        String sql = "INSERT INTO library_loan(admin_id, book_id, customer_id, cell_id, issuing_date, issue_outcome) " +
                "VALUES(1, (SELECT book_id FROM books WHERE book_title = ?), " +
                "?, (SELECT cell_id FROM cells WHERE iscellavailable = true LIMIT 1), current_date, " +
                "current_date + interval '14 DAY' );" +
                "UPDATE cells SET iscellavailable = false WHERE cell_id IN (SELECT cell_id " +
                "FROM library_loan WHERE customer_id = ?);" +
                "UPDATE books SET isbookavailable = false WHERE book_id IN (SELECT book_id " +
                "FROM library_loan WHERE book_title = ?)";
        try {
            jdbcTemplate.update(sql,
                    book_title,
                    customer_id,
                    customer_id,
                    book_title
            );
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 4;
        }

    }

    public void updateLoanData(int customer_id, String book_title) {
        String sql = "UPDATE library_loan SET return_date = current_date WHERE customer_id = ? AND " +
                "book_id = (SELECT book_id FROM books WHERE book_title = ?);";
        try {
            jdbcTemplate.update(sql, customer_id, book_title);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
