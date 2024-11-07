package com.goodmit.framework.application.config;

import com.goodmit.framework.domain.config.DomainConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.goodmit.framework.application")
public class ApplicationConfig {
}
