package com.goodmit.framework.web.config;

import com.goodmit.framework.domain.config.DomainConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfig.class})
public class WebAppConfig {

}
