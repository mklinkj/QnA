package org.mklinkj.qna.spring_security.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Tag("integration")
@Slf4j
class HelloControllerIntegrationTests {
  @Test
  void testHttpStatus200() throws Exception {
    // https://akhikhl.github.io/gretty-doc/Integration-tests-support.html
    // ✨ Your test code can access these properties via System.getProperty function.
    //    This allows to parametrize test code with Gretty-specific parameters.
    String randomPort = System.getProperty("gretty.httpPort");
    LOGGER.info("### gretty random port: {}", randomPort);
    HttpClient client =
        HttpClient.newBuilder() //
            .build();
    HttpRequest request =
        HttpRequest.newBuilder() //
            .uri(URI.create("http://localhost:%s".formatted(randomPort)))
            .timeout(Duration.ofSeconds(5))
            // 타임아웃 설정은 필수입니다. 연결이 제대로 되지 않으면 무한 대기 상태가 됩니다.
            // Setting a timeout is essential. In case of improper connection,
            // it results in indefinite waiting.
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }
}
