package com.transaction.tax_processor.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.transaction.processor.dto.TransactionDetailDTO;

@Component
@KafkaListener(topics = "${kafka.topic}", groupId = "processor", containerFactory = "kafkaListenerContainerFactory")
public class ConsumerService {
	private TransactionService transactionService;

	public ConsumerService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@KafkaHandler
	public void handleInMessageImplEvent(@Payload TransactionDetailDTO event) {
		transactionService.save(event);
	}
}