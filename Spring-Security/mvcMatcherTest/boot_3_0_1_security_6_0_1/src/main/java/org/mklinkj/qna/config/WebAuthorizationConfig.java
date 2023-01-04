package org.mklinkj.qna.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic();

    http.authorizeHttpRequests(
        authorize ->
            authorize
                // .shouldFilterAllDispatcherTypes(false)
                .dispatcherTypeMatchers(DispatcherType.FORWARD)
                .permitAll()
                .requestMatchers("/hello")
                .authenticated());

    http.csrf().disable();
    return http.build();
  }
}
