package com.goodmit.framework.domain.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "datasource")
@Component
@Getter
@Setter
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private Hikari hikari;

    @Getter
    @Setter
    public static class Hikari {
        private long maxLifetime;
        private long idleTimeout;
        private long connectionTimeout;
    }

}