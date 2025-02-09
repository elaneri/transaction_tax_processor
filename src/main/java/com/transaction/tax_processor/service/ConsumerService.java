package com.transaction.tax_processor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.transaction.processor.dto.TransactionDTO;
import com.transaction.processor.dto.TransactionDetailDTO;

@Component
@KafkaListener(topics = "new-transactions", groupId = "processor", containerFactory = "kafkaListenerContainerFactory")
public class ConsumerService {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	private TransactionService transactionService;

	public ConsumerService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@KafkaHandler
	public void handleInMessageImplEvent(@Payload TransactionDetailDTO event) {
		transactionService.save(event);
		logger.info(String.format("Received: " + event));
	}
}
