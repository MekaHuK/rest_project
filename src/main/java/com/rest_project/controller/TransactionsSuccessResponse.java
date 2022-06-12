package com.rest_project.controller;

import com.rest_project.dto.TransactionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransactionsSuccessResponse {
    private List<TransactionDto> transactions = new ArrayList<>();

}
