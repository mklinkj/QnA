package org.mklinkj.qna.spring_security.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Slf4j
class HelloControllerClientTests {
  private int getGrettyRandomPort() {
    Path portProps =
        Paths.get(Paths.get("build").toAbsolutePath().toString(), "gretty_ports.properties");
    Properties properties = new Properties();

    try (InputStream inputStream = Files.newInputStream(portProps, StandardOpenOption.READ)) {
      properties.load(inputStream);

      // 😅 TODO: 프로퍼티의 포트번호 내용에 +3을 해줘야 정상 설정됨, 왜 그럴까? ㅠㅠ
      //      The value of the ‘servicePort’ item in the properties file needs to be
      //      increased by 3 for correct configuration.
      return Integer.valueOf(properties.getProperty("servicePort")) + 3;
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to load servicePort value.", e);
    }
  }

  @Test
  void testHttpStatus200() throws IOException, InterruptedException {

    HttpClient client =
        HttpClient.newBuilder() //
            .build();
    HttpRequest request =
        HttpRequest.newBuilder() //
            .uri(URI.create("http://localhost:%d".formatted(getGrettyRandomPort())))
            .timeout(Duration.ofSeconds(5)) // 타임아웃 설정을 꼭 해야한다. 제대로 연결이 안될 때, 무한정 대기함.
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void testLoadProps() throws IOException {
    String value =
        """
        ### value: #Sat Nov 18 10:25:33 KST 2023
        statusPort=50552
        servicePort=50553
        """;
    Properties properties = new Properties();
    properties.load(new StringReader(value));
    assertThat(properties.getProperty("servicePort")).isEqualTo("50553");
  }
}
