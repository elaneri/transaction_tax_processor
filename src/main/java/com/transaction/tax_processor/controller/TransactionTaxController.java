package com.transaction.tax_processor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.processor.dto.TransactionTaxResponseDTO;
import com.transaction.tax_processor.service.TransactionBatchService;

@RestController
@RequestMapping("/api")
public class TransactionTaxController {

	private TransactionBatchService transactionBatchService;

	public TransactionTaxController(TransactionBatchService transactionBatchService) {
		this.transactionBatchService = transactionBatchService;
	}

	@GetMapping("/transaction/{batchTransactionId}")
	public ResponseEntity<TransactionTaxResponseDTO> produce(@PathVariable("batchTransactionId") String batchTransactionId) {
		TransactionTaxResponseDTO transaction = transactionBatchService.findByBatchId(batchTransactionId);
		return ResponseEntity.ok(transaction);
	}
}
