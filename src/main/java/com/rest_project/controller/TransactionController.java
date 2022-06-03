package com.rest_project.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rest_project.model.Transaction;
import com.rest_project.model.Type;
import com.rest_project.repository.TransactionRepository;
import com.rest_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions")
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {

        transactionService.create(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction successfully created with id: " + transaction.getId());

//        if (transaction.getContent().length() >= 10) {
//            transactionService.create(transaction);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction successfully created with id: " + transaction.getId());
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not created: the transaction must be longer than 10 characters");
//        }
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<Transaction>> read() {
        final List<Transaction> transactions = transactionService.readAll();

        return transactions != null && !transactions.isEmpty()
                ? new ResponseEntity<>(transactions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) {
        boolean exists =transactionService.existsById(id);
        if (exists) {
            final Transaction transaction = transactionService.read(id);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction with id=" + id + " is not exists");
        }
    }

    @GetMapping(value = "/transactions/status={status}")
    public ResponseEntity<List<Transaction>> read(@PathVariable(name = "status") String status) {
        final List<Transaction> transactions = transactionService.statusFilter(status);

        return transactions != null && !transactions.isEmpty()
                ? new ResponseEntity<>(transactions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
