> ✨ 이 프로젝트는 상태를 남겨야하므로 Gretty, Thymeleaf 에 대해서는 버전업그레이드를 하지 않는게 낫겠다.
>
> * Gretty, Thymeleaf 
>
>   Gretty 버전을 `4.0.3`에서 `4.1.0`으로 올리면 다른 문제가 나타남.

# When using Gretty with the Thymeleaf Layout Dialect, an exception was thrown when visiting a layout-applied page.



Hello.

When running the web server with Gretty.. When accessing the layout page, the following exception is exposed and nothing appears on the web browser screen.

* Exception Log

  ```
  Caused by: java.lang.ClassCastException: class nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.EventIterator cannot be cast to class [Ljava.lang.Object; (nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.EventIterator is in unnamed module of loader java.net.URLClassLoader @889d9e8; [Ljava.lang.Object; is in module java.base of loader 'bootstrap')
          at org.codehaus.groovy.runtime.dgm$236.doMethodInvoke(Unknown Source) ~[groovy-4.0.10.jar:4.0.10]
          at org.codehaus.groovy.vmplugin.v8.IndyInterface.fromCache(IndyInterface.java:321) ~[groovy-4.0.10.jar:4.0.10]
          at nz.net.ultraq.thymeleaf.layoutdialect.models.extensions.IModelExtensions.find(IModelExtensions.groovy:117) ~[thymeleaf-layout-dialect-3.2.1.jar:?]
          ...
  ```



My project environment looks like this

* Spring 6 (Not Spring Boot )
* Thymeleaf
  * thymeleaf-spring6: `3.1.1.RELEASE`
  * thymeleaf-layout-dialect: `3.2.1`
* Gretty `4.0.3`
  * Tomcat `10.1.10`



When I run the web project, I run it with the command below.

```sh
gradle clean appRun
```

When I ran Gretty and accessed the page with the layout applied, I definitely saw the aforementioned exception.



However, there are two things that are unusual.



### ✨ Two unusual things

1. If I'm using a direct deployment of Tomcat without using Gretty, I don't get the exception.

2. If I use `https://github.com/zhanhb/thymeleaf-layout-dialect`, there's no problem running a web project with Gretty.

   ```groovy
     // TODO: Using gretty with thymeleaf-layout-dialect causes an exception
     implementation "nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:${thymeleafLayoutVersion}"
     // TODO: If I use the thymeleaf-layout-dialect modified by zhanhb, the exception does not occur.
     // implementation "com.github.zhanhb:thymeleaf-layout-dialect:${zhanhbThymeleafLayoutVersion}"
   ```

   



### ✨ Example project to see the problem

I've created an example project for you to see the issue.

* Example project github url

  * https://github.com/mklinkj/QnA/tree/master/Thymeleaf/thyemeleaf-layout-gretty

* How to run

  * Run with Gretty

    ```sh
    gradle clean appRun
    ```

  * Accessing the layout page in a web browser

    * `http://localhost:8090/main`




Thank you. Have a nice day. 👍



---

# Thymeleaf Layout Dialect와 Gretty를 함깨 사용할 때, 레이아웃 적용한 페이지 방문시 예외가 발생하는 문제.



안녕하세요

Gretty로 웹 서버를 실행할 때.. 레이아웃 페이지에 접근하면, 아래의 예외가 노출되고 웹 브라우저 화면에는 아무것도 뜨지 않습니다.



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

Gretty를 통해서 실행해서 레이아웃이 적용된 페이지에 접근하면,  반드시 먼저 언급한 예외가 생깁니다.



그런데, 특이한 점 2가지가 있습니다.



### ✨ 특이한 점 2가지

1. Gretty 를 사용하지 않고 직접 Tomcat에 배포해서 사용하면 오류가 나지 않습니다. 왜그런지 이유를 잘 모르겠습니다.

2. https://github.com/zhanhb/thymeleaf-layout-dialect`를 사용하면 Gretty로 웹 프로젝트를 실행하는 데 아무런 문제가 없습니다.

   ```groovy
     // TODO: Gretty와 thymeleaf-layout-dialect를 함깨 사용하면 오류가 발생합니다.
     implementation "nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:${thymeleafLayoutVersion}"
     // TODO: zhanhb에 의해 수정된 thymeleaf-layout-dialect를 사용하면 오류가 발생하지 않습니다.
     // implementation "com.github.zhanhb:thymeleaf-layout-dialect:${zhanhbThymeleafLayoutVersion}"
   ```
   
   



### ✨ 문제를 확인해 볼 수 있는 예제 프로젝트

문제를 확인해 볼 수 있는 예제 프로젝트를 만들었습니다.

* 예제 프로젝트  깃허브 주소
  * https://github.com/mklinkj/QnA/tree/master/Thymeleaf/thyemeleaf-layout-gretty

* 실행 방법

  * Gretty로 실행

    ```sh
    gradle clean appRun
    ```

  * 웹브라우저에서 레이아웃 페이지 접근하기

    * `http://localhost:8090/main`




감사합니다. 좋은하루되세요. 👍
