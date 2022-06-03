package com.rest_project.service;

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
    List<Transaction> readAll();

    /**
     * returns transaction by id
     */
    Transaction read(int id);

    /**
     * returns a list of transactions by status
     */
    List<Transaction> statusFilter(String status);

    /**
     * check transaction exists in database
     */
    boolean existsById(int id);

}
