package com.transaction.tax_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.tax_processor.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
