package com.api.biblioteca.controller;

import com.api.biblioteca.model.entity.Loan;
import com.api.biblioteca.model.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public String createLoan(@RequestParam int clientId, @RequestParam int bookId) {
        return loanService.createLoan(clientId, bookId);
    }

    @GetMapping("/all")
    public List<Loan> findAllLoans() {
        return loanService.findAllLoans();
    }

    @GetMapping("/{id}")
    public Loan findLoanById(@PathVariable int id) {
        return loanService.findLoanById(id);
    }

    @PutMapping("/update/{id}")
    public Loan updateLoan(@PathVariable int id, @RequestBody Loan loan) {
        return loanService.updateLoan(id, loan);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLoan(@PathVariable int id) {
        return loanService.deleteLoan(id);
    }
}
