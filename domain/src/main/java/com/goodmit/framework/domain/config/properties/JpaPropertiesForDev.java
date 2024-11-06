package com.goodmit.framework.domain.config.properties;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Profile({"develop", "local"})
public class JpaPropertiesForDev implements JpaProperties {

    private final HibernateProperties hibernateProperties;

    public JpaPropertiesForDev(HibernateProperties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    @Override
    public Properties jpaProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
        properties.setProperty("hibernate.show_sql", String.valueOf(hibernateProperties.isShowSql()));
        properties.setProperty("hibernate.format_sql", String.valueOf(hibernateProperties.isFormatSql()));
        properties.setProperty("hibernate.physical_naming_strategy", hibernateProperties.getPhysicalNamingStrategy());
        properties.setProperty("hibernate.implicit-strategy", hibernateProperties.getImplicitStrategy());
        return properties;
    }
}