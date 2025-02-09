package com.transaction.tax_processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.transaction")
@EnableScheduling
public class TaxProcessorApplication {

	private static final Logger logger = LoggerFactory.getLogger(TaxProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaxProcessorApplication.class, args);
	}

}
