package org.mklinkj.qna.spring_guide.consuming_rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class ConsumingRestApplication {
  public record Quote(String type, Value value) {}

  public record Value(int id, String quote) {}

  @Bean
  ApplicationRunner run(RestTemplate restTemplate) {
    return args -> {
      Quote quote = restTemplate.getForObject("http://lvm.test-linux:18080/api/random", Quote.class);
      LOGGER.info("quote: {}", quote);
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumingRestApplication.class, args);
  }

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
}
