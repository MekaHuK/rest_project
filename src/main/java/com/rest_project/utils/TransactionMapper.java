package com.rest_project.utils;

import com.rest_project.dto.TransactionDto;
import com.rest_project.model.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(TransactionDto transactionDto);

    @InheritInverseConfiguration
    TransactionDto toTransactionDto(Transaction transaction);
}
