package com.transaction.tax_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.tax_processor.entity.TransactionTax;

public interface TransactionTaxRepository extends JpaRepository<TransactionTax, Long> {
	TransactionTax findByCurrency(String currency);
}
