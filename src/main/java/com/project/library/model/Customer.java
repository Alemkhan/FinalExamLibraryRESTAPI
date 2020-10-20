package com.project.library.model;

public class Customer {

    private int customer_id;
    private String customer_fname;
    private String customer_lname;

    public Customer() {}

    public Customer(int id, String fname, String lname) {
        this.customer_id = id;
        this.customer_fname = fname;
        this.customer_lname = lname;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }
}
