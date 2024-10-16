package com.application.bsbookservice.config;

import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean
    public Logger getLogger() {
        return Logger.getLogger("com.application.bsbookservice");
    }
}
