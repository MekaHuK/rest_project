package com.rest_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "test_table_228")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "transactionsIdSeq", sequenceName = "transactions_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionsIdSeq")
    private Integer id;

    @Size(min = 10, message = "content size too short: 10 characters min")
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name="init_time")
    private final Instant time = Instant.now();

    @EnumType
    @Column(name="transaction_type")
    private String enumType;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public String getEnumType() {
        return enumType;
    }

    public void setType(String type){
            this.enumType = type;
    }

}