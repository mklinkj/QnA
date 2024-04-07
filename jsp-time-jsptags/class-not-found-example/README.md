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
runtimeOnly (libs.jakarta.jstl.api)
```

💡 java-time-jsptags 2.0.2 버전 부터는 jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api를 사용해야하는 것 같다.

* libs.jakarta.jstl.api
  * jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api





## 의견

다른 프로젝트도 바꿔두자! ...😊👍