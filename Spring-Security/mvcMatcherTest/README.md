

# Question. MVC Matcher rules that worked in Spring Security 5.7.6 don't work in 6.0.1.

Hello.

MVC Matcher rules that worked in Spring Security 5.7.6 don't work in 6.0.1.



#### Spring 5.7.6 configuration (Spring Boot 2.7.7 environment)

* Example Project: [boot_2_7_7_security_5_7_6](boot_2_7_7_security_5_7_6)

* Configuration

  ```java
      http.authorizeHttpRequests() //
          .mvcMatchers("/hello")
          .authenticated();
  ```

* Test code

  ```java
    // HTTP 200 Response
    @Test
    @WithUserDetails("mklinkj")
    void testCallingHelloVariationWithAuthentication() throws Exception {
      mvc.perform(get("/hello/")) //
          .andExpect(status().isOk());
    }
  ```

  



#### Spring 6.0.1 configuration (Spring Boot 3.0.1 environment)

* Example Project: [boot_3_0_1_security_6_0_1](boot_3_0_1_security_6_0_1)

* Configuration

  ```java
      http.authorizeHttpRequests() //
          .requestMatchers("/hello")
          .authenticated();
  ```

* Test code

  ```java
    // HTTP 403 Response
    @Test
    @WithUserDetails("mklinkj")
    void testCallingHelloVariationWithAuthentication() throws Exception {
      mvc.perform(get("/hello/")) //
          .andExpect(status().isForbidden());
    }
  ```

  

As above, I expected 200, but I get 403 response in Spring Security 6.0.1 environment.

Please let me know if there are any settings I am doing wrong.





---

# 질문.  Spring Security 5.7.6에서 동작하던 MVC Matcher 규칙이 6.0.1에서 동작하지 않습니다.



안녕하세요. 

Spring Security 5.7.6에서 동작하던 MVC Matcher 규칙이 6.0.1에서 동작하지 않습니다.



#### Spring 5.7.6 설정 (Spring Boot 2.7.7 환경)

* 예제 프로젝트: [boot_2_7_7_security_5_7_6](boot_2_7_7_security_5_7_6)

* 설정 클래스

  ```java
      http.authorizeHttpRequests() //
          .mvcMatchers("/hello")
          .authenticated();
  ```

* 테스트 코드 

  ```java
    // HTTP 200 응답입니다.
    @Test
    @WithUserDetails("mklinkj")
    void testCallingHelloVariationWithAuthentication() throws Exception {
      mvc.perform(get("/hello/")) //
          .andExpect(status().isOk());
    }
  ```

  

#### Spring 6.0.1 설정 (Spring Boot 3.0.1 환경) 

* 예제 프로젝트: [boot_3_0_1_security_6_0_1](boot_3_0_1_security_6_0_1)

* 설정 클래스

  ```java
      http.authorizeHttpRequests() //
          .requestMatchers("/hello")
          .authenticated();
  ```

* 테스트 코드

  ```java
    // HTTP 403 응답
    @Test
    @WithUserDetails("mklinkj")
    void testCallingHelloVariationWithAuthentication() throws Exception {
      mvc.perform(get("/hello/")) //
          .andExpect(status().isForbidden());
    }
  ```

  

위의 내용처럼 200을 기대하였는데, Spring Security 6.0.1환경에서 403 응답을 받습니다.

혹시 제가 잘못한 설정이 있는지 문의드립니다.



#### 





