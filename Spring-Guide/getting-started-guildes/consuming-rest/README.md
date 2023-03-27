# RESTful 웹 서비스 사용



> #### 예제 
>
> * https://spring.io/guides/gs/consuming-rest/



## 서비스를 제공할  RESTful 웹 서비스  준비

* RESTful 웹 서비스를 제공하는 프로젝트를 Clone 받아 가상머신에서 실행
  * https://github.com/spring-guides/quoters
    * maven 빌드
    * Spring Boot 2.7.x

* API 프로젝트를 가상머신에서 실행시켜 둠. : `http://lvm.test-linux:8090`

* 가상머신에서 TCP 8080 포트를 다른 프로젝트가 사용중이여서, 8090으로 띄우고 방화벽에 설정 추가함.

  * application.properties 에서 서버 포트 변경

    ```properties
    server.port=8090
    ```

  * 방화벽 포트 추가 (잘 까먹어서 또 적어둠..😅)
    ```sh
    # 8090 포트 추가
    sudo firewall-cmd --permanent --add-port=8090/tcp
    # 8090 포트 제거
    # sudo firewall-cmd --permanent --remove-port=8090/tcp
    # 변경사항 적용
    sudo firewall-cmd --reload
    ```


* 호출 테스트

  * http://lvm.test-linux:8090/api
  * http://lvm.test-linux:8090/api/1
  * ...
  * http://lvm.test-linux:8090/api/random
  * GET호출이 되서 브라우저에서 주소 입력하고 봐도 응답 내용을 바로 볼 수 있다.

* 응답 모양

  ```json
  {
      "type":"success",
      "value":{
          "id":10,
          "quote":"Really loving Spring Boot, makes stand alone Spring apps easy."
      }
  }
  ```

  

## 서비스를 사용할 예제 프로젝트 작성

### RestTemplate 사용을 위해 spring-boot-starter-web 추가

* build.gradle

  ```groovy
  implementation 'org.springframework.boot:spring-boot-starter-web'
  ```

* 그런데 main 메서드에서 REST API 호출만 한번하고 프로그램을 종료할 것이므로 Tomcat을 실행시키진 않음

  * application.yml

    ```yml
    spring:
      main:
        web-application-type: none
    ```

  


#### 결과를 받을 도메인

* Java 17 환경이므로 record로 생성

  ```java
  public record Quote(String type, Value value) {}
  
  public record Value(int id, String quote) {}
  ```

  * `@JsonIgnoreProperties(ignoreUnknown = true)` 가 붙어있었는데.. 여기서는 필드가 정해져 있어서 이걸 설정할 필요는 없을 것 같다.

#### RestTemplateBuilder 사용 해서 RestTemplate 만들기

```java
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
```



#### 코드 실행기

* https://www.amitph.com/spring-boot-runners/

  * 차이점 설명을 보긴 했는데...  잘모르겠음.😅

    ```java
      @Bean
      ApplicationRunner run(RestTemplate restTemplate) {
        return args -> {
          Quote quote = restTemplate.getForObject("http://lvm.test-linux:8090/api/random", Quote.class);
          LOGGER.info("quote: {}", quote);
        };
      }
    ```

    

### 실행결과

```
...
17:52:01.082 [main] INFO  o.m.q.s.c.ConsumingRestApplication - quote: Quote[type=success, value=Value[id=9, quote=So easy it is to switch container in #springboot.]]
```





## 의견

* RestTemplate을 그냥 new로 만들었었는데... builder를 사용하는 것이 미리 정의된 기본적인 설정이 더 들어가서 낳다고 하신다..👍, 앞으로도 생성시 빌더를 쓰도록 하자.

