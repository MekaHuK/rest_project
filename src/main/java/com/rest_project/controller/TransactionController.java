package com.rest_project.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rest_project.model.Transaction;
import com.rest_project.repository.TransactionRepository;
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
        if (transaction.getContent().length() >= 10) {
            transactionService.create(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction successfully created with id: " + transaction.getId());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The transaction must be longer than 10 characters");
        }

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
//        final Transaction transaction = transactionService.read(id);
//
//        if (transaction != null && transaction.getStatus() != null) {
//            return ResponseEntity.status(HttpStatus.OK).body("This transaction exist\n" + "transaction ID is: " + transaction.getId() +
//                    "\n" + "transaction status is: " + transaction.getStatus());
//        }else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This transaction is NOT exist");
//        }



//        try {
//            final Transaction transaction = transactionService.read(id);
//            if (transaction != null) {
//                return new ResponseEntity<>(transaction, HttpStatus.OK);
//            } else {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Out of the try catch block");
//            }
//
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Out of the try catch block");
//        }
//

//        try{
//            return new ResponseEntity<>(transaction, HttpStatus.OK);
//        } catch (Exception exp){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction with that id is not exist");
//        }
//        return transaction != null
//                ? new ResponseEntity<>(transaction, HttpStatus.OK)
//                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction with that id is not exist");
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Out of the try catch block");

    }

    @GetMapping(value = "/transactions/status={status}")
    public ResponseEntity<List<Transaction>> read(@PathVariable(name = "status") String status) {
        final List<Transaction> transactions = transactionService.statusFilter(status);

        return transactions != null && !transactions.isEmpty()
                ? new ResponseEntity<>(transactions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
