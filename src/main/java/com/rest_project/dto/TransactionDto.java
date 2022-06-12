package com.rest_project.dto;

import com.rest_project.model.EnumType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.Instant;

@Setter
@Getter

public class TransactionDto {
    private Integer id;
    @Size(min = 10, message = "content size is too short: 10 characters min")
    private String content;
    private String status;
    private Instant time = Instant.now();
    @EnumType
    private String enumType;
}
