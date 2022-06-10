package com.rest_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rest_project.controller.controlleradvice.Violation;
import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import com.rest_project.utils.MappingUtils;
import com.rest_project.repository.TransactionRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public JSONObject create(TransactionDto transactionDto){
        Transaction transaction = MappingUtils.mapToTransaction(transactionDto);
        transactionRepository.save(transaction);
        JSONObject transactionJson = new JSONObject();
        transactionJson.put("id",transaction.getId());
        return transactionJson;
    }

    @Override
    public List<String> readAll() throws JsonProcessingException {
        List<TransactionDto> result = transactionRepository.findAll().stream()
                .map(MappingUtils::mapToTransactionDTO)
                .collect(Collectors.toList());

        List<String> transactionsJson = new ArrayList<>();
        for (TransactionDto dto : result){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            transactionsJson.add(objectMapper.writeValueAsString(dto));
        }
        return transactionsJson;
    }

    @Override
    public String read(int id) throws JsonProcessingException {

        TransactionDto result = MappingUtils.mapToTransactionDTO(transactionRepository.getOne(id));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(result);
    }

    @Override
    public List<String> statusFilter(String status) throws JsonProcessingException {
        List<Transaction> dtoList = new ArrayList<>();
        List<Transaction> allTransactions = transactionRepository.findAll();
        for(Transaction transaction : allTransactions){
            if(transaction.getStatus().equals(status)){
                dtoList.add(transaction);
            }
        }
        List<TransactionDto> result = dtoList.stream()
                .map(MappingUtils::mapToTransactionDTO)
                .collect(Collectors.toList());

        List<String> transactionsJson = new ArrayList<>();
        for (TransactionDto dto : result){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            transactionsJson.add(objectMapper.writeValueAsString(dto));
        }
        return transactionsJson;
    }

    @Override
    public List<String> complexFilter(String string) throws JsonProcessingException {
        List<Transaction> dtoList = new ArrayList<>();
        List<Transaction> allTransactions = transactionRepository.findAll();
        for(Transaction transaction : allTransactions){
            if(transaction.getStatus().equals(string) || transaction.getContent().equals(string)){
                dtoList.add(transaction);
            }
        }
        List<TransactionDto> result = dtoList.stream()
                .map(MappingUtils::mapToTransactionDTO)
                .collect(Collectors.toList());

        List<String> transactionsJson = new ArrayList<>();
        for (TransactionDto dto : result){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            transactionsJson.add(objectMapper.writeValueAsString(dto));
        }
        return transactionsJson;
    }

    @Override
    public boolean existsById(int id){
        return transactionRepository.existsById(id);
    }

    @Override
    public boolean existsAny(){
        return transactionRepository.count() > 0;
    }

}
