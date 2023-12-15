# Jetty12에서 ServletContext#getRealPath() 가 null을 반환하는 문제 확인용 예제



Struts2 를 사용한 다른 프로젝트에서 Jetty12 버전업 후 리스너의 contextInitialized 메서드에서 얻은 ServletContext 의 getRealPath() 를 호출하면 null을 반환하길레, 최소한의 예제를 만들어보았는데...



여기선 문제가 없음 😅 



## [simple-servlet](simple-servlet) 단순 서블릿 프로젝트

#### Jetty 12 실행

```sh
mvn clean jetty:run
```

#### Jetty 10 실행

```
mvn clean jetty:run -Pjetty10
```



#### 실행시 리스너에 설정한 로그확인

```java
public class TestGetRealPathListener implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestGetRealPathListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    LOGGER.info("### listener {}", sce.getServletContext().getRealPath("/WEB-INF/"));
  }
}
```

```
08:17:30.697 [main] INFO  org.mklinkj.qna.TestGetRealPathListener - ### listener {LOCAL_GIT_REPO}\QnA\Jetty\test-jetty12-servlet-context-get-real-path\src\main\webapp\WEB-INF/
```

Jetty 12에서도 경로 제대로 출력됨 😅



#### 서블릿 내에서 설정한 코드도 정상 동작

```java
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.getWriter().println(getClass().getSimpleName());
    var realPath = context.getRealPath("/WEB-INF/");
    LOGGER.info("### servlet: {}", realPath);
    response.getWriter().println(realPath);
  }
```

```
08:19:24.272 [qtp1680139795-33] INFO  org.mklinkj.qna.TestGetRealPathServlet - ### servlet: {LOCAL_GIT_REPO}\QnA\Jetty\test-jetty12-servlet-context-get-real-path\src\main\webapp\WEB-INF
```



음... 이러면  Struts를 붙여봐야하나...😆

webpapp 경로의 index.jsp도 정상 접근하는 걸로봐서 DefaultServlet 도 잘동작하는 것 같은데...
그래도 web.xml의 welcome 목록에 index.jsp가 등록이 되어있는데... 12에서는 / 에서 바로 index.jsp로 가질 않음.. 10에서는 가는데...



## [simple-struts2](simple-struts2) 단순 Struts 2 프로젝트

단순 Struts 2 테스트를 만들어서 Jetty 12에서 확인해보았는데, 정상적으로 realPath를 가져온다.. 

문제가 있던 프로젝트는... 뭐가 문제일까?



