package com.project.library.controllers;

import com.project.library.model.Customer;
import com.project.library.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path="/customers/{customerId}")
    public Customer getAdminById(@PathVariable int customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(path = "/customers")
    public String registerCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "Customer has been successfully added";
    }

    @PutMapping(path = "/customers/update/{custId}")
    public String updateCustomerData(@PathVariable int custID,
                                     @RequestParam(name="customer_fname") String fname,
                                     @RequestParam(name="customer_lname") String lname) {
        Customer updatedCust = null;
        try {
            updatedCust = customerService.getCustomerById(custID);
        } catch (Exception e) {
            return e.getMessage();
        }
        updatedCust.setCustomer_fname(fname);
        updatedCust.setCustomer_lname(lname);
        customerService.callCustomerUpdater(updatedCust);
        return "The data of customer with ID " + updatedCust.getCustomer_id() +" has been updated";
    }

    @DeleteMapping(path = "customers/update/{custId}")
    public String deleteAdmin(@PathVariable int custId) {
        try {
            customerService.deleteById(custId);
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "Admin with ID " + custId + "deleted";
    }

    @PostMapping(path = "customers/makeOrder/{customer_id}/{book_title}")
    public String makeOrderByTitle(@PathVariable int customer_id, @PathVariable String book_title) {
        int result = customerService.makeOrderByTitle(book_title, customer_id);
        if(result == 0) {
            return "The book is reserved";
        } else if (result == 1) {
            return "The book is not available";
        } else if (result == 2){
            return "There is no such book";
        } else if (result == 3){
            return "No available cells";
        } else {
            return "Something went wrong";
        }
    }

    @PostMapping(path = "customers/returnBook/{customer_id}/{book_title}")
    public String returnBook(@PathVariable int customer_id, @PathVariable String book_title) {
        customerService.returnBook(customer_id, book_title);
        return "Thanks for returning";
    }


}
