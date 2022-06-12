package com.rest_project.utils;

import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    /**
     * from entity to dto
     */
    public static TransactionDto mapToTransactionDTO(@NotNull Transaction transaction){
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setContent(transaction.getContent());
        dto.setStatus(transaction.getStatus());
        dto.setTime(transaction.getTime());
        dto.setEnumType(transaction.getEnumType());
        return dto;
    }

    /**
     * from dto to entity
     */
    public static Transaction mapToTransaction(TransactionDto dto){
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setContent(dto.getContent());
        transaction.setStatus(dto.getStatus());
        transaction.setTime(dto.getTime());
        transaction.setEnumType(dto.getEnumType());
        return transaction;
    }
}
