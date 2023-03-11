package webclient.tutorial;

import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * '토비'님의 "Spring 6의 새로운 HTTP Interface와 3 가지 REST Clients 라이브 코딩" 따라해보기
 *
 * <p>https://www.youtube.com/watch?v=Kb37Q5GCyZs
 */
@Component
public class App {
  private static final String API_HOST = "https://open.er-api.com";
  private static final String API_PATH = "/v6/latest";

  private final RestTemplate restTemplate;

  private final WebClient webClient;

  private final ErApi erApi;

  public App(RestTemplate restTemplate, WebClient webClient, ErApi erApi) {
    this.restTemplate = restTemplate;
    this.webClient = webClient;
    this.erApi = erApi;
  }

  @SuppressWarnings("unchecked")
  void run() {
    // RestTemplate 사용
    Map<String, Map<String, Double>> result =
        restTemplate.getForObject(API_HOST + API_PATH, Map.class);
    System.out.println(result.get("rates").get("KRW"));

    // WebClient 사용
    Map<String, Map<String, Double>> result2 =
        webClient.get().uri(API_PATH).retrieve().bodyToMono(Map.class).block();
    System.out.println(result2.get("rates").get("KRW"));

    // HTTP interface 사용
    Map<String, Map<String, Double>> result3 = erApi.getLatest();
    System.out.println(result3.get("rates").get("KRW"));
  }

  interface ErApi {
    @GetExchange(API_PATH)
    Map getLatest();
  }

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class)) {
      context.getBean("app", App.class).run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Configuration
  @ComponentScan("webclient.tutorial")
  public static class AppConfig {
    @Bean
    RestTemplate restTemplate() {
      return new RestTemplate();
    }

    @Bean
    WebClient webClient() {
      return WebClient.create(API_HOST);
    }

    @Bean
    ErApi erApi() {
      HttpServiceProxyFactory httpServiceProxyFactory =
          HttpServiceProxyFactory //
              .builder(WebClientAdapter.forClient(webClient())) //
              .build();
      return httpServiceProxyFactory.createClient(ErApi.class);
    }
  }
}
