package com.velo.microserviceusermanagement.repository;

import com.velo.microserviceusermanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long userId);

    List<Transaction> findAllByCourseId(Long id); // should be courseID ??
}
