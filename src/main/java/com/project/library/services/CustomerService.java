package com.project.library.services;

import com.project.library.model.Customer;
import com.project.library.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LoanService loanService;

    public CustomerService(CustomerRepository customerRepository, LoanService loanService) {
        this.customerRepository = customerRepository;
        this.loanService = loanService;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomersFromRepo();
    }


    public void addCustomer(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    public Customer getCustomerById(int custId) {
        return customerRepository.getCustomerById(custId);
    }

    public void deleteById(int custId) {
        customerRepository.deleteById(custId);
    }

    public void callCustomerUpdater(Customer updatedCust) {
        customerRepository.updateCustomerData(updatedCust);
    }

    public int makeOrderByTitle(String book_title, int customer_id) {
        if (customerRepository.makeOrderByTitle(book_title) == 0) {
            return loanService.preparingOrder(book_title, customer_id);
        } else {
            return customerRepository.makeOrderByTitle(book_title);
        }
    }

    public void returnBook(int customer_id, String book_title) {
        loanService.returningBook(customer_id, book_title);
    }
}
