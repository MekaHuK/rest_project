package com.rest_project.service;

import com.rest_project.model.Transaction;
import com.rest_project.model.Type;
import com.rest_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

//    //Transaction storage
//    private static final Map<Integer, Transaction> TRANSACTION_MAP = new HashMap<>();

    @Autowired
    private TransactionRepository transactionRepository;

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
    public List<Transaction> readAll(){
//        return new ArrayList<>(TRANSACTION_MAP.values());
        return transactionRepository.findAll();
    }

    @Override
    public Transaction read(int id){
//        return TRANSACTION_MAP.get(id);
        return transactionRepository.getOne(id);
    }

    @Override
    public List<Transaction> statusFilter(String status){
        List<Transaction> result = new ArrayList<>();
        List<Transaction> allTransactions = transactionRepository.findAll();
        for(Transaction transaction : allTransactions){
            if(transaction.getStatus().equals(status)){
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public List<Transaction> complexFilter(String string){
        List<Transaction> result = new ArrayList<>();
        List<Transaction> allTransactions = transactionRepository.findAll();
        for(Transaction transaction : allTransactions){
            if(transaction.getStatus().equals(string) || transaction.getContent().equals(string)){
                result.add(transaction);
            }
        }
        return result;
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
