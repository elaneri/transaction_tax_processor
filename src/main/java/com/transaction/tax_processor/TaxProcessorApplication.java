package com.transaction.tax_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.transaction")
@EnableScheduling
public class TaxProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxProcessorApplication.class, args);
	}

}
