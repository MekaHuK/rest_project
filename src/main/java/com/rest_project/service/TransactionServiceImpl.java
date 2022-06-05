package com.rest_project.service;

import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import com.rest_project.model.Type;
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
    public List<TransactionDto> readAll(){
//        return new ArrayList<>(TRANSACTION_MAP.values());
        List<TransactionDto> result = transactionRepository.findAll().stream()
                .map(MappingUtils::mapToTransactionDTO)
                .collect(Collectors.toList());
        helloUsers(result);
        return result;
    }

    @Override
    public TransactionDto read(int id){
//        return TRANSACTION_MAP.get(id);
        TransactionDto result = MappingUtils.mapToTransactionDTO(transactionRepository.getOne(id));
        helloUser(result);
        return result;
    }

    @Override
    public List<TransactionDto> statusFilter(String status){
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
        return result;
    }

    @Override
    public List<TransactionDto> complexFilter(String string){
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
