package com.rest_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import com.rest_project.utils.MappingUtils;
import com.rest_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

//    //Transaction storage
//    private static final Map<Integer, Transaction> TRANSACTION_MAP = new HashMap<>();

    @Autowired
    private TransactionRepository transactionRepository;

    private final String greetingsMessage = "Hello userName!";

    public void helloUsers(List<TransactionDto> list){
        list.forEach(transactionDto -> transactionDto.setGreetingsMessage(greetingsMessage));
    }

    public void helloUser(TransactionDto transactionDto){
        transactionDto.setGreetingsMessage(greetingsMessage);
    }
//    //Variable for transaction id generation
//    private static final AtomicInteger TRANSACTION_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Transaction transaction){
//        final int transactionId = TRANSACTION_ID_HOLDER.incrementAndGet();
//        transaction.setId(transactionId);
//        TRANSACTION_MAP.put(transactionId, transaction);
        transactionRepository.save(transaction);
    }

    @Override
    public List<String> readAll() throws JsonProcessingException {
//        return new ArrayList<>(TRANSACTION_MAP.values());
        List<TransactionDto> result = transactionRepository.findAll().stream()
                .map(MappingUtils::mapToTransactionDTO)
                .collect(Collectors.toList());
        helloUsers(result);

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
//        return TRANSACTION_MAP.get(id);
        TransactionDto result = MappingUtils.mapToTransactionDTO(transactionRepository.getOne(id));
        helloUser(result);
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
        helloUsers(result);

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
        helloUsers(result);

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
