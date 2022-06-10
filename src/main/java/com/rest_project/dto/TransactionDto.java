package com.rest_project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.Instant;

@Setter
@Getter

public class TransactionDto {
    private Integer id;
    private String content;
    private String status;
    private Instant time = Instant.now();;
    private String enumType;
}
