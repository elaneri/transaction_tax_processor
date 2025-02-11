package com.transaction.tax_processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.transaction.processor.dto.TaxRuleDTO;
import com.transaction.tax_processor.entity.TransactionTax;
import com.transaction.tax_processor.repository.TransactionTaxRepository;

@Service
public class TaxService {
	private TransactionTaxRepository transactionTaxRepository;

	public TaxService(TransactionTaxRepository transactionTaxRepository) {
		this.transactionTaxRepository = transactionTaxRepository;

	}

	public void save(TaxRuleDTO taxRuleDTO) {
		TransactionTax tax = transactionTaxRepository.findByCurrency(taxRuleDTO.getCurrency());
		if (tax == null) {
			tax = new TransactionTax();
			tax.setCurrency(taxRuleDTO.getCurrency());
		}
		tax.setTax(taxRuleDTO.getTax());

		transactionTaxRepository.save(tax);
		taxRuleDTO.setId(tax.getId());
	}

	public TaxRuleDTO findByCurrency(String currency) {
		TaxRuleDTO taxDto = new TaxRuleDTO();
		TransactionTax tax = transactionTaxRepository.findByCurrency(currency);
		if (tax != null) {
			taxDto.setCurrency(tax.getCurrency());
			taxDto.setTax(tax.getTax());
			taxDto.setId(tax.getId());
			return taxDto;
		}
		return null;
	}

	public List<TaxRuleDTO> getTaxes() {
		List<TaxRuleDTO> taxesDto = new ArrayList<>();
		List<TransactionTax> taxes = transactionTaxRepository.findAll();
		for (TransactionTax transactionTax : taxes) {
			TaxRuleDTO dto = new TaxRuleDTO();
			dto.setCurrency(transactionTax.getCurrency());
			dto.setTax(transactionTax.getTax());
			dto.setId(transactionTax.getId());
			taxesDto.add(dto);
		}
		return taxesDto;
	}
}
