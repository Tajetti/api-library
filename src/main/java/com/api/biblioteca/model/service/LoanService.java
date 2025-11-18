package com.api.biblioteca.model.service;

import com.api.biblioteca.model.entity.Loan;
import com.api.biblioteca.model.entity.Book;
import com.api.biblioteca.model.entity.Client;
import com.api.biblioteca.model.repository.LoanRepository;
import com.api.biblioteca.model.repository.BookRepository;
import com.api.biblioteca.model.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    // =====================
    // Criar empréstimo
    // =====================
    public String createLoan(int clientId, int bookId) {

        Client client = clientRepository.findClientById((long) clientId);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }

        Book book = bookRepository.findBookById((long) bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Loan loan = new Loan();
        loan.setClient(client);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7)); // 7 dias padrão
        loan.setFine(0.0);
        loan.setReturnDate(null);

        loanRepository.save(loan);
        return "Loan created successfully!";
    }


    // =====================
    // Listar empréstimos
    // =====================
    public List<Loan> findAllLoans() {
        return (List<Loan>) loanRepository.findAll();
    }


    // =====================
    // Buscar empréstimo por ID
    // =====================
    public Loan findLoanById(int id) {
        return loanRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));
    }


    // =====================
    // Atualizar empréstimo
    // =====================
    public Loan updateLoan(int id, Loan newLoanData) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));

        // Atualizar data de devolução
        if (newLoanData.getReturnDate() != null) {
            loan.setReturnDate(LocalDate.now());

            // calcular multa se atrasado
            if (loan.getReturnDate().isAfter(loan.getDueDate())) {
                long diff = loan.getReturnDate().toEpochDay() - loan.getDueDate().toEpochDay();
                loan.setFine(diff * 2.0); // multa de 2,00 por dia
            }
        }

        // Atualizar data de vencimento (caso queria renovar)
        if (newLoanData.getDueDate() != null) {
            loan.setDueDate(newLoanData.getDueDate());
        }

        // Atualizar multa manualmente
        if (newLoanData.getFine() != null) {
            loan.setFine(newLoanData.getFine());
        }

        loanRepository.save(loan);
        return loan;
    }


    // =====================
    // Deletar empréstimo
    // =====================
    public String deleteLoan(int id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));

        loanRepository.delete(loan);
        return "Loan deleted successfully!";
    }
}
