package com.transaction.tax_processor.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.transaction.processor.dto.TransactionAmountDTO;
import com.transaction.processor.dto.TransactionDetailDTO;
import com.transaction.processor.dto.TransactionTotalDetailDTO;
import com.transaction.tax_processor.entity.Transaction;
import com.transaction.tax_processor.entity.TransactionTax;
import com.transaction.tax_processor.repository.TransactionRepository;
import com.transaction.tax_processor.repository.TransactionTaxRepository;

@Service
public class TransactionService {
	private TransactionRepository transactionRepository;
	private TransactionTaxRepository transactionTaxRepository;

	public TransactionService(TransactionRepository transactionRepository,
			TransactionTaxRepository transactionTaxRepository) {
		this.transactionRepository = transactionRepository;
		this.transactionTaxRepository = transactionTaxRepository;

	}
	public void save(TransactionDetailDTO trxDto)  {

		Transaction trx = new Transaction();
		trx.setDescription(trxDto.getDescription());
		trx.setTransactionId(trxDto.getTransactionId());
		trx.setBatchTransactionId(trxDto.getBatchTransactionId());
		TransactionAmountDTO trxAmmount = trxDto.getTransactionAmount();
		TransactionTotalDetailDTO trxDetail = trxAmmount.getDetails();

		trx.setCurrency(trxAmmount.getCurrency());
		trx.setTotal(trxAmmount.getTotal());
		trx.setSubtotal(trxDetail.getSubtotal());
		trx.setShipping(trxDetail.getShipping());
		TransactionTax tax = transactionTaxRepository.findByCurrency(trxAmmount.getCurrency());
		if (tax == null) {
			trx.setTotalTax(BigDecimal.ZERO);
			trx.setBasedTax(BigDecimal.ZERO);
		} else {
			trx.setTotalTax(trxAmmount.getTotal().multiply(BigDecimal.ONE.divide(tax.getTax())));
			trx.setBasedTax(tax.getTax());
		}
		transactionRepository.save(trx);
	}
}
