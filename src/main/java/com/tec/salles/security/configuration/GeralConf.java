package com.tec.salles.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeralConf {
    @Bean
    public BCryptPasswordEncoder encryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
