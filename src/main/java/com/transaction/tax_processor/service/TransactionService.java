package com.transaction.tax_processor.service;

import org.springframework.stereotype.Service;

import com.transaction.processor.dto.TransactionAmountDTO;
import com.transaction.processor.dto.TransactionDTO;
import com.transaction.processor.dto.TransactionDetailDTO;
import com.transaction.processor.dto.TransactionTotalDetailDTO;
import com.transaction.tax_processor.entity.Transaction;
import com.transaction.tax_processor.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public void save(TransactionDetailDTO trxDto) {

		Transaction trx = new Transaction();
		trx.setDescription(trxDto.getDescription());
		trx.setTransactionId(trxDto.getTransactionId());

		TransactionAmountDTO trxAmmount = trxDto.getTransactionAmount();
		TransactionTotalDetailDTO trxDetail = trxAmmount.getDetail();

		trx.setCurrency(trxAmmount.getCurrency());
		trx.setTotal(trxAmmount.getTotal());
		trx.setSubtotal(trxDetail.getSubtotal());
		trx.setShipping(trxDetail.getShipping());

		trx.setTotalTax(null);
		trx.setBasedTax(null);

		transactionRepository.save(trx);
	}
}
