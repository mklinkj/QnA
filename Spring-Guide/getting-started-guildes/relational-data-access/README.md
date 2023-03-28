# Spring에서 JDBC를 사용하여 관계형 데이터에 액세스



> #### 예제 
>
> * https://spring.io/guides/gs/relational-data-access/
> * JdbcTemplate를 배치 레파지토리 생성 / 정리 프로젝트 할 때만 써봤던 것 같음.. 보통은 쓸일이 별로 없었던 것 같긴하다.
> * 다른 스터디 프로젝트에서 SqlSession 사용하는 코드를 탬플릿으로 바꾸고 싶은데.. 참고해서 따라해보고 싶다.





## 진행

### JdbcTemplate 로깅

* ApplicationRunner에 `@Bean`을 안붙여놓고, JdbcTemplate 로그가 왜 뜨지않지? 로깅 설정을 잘못했나 해깔려서 시간소모 했다.
  * logback-spring.xml을 그냥 추가함. application.yml에서도 쉽게 할 수 있는데.. 설정파일 추가했음.




### h2 메모리 데이터 베이스 접근

* application.yml

  ```yml
  spring:
    h2:
      console:
        enabled: true
        path: /h2-console
    datasource:
      url: jdbc:h2:mem:testdb
  ```

  * 콘솔이 기본적으로는 disable 상태여서 열어줘야한다.

  * 내장 Tomcat이 실행되야 사용할 수 있어서, `spring-boot-starter-web`가 추가되야함.

    ```groovy
    implementation 'org.springframework.boot:spring-boot-starter-web'
    ```

    

### 콘솔에서 `y`입력하면 프로그램 종료하는 코드 추가

```java
      try (Scanner sc = new Scanner(System.in)) {
        String yes = "n";
        System.out.println("[알림] 프로그램 종료하시려면 y키 눌러주세요.");
        while (!"y".equals(yes)) {
          yes = sc.next();
        }
        SpringApplication.exit(context, () -> 0);
      }
```

spring-boot-starter-web 을 사용하지 않았을 때.. 그냥 종료되어버러서 h2 DB에 접근 못할 까봐 추가했던 코드인데... HTTP 서버가 그냥 실행되어버리기 때문에 크게의미는 없지만.. 콘솔에서 y누르면 응용프로그램이 안전하게 종료된다. 😅



## 의견

* 여기서 제일 궁금했던 것은... 

  ```java
        jdbcTemplate.execute(
            """
            CREATE TABLE customers(
              id SERIAL,
              first_name VARCHAR(255),
              last_name VARCHAR(255)
            )
            """);
  ```

  위와 같은 코드가 정상 실행되는지의 여부였다.

  JPA할 때.. sql파일 실행할 때.. 멀티라인 Excuter 같은 것을 써야 정상적으로 되었어서... 그런데.. 잘된다..



* 예제에서 Deprecated 된 메서드를 사용하는 부분이 있던데...

  ```java
  	@Deprecated
  	@Override
  	public <T> List<T> query(String sql, @Nullable Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
  		return result(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)));
  	}
  ```

  스프링 부트 버전을 2.7.5 정도로을 낮춘 상태에서도 Deprecated 상태인 걸로 봐서는 이건 내가 관련 부분 수정해서 풀리퀘스트 보내도 처리해줄 것 같긴함. 

  바로 아래 메서드를 사용하면 args를 그냥 전달하는 식으로 사용하면 될 듯...

  ```java
      @Override
  	public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args) throws DataAccessException {
  		return result(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)));
  	}
  ```

  

