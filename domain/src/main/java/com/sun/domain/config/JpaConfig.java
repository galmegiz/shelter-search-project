package com.sun.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@Configuration
@EntityScan(basePackages = "com.sun")
@EnableJpaRepositories(basePackages = {"com.sun"})
public class JpaConfig {
}
