## 다음 이슈 관련 질문용 예제 프로젝트 생성

* https://github.com/spring-projects/spring-security/issues/13789
* https://github.com/spring-projects/spring-security/issues/13794

---

hello.

I've created a simple example related to your issue.



### Example Project URL

* https://github.com/mklinkj/QnA/tree/master/Spring-Security/requestMatchers-example



### Example project environment

* Spring 5.3.30
* Spring Security 
  * 5.8.4: No problem.
  * 5.8.8: Exception thrown when running WAS. (Version 5.8.5 and later)
* Running WAS with Gretty
  * Tomcat 9.0.82
  * Jetty 10.0.18



First, Spring Security version 5.8.4 is fine.

Starting with 5.8.5 and later versions, the exception below occurs.

In 5.8.5 and later, to run without exceptions, you had to use `antMatcher()` like the code below.

```java
    http.authorizeHttpRequests(
            (authz) ->
                authz
                    /*
                    .requestMatchers(
                        "/webjars/**", //
                        "/resources/**",
                        "/",
                        "/index",
                        "/login",
                        "/favicon.ico")
                    */
                    .requestMatchers(
                        antMatcher("/webjars/**"), //
                        antMatcher("/resources/**"),
                        antMatcher("/"),
                        antMatcher("/index"),
                        antMatcher("/login"),
                        antMatcher("/favicon.ico"))
                    .permitAll()
                    // .requestMatchers("/admin")
                    .requestMatchers(antMatcher("/admin"))
                    .hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated())
```





#### How to run the example project

* **Tomcat 9.0.82**

  * Set Gretty's settings in `build.gradle` to `servletContainer = "tomcat9"`

    ```
    ./gradlew clean appRun
    ```

* **Jetty 10.0.18**

  * Set Gretty's settings in `build.gradle` to `servletContainer = "jetty10"`

    ```sh
    ./gradlew clean appRun
    ```

  

### Exceptions that occur when running with Tomcat 9.0.82

```
Caused by: java.lang.UnsupportedOperationException: Section 4.4 of the Servlet 3.0 specification does not permit this method to be called from a ServletContextListener that was not defined in web.xml, a web-fragment.xml file nor annotated with @WebListener
        at org.apache.catalina.core.StandardContext$NoPluggabilityServletContext.getServletRegistrations(StandardContext.java:6306)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.mappableServletRegistrations(AbstractRequestMatcherRegistry.java:333)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:317)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:394)
        at org.mklinkj.qna.spring_security.security.HelloSecurityConfig.lambda$securityFilterChain$0(HelloSecurityConfig.java:50)
        ...
```



### Exceptions encountered when running with Jetty 10.0.18

```
Caused by:
java.lang.UnsupportedOperationException
        at org.eclipse.jetty.servlet.ServletContextHandler$Context.getServletRegistrations(ServletContextHandler.java:1385)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.mappableServletRegistrations(AbstractRequestMatcherRegistry.java:333)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:317)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:394)
        at org.mklinkj.qna.spring_security.security.HelloSecurityConfig.lambda$securityFilterChain$0(HelloSecurityConfig.java:50)
```

I would appreciate your confirmation.

Thank you. Have a nice day. 👍



---



안녕하세요.

이슈와 관련해서 단순한 예제를 만들었습니다.

### 예제 프로젝트 주소

* https://github.com/mklinkj/QnA/tree/master/Spring-Security/requestMatchers-example



### 예제 프로젝트 환경

* Spring 5.3.30
* Spring Security 
  * 5.8.4: 문제 없음.
  * 5.8.8: WAS 실행시 예외가 발생함.
* Gretty를 통한 WAS 실행
  * Tomcat 9.0.82
  * Jetty 10.0.18



먼저 Spring Security 5.8.4 버전에서는 문제가 없습니다.

5.8.5 이상 버전부터 아래 예외가 발생합니다.

5.8.5 이상 버전에서 예외 없이 실행하려면 아래의 코드 처럼 반드시 `antMatcher()`를 써야했습니다.

```java
    http.authorizeHttpRequests(
            (authz) ->
                authz
                    /*
                    .requestMatchers(
                        "/webjars/**", //
                        "/resources/**",
                        "/",
                        "/index",
                        "/login",
                        "/favicon.ico")
                    */
                    .requestMatchers(
                        antMatcher("/webjars/**"), //
                        antMatcher("/resources/**"),
                        antMatcher("/"),
                        antMatcher("/index"),
                        antMatcher("/login"),
                        antMatcher("/favicon.ico"))
                    .permitAll()
                    // .requestMatchers("/admin")
                    .requestMatchers(antMatcher("/admin"))
                    .hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated())
```





#### 예제 프로젝트 실행방법

* **Tomcat 9.0.82**

  * build.gradle의 Gretty의 설정을 `servletContainer = "tomcat9"` 으로 설정

    ```
    ./gradlew clean appRun
    ```

* **Jetty 10.0.18**

  * Gretty의 설정을 `servletContainer = "jetty10"` 으로 설정

    ```sh
    ./gradlew clean appRun
    ```

  

### Tomcat 9.0.82 로 실행할 경우 예외

```
Caused by: java.lang.UnsupportedOperationException: Section 4.4 of the Servlet 3.0 specification does not permit this method to be called from a ServletContextListener that was not defined in web.xml, a web-fragment.xml file nor annotated with @WebListener
        at org.apache.catalina.core.StandardContext$NoPluggabilityServletContext.getServletRegistrations(StandardContext.java:6306)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.mappableServletRegistrations(AbstractRequestMatcherRegistry.java:333)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:317)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:394)
        at org.mklinkj.qna.spring_security.security.HelloSecurityConfig.lambda$securityFilterChain$0(HelloSecurityConfig.java:50)
        ...
```



### Jetty 10.8.18로 실행할 경우 예외

```
Caused by:
java.lang.UnsupportedOperationException
        at org.eclipse.jetty.servlet.ServletContextHandler$Context.getServletRegistrations(ServletContextHandler.java:1385)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.mappableServletRegistrations(AbstractRequestMatcherRegistry.java:333)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:317)
        at org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.requestMatchers(AbstractRequestMatcherRegistry.java:394)
        at org.mklinkj.qna.spring_security.security.HelloSecurityConfig.lambda$securityFilterChain$0(HelloSecurityConfig.java:50)
```



그럼 확인을 부탁드립니다.

감사합니다. 좋은하루되세요. 👍



---

### 5.8.4 환경에서 실행하거나 5.8.8환경 에서 antMatchers()를 사용하여 정상 실행시켰을 때의 화면

### (When running in a 5.8.4 environment, or when running normally using antMatchers() in a 5.8.8 environment.)

![image-20231116152243835](doc-resources/image-20231116152243835.png)

![image-20231116152402532](doc-resources/image-20231116152402532.png)

![image-20231116152453055](doc-resources/image-20231116152453055.png)
