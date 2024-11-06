package com.goodmit.framework.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.goodmit.framework.domain.config.properties.DataSourceProperties;
import com.goodmit.framework.domain.config.properties.HibernateProperties;
import com.goodmit.framework.domain.config.properties.JpaProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.goodmit.framework.domain")
@EntityScan(basePackages = "com.goodmit.framework.domain")
@ComponentScan(basePackages = "com.goodmit.framework.domain")
@Import(QueryDslConfig.class)
@EnableConfigurationProperties({DataSourceProperties.class, HibernateProperties.class})
public class DomainConfig {

    private final DataSourceProperties dataSourceProperties;
    private final JpaProperties jpaProperties;

    // 쓰레드풀 관련 프로퍼티를 YML에서 가져오기 위한 필드
    @Value("${threadpool.core}")
    private int threadPoolCore;

    @Value("${threadpool.max}")
    private int threadPoolMax;

    @Value("${threadpool.queue}")
    private int threadPoolQueue;

    // 생성자를 통해 DataSourceProperties와 JpaProperties 주입
    public DomainConfig(DataSourceProperties dataSourceProperties, JpaProperties jpaProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.jpaProperties = jpaProperties;
    }

    // ObjectMapper 빈 설정
    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

    // ThreadPoolTaskExecutor 빈 설정
    @Bean
    public ThreadPoolTaskExecutor eventTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("app-share-pool");
        executor.setCorePoolSize(threadPoolCore);
        executor.setMaxPoolSize(threadPoolMax);
        executor.setQueueCapacity(threadPoolQueue);
        executor.afterPropertiesSet();
        return executor;
    }

    // HikariDataSource 빈 설정
    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        return new HikariDataSource(hikariConfig);
    }

    // JPA EntityManagerFactory 설정
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("com.goodmit.framework.domain");
        factory.setDataSource(hikariDataSource());
        factory.setJpaProperties(jpaProperties.jpaProperties());
        return factory;
    }

    // TransactionManager 설정
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    // NamedParameterJdbcTemplate 설정
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}