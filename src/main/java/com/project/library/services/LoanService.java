package com.project.library.services;

import com.project.library.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final CellService cellService;

    public LoanService(LoanRepository loanRepository, CellService cellService) {
        this.loanRepository = loanRepository;
        this.cellService = cellService;
    }

    public int preparingOrder(String book_title, int customer_id) {
        if (cellService.selectAvailableCells()){
            return loanRepository.createLoan(book_title, customer_id);
        } else {
            return 3;
        }
    }

    public void returningBook(int customer_id, String book_title) {
        loanRepository.updateLoanData(customer_id, book_title);
    }
}
