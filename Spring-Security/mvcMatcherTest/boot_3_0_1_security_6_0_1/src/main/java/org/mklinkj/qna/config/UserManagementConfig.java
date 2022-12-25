package org.mklinkj.qna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {
  @Bean
  UserDetailsService userDetailsService() {
    var manager = new InMemoryUserDetailsManager();

    manager.createUser(
        User.withUsername("mklinkj") //
            .password("{noop}12345")
            .roles("ADMIN")
            .build());

    manager.createUser(
        User.withUsername("struts") //
            .password("{noop}54321")
            .roles("MANAGER")
            .build());
    return manager;
  }
}
