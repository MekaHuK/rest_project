package com.rest_project.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rest_project.dto.TransactionDto;
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
    public ResponseEntity<?> read() {
        boolean exists = transactionService.existsAny();
        if(exists){
            final List<TransactionDto> transactionsDto = transactionService.readAll();
            return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You have no any transactions");
        }
//
//
//        return transactions != null && !transactions.isEmpty()
//                ? new ResponseEntity<>(transactions, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) {
        boolean exists =transactionService.existsById(id);
        if (exists) {
            final TransactionDto transactionDto = transactionService.read(id);
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction with id=" + id + " is not exists");
        }
    }

    @GetMapping(value = "/transactions/status-search/{status}")
    public ResponseEntity<?> searchStatus(@PathVariable(name = "status") String status) {
        final List<TransactionDto> transactionsDto = transactionService.statusFilter(status);
        if(!transactionsDto.isEmpty()){
            return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No have any transactions with status = " + status);
        }
    }

    @GetMapping(value = "transactions/complex-search/{complex}")
    public ResponseEntity<?> searchComplex(@PathVariable(name = "complex") String complex) {
        final List<TransactionDto> transactionsDto = transactionService.complexFilter(complex);
        if(!transactionsDto.isEmpty()){
            return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No have any transactions with status OR content = " + complex);
        }
    }

}
