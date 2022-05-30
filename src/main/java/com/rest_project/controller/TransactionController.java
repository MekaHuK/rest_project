package com.rest_project.controller;

import com.rest_project.model.Transaction;
import com.rest_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<Transaction>> read() {
        final List<Transaction> transactions = transactionService.readAll();

        return transactions != null && !transactions.isEmpty()
                ? new ResponseEntity<>(transactions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<Transaction> read(@PathVariable(name = "id") int id) {
        final Transaction transaction = transactionService.read(id);

        return transaction != null
                ? new ResponseEntity<>(transaction, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
//    @GetMapping(value = "/transactions/status={status}")
//    public ResponseEntity<List<Transaction>> read(@PathVariable(name = "status") String status) {
//        final List<Transaction> transactions = transactionService.statusFilter(status);
//
//        return transactions != null && !transactions.isEmpty()
//                ? new ResponseEntity<>(transactions, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
