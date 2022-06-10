package com.rest_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;

import java.util.List;


public interface TransactionService {

    /**
     * create new Transaction
     */
    int create(TransactionDto transactionDto);

    /**
     * returns a list of all transactions
     */
    List<String> readAll() throws JsonProcessingException;

    /**
     * returns transaction by id
     */
    String read(int id) throws JsonProcessingException;

    /**
     * returns a list of transactions by status
     */
    List<String> statusFilter(String status) throws JsonProcessingException;

    /**
     * returns a list of transactions by status and content
     */
    List<String> complexFilter(String status) throws JsonProcessingException;

    /**
     * check transaction exists in database
     */
    boolean existsById(int id);

    /**
     * check database is not empty
     */
    boolean existsAny();
}
