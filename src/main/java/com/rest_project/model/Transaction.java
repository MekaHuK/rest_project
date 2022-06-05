package com.rest_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "test_table_228")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Size(min = 10, message = "content size too short: 10 characters min")
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name="init_time")
    private Instant time = Instant.now();

    @EnumType
    @Column(name="transaction_type")
    private String enumType;

    public void setType(String type){
            this.enumType = type;
    }

}