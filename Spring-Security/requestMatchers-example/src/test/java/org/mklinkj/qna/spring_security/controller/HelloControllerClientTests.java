package org.mklinkj.qna.spring_security.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class HelloControllerClientTests {
  @Test
  void testHttpStatus500() throws IOException, InterruptedException {
    HttpClient client =
        HttpClient.newBuilder() //
            .build();
    HttpRequest request =
        HttpRequest.newBuilder() //
            .uri(
                URI.create(
                    "http://localhost:8080")) // It would be nice if the port was random... 😂
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
