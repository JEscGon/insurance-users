package com.dev.insurance_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.dev.insurance_users.infrastructure.repository.mapper",
    "com.dev.insurance_users.infrastructure.rest.controller.mapper",
    "com.dev.insurance_users.infrastructure.rest.controller",
    "com.dev.insurance_users.application.service",
    "com.dev.insurance_users.infrastructure.repository"
})
public class InsuranceUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceUsersApplication.class, args);
	}

}
