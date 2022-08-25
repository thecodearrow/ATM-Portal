package com.pvbank.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.pvbank.portal")
@EntityScan(basePackages = "com.pvbank.portal")
@EnableTransactionManagement
public class PvBankPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PvBankPortalApplication.class, args);
	}



}
