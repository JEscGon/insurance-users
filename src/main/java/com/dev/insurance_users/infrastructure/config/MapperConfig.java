package com.dev.insurance_users.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "com.dev.insurance_users.infrastructure.repository.mapper",
    "com.dev.insurance_users.infrastructure.rest.controller.mapper"
})
public class MapperConfig {
}