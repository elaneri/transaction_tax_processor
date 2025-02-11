package com.transaction.tax_processor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.processor.dto.TaxRuleDTO;
import com.transaction.tax_processor.service.TaxService;

@RestController
@RequestMapping("/api")
public class TaxController {
	private TaxService taxService;

	public TaxController(TaxService taxService) {
		this.taxService = taxService;
	}

	@PostMapping("/tax")
	public ResponseEntity<TaxRuleDTO> addTaxRule(@RequestBody TaxRuleDTO taxRuleDTO) {
		taxService.save(taxRuleDTO);
		return ResponseEntity.ok(taxRuleDTO);
	}

	@GetMapping("/tax/{currency}")
	public ResponseEntity<TaxRuleDTO> getTax(@PathVariable String currency) {
		TaxRuleDTO tax = taxService.findByCurrency(currency);
		if (tax != null)
			return ResponseEntity.ok(tax);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	}

	@GetMapping("/taxes")
	public ResponseEntity<List<TaxRuleDTO>> getTaxes() {
		List<TaxRuleDTO> tax = taxService.getTaxes();
		return ResponseEntity.ok(tax);
	}

}
