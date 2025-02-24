package com.dev.insurance_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.dev.insurance_users")
@ComponentScan(basePackages = {
    "com.dev.insurance_users.infrastructure.repository.mapper",
    "com.dev.insurance_users.infrastructure.rest.controller.mapper"
})
public class InsuranceUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceUsersApplication.class, args);
	}

}
