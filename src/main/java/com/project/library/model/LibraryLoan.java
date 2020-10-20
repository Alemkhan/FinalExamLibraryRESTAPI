package com.project.library.model;

import java.time.LocalDate;

public class LibraryLoan {

    private int loan_id;
    private int admin_id;
    private int book_id;
    private int customer_id;
    private int cell_id;
    private LocalDate issuing_date;
    private LocalDate issue_outcome;
    private LocalDate return_date;

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCell_id() {
        return cell_id;
    }

    public void setCell_id(int cell_id) {
        this.cell_id = cell_id;
    }

    public LocalDate getIssuing_date() {
        return issuing_date;
    }

    public void setIssuing_date(LocalDate issuing_date) {
        this.issuing_date = issuing_date;
    }

    public LocalDate getIssue_outcome() {
        return issue_outcome;
    }

    public void setIssue_outcome(LocalDate issue_outcome) {
        this.issue_outcome = issue_outcome;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }
}
