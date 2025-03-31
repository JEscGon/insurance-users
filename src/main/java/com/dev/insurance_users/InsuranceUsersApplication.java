package com.dev.insurance_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.dev.insurance_users"})
public class InsuranceUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceUsersApplication.class, args);
	}
}
