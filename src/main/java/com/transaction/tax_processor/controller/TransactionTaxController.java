package com.transaction.tax_processor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.processor.dto.TransactionDTO;

@RestController
@RequestMapping("/api")
public class TransactionTaxController {

	@PostMapping(value = "/process")
	public ResponseEntity<Boolean> produce(@RequestBody TransactionDTO transaction) {
	
			return ResponseEntity.ok(Boolean.FALSE);
	}
}
