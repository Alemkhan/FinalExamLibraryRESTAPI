package com.project.library.repositories;

import com.project.library.model.Customer;
import com.project.library.model.LibraryLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository implements Deletable{

    private JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Customer> getAllCustomersFromRepo() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer existingCustomer = new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                return existingCustomer;
            }
        });
    }

    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customers VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                customer.getCustomer_id(),
                customer.getCustomer_fname(),
                customer.getCustomer_lname()
        );
    }

    public Customer getCustomerById(int custId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{custId}, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public void deleteById(int custId) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        jdbcTemplate.update(sql, custId);
    }

    public void updateCustomerData(Customer updatedCust) {
        String sql = "UPDATE customers SET customer_fname = ?, customer_lname = ? WHERE admin_id = "+updatedCust.getCustomer_id()+"";
        jdbcTemplate.update(sql,
                updatedCust.getCustomer_fname(),
                updatedCust.getCustomer_lname());
    }

    public int makeOrderByTitle(String book_title) {
        String sql = "SELECT count(*) FROM books WHERE book_title = ? AND isbookavailable = true LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, book_title);
        if (count != null && count > 0) {
            return 0;
        } else {
            sql = "SELECT count(*) FROM books WHERE book_title = ? AND isbookavailable = false LIMIT 1";
            count = jdbcTemplate.queryForObject(sql,Integer.class, book_title);
            if (count != null && count > 0) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
