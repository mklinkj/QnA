package org.mklinkj.qna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic();

    http.authorizeHttpRequests() //
        .requestMatchers("/hello")
        .authenticated();
    http.csrf().disable();
    return http.build();
  }
}
