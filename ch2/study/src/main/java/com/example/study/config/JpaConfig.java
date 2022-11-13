package com.example.study.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    
    // 누가 수정했는지 알게 됨

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("yubin"); // 스프링 시큐리티로 인증 기능을 붙이게 될 때 수정하자
    }
}





