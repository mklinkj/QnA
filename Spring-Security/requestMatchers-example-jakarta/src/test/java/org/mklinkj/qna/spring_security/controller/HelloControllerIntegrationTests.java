package org.mklinkj.qna.spring_security.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Slf4j
class HelloControllerIntegrationTests {
  @Tag("integration")
  @Test
  void testHttpStatus200() throws IOException, InterruptedException {
    // https://akhikhl.github.io/gretty-doc/Integration-tests-support.html
    // ✨ Your test code can access these properties via System.getProperty function.
    //    This allows to parametrize test code with Gretty-specific parameters.
    // ✨ gretty.httpBaseURI: "http://${host}:${httpPort}${contextPath}",
    //     when HTTP protocol is enabled; null otherwise.
    String httpBaseURI = System.getProperty("gretty.httpBaseURI");
    LOGGER.info("### gretty httpBaseURI: {}", httpBaseURI);
    HttpClient client =
        HttpClient.newBuilder() //
            .build();
    HttpRequest request =
        HttpRequest.newBuilder() //
            .uri(URI.create(httpBaseURI))
            .timeout(Duration.ofSeconds(5)) // 타임아웃 설정을 꼭 해야한다. 제대로 연결이 안될 때, 무한정 대기함.
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }
}
