package com.rest_project.service;

import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;

import java.util.List;


public interface TransactionService {

    /**
     * create new Transaction
     */
    void create(Transaction transaction);

    /**
     * returns a list of all transactions
     */
    List<TransactionDto> readAll();

    /**
     * returns transaction by id
     */
    TransactionDto read(int id);

    /**
     * returns a list of transactions by status
     */
    List<TransactionDto> statusFilter(String status);

    /**
     * returns a list of transactions by status and content
     */
    List<TransactionDto> complexFilter(String status);

    /**
     * check transaction exists in database
     */
    boolean existsById(int id);

    /**
     * check database is not empty
     */
    boolean existsAny();
}
