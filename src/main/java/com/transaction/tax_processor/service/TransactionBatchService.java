package com.transaction.tax_processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.transaction.processor.dto.TransactionAmountDTO;
import com.transaction.processor.dto.TransactionDetailResposeDTO;
import com.transaction.processor.dto.TransactionTaxResponseDTO;
import com.transaction.processor.dto.TransactionTotalDetailDTO;
import com.transaction.tax_processor.entity.Transaction;
import com.transaction.tax_processor.repository.TransactionRepository;

@Service
public class TransactionBatchService {

	private TransactionRepository transactionRepository;

	public TransactionBatchService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;

	}

	private TransactionDetailResposeDTO transactionDTO(Transaction transaction) {
		TransactionDetailResposeDTO trx = new TransactionDetailResposeDTO();
		trx.setDescription(transaction.getDescription());
		trx.setTransactionId(transaction.getTransactionId());
		trx.setBatchTransactionId(transaction.getBatchTransactionId());

		TransactionAmountDTO trxAmmount = new TransactionAmountDTO();
		trxAmmount.setCurrency(transaction.getCurrency());
		trxAmmount.setTotal(transaction.getTotal());

		TransactionTotalDetailDTO trxDetail = new TransactionTotalDetailDTO();
		trxDetail.setShipping(transaction.getShipping());
		trxDetail.setSubtotal(transaction.getSubtotal());
		trxAmmount.setDetails(trxDetail);
		trx.setTransactionAmount(trxAmmount);
		trx.setTax(transaction.getTotalTax());
		return trx;
	}

	public TransactionDetailResposeDTO findByTransactionId(String transactionId) {
		Transaction transactions = transactionRepository.findByTransactionId(transactionId);
		if (transactions != null)
			return transactionDTO(transactions);
		return null;
	}

	public TransactionTaxResponseDTO findByBatchId(String batchID, Pageable pageable) {
		TransactionTaxResponseDTO transactionsDto = new TransactionTaxResponseDTO();
		List<Transaction> transactions = transactionRepository.findByBatchTransactionId(batchID, pageable);

		List<TransactionDetailResposeDTO> transactionsDetails = new ArrayList<>();
		for (Transaction transaction : transactions) {

			transactionsDetails.add(transactionDTO(transaction));

		}
		transactionsDto.setTransactions(transactionsDetails);
		return transactionsDto;
	}
}
