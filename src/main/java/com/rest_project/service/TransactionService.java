package com.rest_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rest_project.controller.TransactionsSuccessResponse;
import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import net.minidev.json.JSONObject;
import java.util.HashMap;
import java.util.List;


public interface TransactionService {

    /**
     * create new Transaction
     */
    JSONObject createTransaction(TransactionDto transactionDto);

    /**
     * returns a list of all transactions
     */
    TransactionsSuccessResponse readAllTransactions() throws JsonProcessingException;

    /**
     * returns transaction by id
     */
    TransactionDto readOneTransaction(int id) throws JsonProcessingException;

    /**
     * returns a list of all transactions
     */
    TransactionsSuccessResponse searchByParams(HashMap<String, String> requestParams);

    public boolean existsById(int id);
}
