package com.rest_project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
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
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction successfully created with id: " + transaction.getId());

    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<?> read() throws JsonProcessingException {
        boolean exists = transactionService.existsAny();
        if(exists){
            final List<String> transactionsJson = transactionService.readAll();
            return new ResponseEntity<>(transactionsJson, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You have no any transactions");
        }
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) throws JsonProcessingException {
        boolean exists =transactionService.existsById(id);
        if (exists) {
            final String transactionJson = transactionService.read(id);

            return new ResponseEntity<>(transactionJson, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction with id=" + id + " is not exists");
        }
    }

    @GetMapping(value = "/transactions/status-search/{status}")
    public ResponseEntity<?> searchStatus(@PathVariable(name = "status") String status) throws JsonProcessingException {
        final List<String> transactionsJson = transactionService.statusFilter(status);
        if(!transactionsJson.isEmpty()){
            return new ResponseEntity<>(transactionsJson, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No have any transactions with status = " + status);
        }
    }

    @GetMapping(value = "transactions/complex-search/{complex}")
    public ResponseEntity<?> searchComplex(@PathVariable(name = "complex") String complex) throws JsonProcessingException {
        final List<String> transactionsJson = transactionService.complexFilter(complex);
        if(!transactionsJson.isEmpty()){
            return new ResponseEntity<>(transactionsJson, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No have any transactions with status OR content = " + complex);
        }
    }
}
