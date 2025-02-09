package com.transaction.processor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.transaction.processor.dto.TransactionDto;

@Component
@KafkaListener(topics = "new-transactions",  groupId = "processor", containerFactory = "kafkaListenerContainerFactory")
public class ConsumerService {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaHandler
	public void handleInMessageImplEvent(@Payload TransactionDto event) {
		logger.info(String.format("Received: " + event));
	}
}
