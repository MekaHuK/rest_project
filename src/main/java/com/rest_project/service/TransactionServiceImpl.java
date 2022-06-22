package com.rest_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rest_project.controller.TransactionsSuccessResponse;
import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import com.rest_project.repository.TransactionRepository;
import com.rest_project.utils.TransactionMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public JSONObject createTransaction(TransactionDto transactionDto){
        Transaction transaction = TransactionMapper.INSTANCE.toTransaction(transactionDto);
        transactionRepository.save(transaction);
        JSONObject transactionJson = new JSONObject();
        transactionJson.put("id",transaction.getId());
        return transactionJson;
    }

    @Override
    public TransactionsSuccessResponse readAllTransactions() throws JsonProcessingException {
        TransactionsSuccessResponse response = new TransactionsSuccessResponse();
            for(Transaction transaction : transactionRepository.findAll()){
                response.getTransactions().add(TransactionMapper.INSTANCE.toTransactionDto(transaction));
            }
        return response;
    }

    @Override
    public TransactionsSuccessResponse searchByParams(HashMap<String, String> requestParams){
        TransactionsSuccessResponse response = new TransactionsSuccessResponse();
        List<Transaction> transactions = new ArrayList<>();

        if(requestParams.containsKey("content") && requestParams.containsKey("status")){
            transactions.addAll(transactionRepository.findByContentAndStatus(requestParams.get("status"), requestParams.get("content")));

        } else if(requestParams.containsKey("status")){
            transactions.addAll(transactionRepository.findAllByStatus(requestParams.get("status")));

        } else if(requestParams.containsKey("content")){
            transactions.addAll(transactionRepository.findAllByContent(requestParams.get("content")));
        }

        for(Transaction transaction : transactions){
            response.getTransactions().add(TransactionMapper.INSTANCE.toTransactionDto(transaction));
        }
        return response;
    }

    @Override
    public TransactionDto readOneTransaction(int id) throws JsonProcessingException {
        if(!transactionRepository.existsById(id)){
            throw new ResourceNotFoundException("Transaction with requested id is not exist");
        }
        return TransactionMapper.INSTANCE.toTransactionDto(transactionRepository.getReferenceById(id));
    }

    @Override
    public boolean existsById(int id){
        return transactionRepository.existsById(id);
    }

}
