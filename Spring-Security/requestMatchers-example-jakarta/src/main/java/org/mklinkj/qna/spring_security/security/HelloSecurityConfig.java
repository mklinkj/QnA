package org.mklinkj.qna.spring_security.security;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Slf4j
@Configuration
@EnableWebSecurity
public class HelloSecurityConfig {
  @Bean
  HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
    return new HandlerMappingIntrospector();
  }

  @Bean
  UserDetailsService userDetailsService() {
    val user =
        User.builder() //
            .username("user")
            .password("{noop}user")
            .authorities("USER")
            .build();

    val admin =
        User.builder()
            .username("admin")
            .password("{noop}admin")
            .authorities("USER", "ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (authz) ->
                authz
                    .requestMatchers(
                        "/webjars/**", //
                        "/resources/**",
                        "/",
                        "/index",
                        "/login",
                        "/favicon.ico")
                    /*
                    .requestMatchers(
                        antMatcher("/webjars/**"), //
                        antMatcher("/resources/**"),
                        antMatcher("/"),
                        antMatcher("/index"),
                        antMatcher("/login"),
                        antMatcher("/favicon.ico"))
                     */
                    .permitAll()
                    .requestMatchers("/admin")
                    // .requestMatchers(antMatcher("/admin"))
                    .hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated())
        .formLogin(
            conf ->
                conf.loginPage("/login") //
                    .loginProcessingUrl("/login"))
        .logout(conf -> conf.logoutSuccessUrl("/"))
        .exceptionHandling(
            conf ->
                conf.accessDeniedHandler(
                    (request, response, accessDeniedException) -> {
                      response.sendRedirect("/login?error=true");
                    }))
        .csrf(withDefaults());
    return http.build();
  }
}
