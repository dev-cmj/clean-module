package com.goodmit.framework.web.config;

import com.goodmit.framework.application.config.ApplicationConfig;
import com.goodmit.framework.domain.config.DomainConfig;
import kr.goodmit.framework.common.config.CommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfig.class, ApplicationConfig.class, CommonConfig.class})
public class WebAppConfig {
}
