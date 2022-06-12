package com.rest_project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.rest_project.dto.TransactionDto;
import com.rest_project.service.TransactionService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public JSONObject createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.createTransaction(transactionDto);
    }

    @GetMapping
    public TransactionsSuccessResponse getAllTransactions() throws JsonProcessingException {
        return transactionService.readAllTransactions();
    }

    @GetMapping(value = "/{id}")
    public TransactionDto getOneById(@PathVariable(name = "id") int id) throws JsonProcessingException {
        return transactionService.readOneTransaction(id);
    }

    @RequestMapping (value = "/search", method = RequestMethod.POST)
    public TransactionsSuccessResponse getTransactionsByParams(@RequestParam HashMap<String, String> requestParams) {
        return  transactionService.searchByParams(requestParams);
    }
}
