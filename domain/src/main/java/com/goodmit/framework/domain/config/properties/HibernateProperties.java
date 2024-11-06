package com.goodmit.framework.domain.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hibernate")
@Component
@Getter
@Setter
public class HibernateProperties {

    private String ddlAuto;
    private boolean showSql;
    private boolean formatSql;
    private String physicalNamingStrategy;
    private String implicitStrategy;

}