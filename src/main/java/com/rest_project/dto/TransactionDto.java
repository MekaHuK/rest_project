package com.rest_project.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Setter
@Getter
public class TransactionDto {
    Integer id;
    String content;
    String status;
    Instant time;
    String enumType;
    String greetingsMessage;
}
