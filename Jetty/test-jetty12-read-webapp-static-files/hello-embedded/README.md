# Embedded Jetty 예제

> Embedded 방식으로 Jetty를 실행하는 내용추가.
>
> `src/main/webapp/WEB-INF/web.xml`을 인식해서 실행까지는 했는데..
> 기본 JSP를 인식해서 사용하는 방법을 아직 모르겠음.. 😅



### 실행

```sh
mvn clean compile exec:java
```

* localhost:8080 접속시 단순하게 webapp 루트의 index.html 노출
* helloworld 서블릿 주소로 접근하면 포워딩 결과 대상인 helloword.jsp를 처리하지 못해서 오류남. 😅

* 정적 경로들은 잘 접근이 됨
  * http://localhost:8080/index.html
  * http://localhost:8080/resources/images/smile.png 


### 참고 해야할 예제
* https://github.com/jetty-project/embedded-jetty-jsp