package com.rest_project.repository;

import com.rest_project.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    @Query("SELECT t FROM Transaction t WHERE t.status = ?1 AND t.content = ?2")
    List<Transaction> findByContentAndStatus(String status, String content);

    @Query("SELECT t FROM Transaction t WHERE t.status = ?1")
    List<Transaction> findAllByStatus(String status);

    @Query("SELECT t FROM Transaction t WHERE t.content = ?1")
    List<Transaction> findAllByContent(String content);
}
