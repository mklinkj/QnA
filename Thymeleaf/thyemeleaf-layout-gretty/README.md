# Thymeleaf Layout Dialect와 Gretty를 함깨 사용할 때, 레이아웃 적용한 페이지 방문시 예외가 발생하는 문제.



안녕하세요

저도 같은 문제를 겪고 있습니다.

레이아웃 페이지에 접근하면, 아래의 예외가 노출되고 화면에는 아무것도 뜨지 않습니다.

* 예외 로그

  ```
  Caused by: java.lang.ClassCastException: class nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.EventIterator cannot be cast to class [Ljava.lang.Object; (nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.EventIterator is in unnamed module of loader java.net.URLClassLoader @889d9e8; [Ljava.lang.Object; is in module java.base of loader 'bootstrap')
          at org.codehaus.groovy.runtime.dgm$236.doMethodInvoke(Unknown Source) ~[groovy-4.0.10.jar:4.0.10]
          at org.codehaus.groovy.vmplugin.v8.IndyInterface.fromCache(IndyInterface.java:321) ~[groovy-4.0.10.jar:4.0.10]
          at nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.IModelExtensions.find(IModelExtensions.groovy:117) ~[thymeleaf-layout-dialect-3.2.1.jar:?]
          ...
  ```



저의 프로젝트 환경은 아래와 같습니다.

* Spring 6 (Spring Boot 아님)
* Thymeleaf
  * thymeleaf-spring6: `3.1.1.RELEASE`
  * thymeleaf-layout-dialect: `3.2.1`
* Gretty `4.0.3`
  * Tomcat `10.1.10`



웹 프로젝트를 실행 시킬 때, 아래 명령으로 실행 시키고 있습니다.

```sh
gradle clean appRun
```

Gretty를 통해서 실행해서 레이아웃이 적용된 페이지에 접근하면,  반드시 위의 오류가 생깁니다.



그런데 특이한 점이 있습니다.



### ✨ 특이한 점 2가지

1. Gretty 를 사용하지 않고 직접 Tomcat에 배포해서 사용하면 오류가 나지 않습니다. 왜그런지 이유를 잘 모르겠습니다.

2. 앞의 리플에서 언급된 `https://github.com/zhanhb/thymeleaf-layout-dialect` 를 사용하면 Gretty로 웹 프로젝트를 실행하더라도 문제가 없습니다.

   ```groovy
     // TODO: Gretty와 thymeleaf-layout-dialect를 함깨 사용하면 오류가 발생합니다.
     implementation "nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:${thymeleafLayoutVersion}"
     // TODO: zhanhb에 의해 수정된 thymeleaf-layout-dialect를 사용하면 오류가 발생하지 않습니다.
     // implementation "com.github.zhanhb:thymeleaf-layout-dialect:${zhanhbThymeleafLayoutVersion}"
   ```
   
   



### ✨ 문제를 확인해 볼 수 있는 예제 프로젝트

문제가 확인해 볼 수 있는 예제 프로젝트를 만들었습니다. 검토를 부탁드립니다.

* 예제 프로젝트 주소
  * ...

* 실행 방법

  * Gretty로 실행

    ```sh
    gradle clean appRun
    ```

  * 웹브라우저에서 레이아웃 페이지 접근하기

    * `http://localhost:8090/main`




감사합니다. 좋은하루되세요.
