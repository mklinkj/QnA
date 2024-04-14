# java-time-jsptags 버전업 이후java.lang.ClassNotFoundException: jakarta.servlet.jsp.jstl.core.Config  발생 이슈 확인

>  2.0.2로 버전을 올리고 나서 위의 문제가 발생해서 예제 프로젝트 만들어서 확인



2.0.2 버전을 사용하면서 다음과 같은 구성에서는 java.lang.ClassNotFoundException: jakarta.servlet.jsp.jstl.core.Config 가 발생함.

```groovy
  implementation "net.sargue:java-time-jsptags:2.0.2"
  runtimeOnly (libs.jakarta.jstl)
```

* libs.jakarta.jstl
  * org.glassfish.web:jakarta.servlet.jsp.jstl



다음과 같이 바꿔주면 됨

```groovy
implementation "net.sargue:java-time-jsptags:2.0.2"
// runtimeOnly (libs.jakarta.jstl) // gretty + Jetty11, Tomcat 10.1 환경에서 이 라이브러리는 없어도 되긴했다. 
runtimeOnly (libs.jakarta.jstl.api)
```

💡 2.0.2 부터는 jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api도 상황에 따라 추가되야함.

* libs.jakarta.jstl.api
  * jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api



### Gretty를 통한 Tomcat 10.1.x, Jetty11 실행

```bash
# Tomcat 실행
gradle clean tomcatRun

# Jetty 11 실행
gradle clean jettyRun
```

* 두 경우 모두 jakarta.servlet.jsp.jstl-api 만 추가 해주어도 되었음.



#### 결론

결국 실행할 WAS 환경에서 해당 라이브러리가 이미 있는지 없는지에 때라서 없을 경우 넣어주면 됨.

이번에 2.0.2로 바뀌면서 jakarta.servlet.jsp.jstl-api의 디펜던시가 전파되지 않으면서 생긴 현상임.





## 의견

다른 프로젝트도 바꿔두자! ...😊👍