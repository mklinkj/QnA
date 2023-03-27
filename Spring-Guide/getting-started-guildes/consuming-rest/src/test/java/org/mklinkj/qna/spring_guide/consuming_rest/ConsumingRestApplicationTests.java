package org.mklinkj.qna.spring_guide.consuming_rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ConsumingRestApplicationTests {

  @Autowired private RestTemplate restTemplate;

  @Autowired private ApplicationRunner runner;

  @Test
  void contextLoads() {
    assertThat(restTemplate).isNotNull();
    assertThat(runner).isNotNull();
  }
}
