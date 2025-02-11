package com.transaction.tax_processor.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.processor.dto.TransactionDetailResposeDTO;
import com.transaction.processor.dto.TransactionTaxResponseDTO;
import com.transaction.tax_processor.service.TransactionBatchService;

@RestController
@RequestMapping("/api")
public class TransactionTaxController {

	private TransactionBatchService transactionBatchService;

	public TransactionTaxController(TransactionBatchService transactionBatchService) {
		this.transactionBatchService = transactionBatchService;

	}

	@GetMapping("/batch-transactions/{batchTransactionId}")
	public ResponseEntity<TransactionTaxResponseDTO> produce(@PathVariable String batchTransactionId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending) {
		Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		TransactionTaxResponseDTO transaction = transactionBatchService.findByBatchId(batchTransactionId,
				PageRequest.of(page, size, sort));
		return ResponseEntity.ok(transaction);
	}

	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<TransactionDetailResposeDTO> produce(@PathVariable String transactionId) {

		TransactionDetailResposeDTO transaction = transactionBatchService.findByTransactionId(transactionId);
		if (transaction != null)
			return ResponseEntity.ok(transaction);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
