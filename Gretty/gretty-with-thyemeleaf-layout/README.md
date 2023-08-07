# Exception occurs when using Gretty and Thymeleaf Layout Dialect together.



Hello.

After running the web server with Gretty and accessing the layout page, the following exception is exposed and nothing is displayed on the web browser screen.

* Exception Log

  ```
  ...
  Caused by: groovy.lang.MissingMethodException: No signature of method: org.thymeleaf.engine.Model.first() is applicable for argument types: () values: []
  Possible solutions: print(java.io.PrintWriter), print(java.lang.Object), find(), find(groovy.lang.Closure), is(java.lang.Object), write(java.io.Writer)
  ...
  ```



My project environment looks like this:

* Spring 6 (Not Spring Boot): `6.0.11`
* Thymeleaf
  * thymeleaf-spring6: `3.1.2.RELEASE`
  * thymeleaf-layout-dialect: `3.2.1`
* Gretty: `4.1.0`
  * Tomcat: `10.1.11`
* Gradle: `8.2.1`
* Java: `17`



When I run the web project, I run it with the command below.

```sh
gradle clean appRun
```

I ran Gretty and accessed the page with the layout applied, and I definitely got the exception mentioned above.

**However, when I deploy and run it directly into Tomcat without using Gretty, I get no errors. I'm not sure why.**





### ✨ Example project to see the problem

I created an example project for you to check the problem.

* Example Project Zip File

  * ...

* Example project GitHub address

  * https://github.com/mklinkj/QnA/tree/master/Gretty/gretty-with-thyemeleaf-layout

* How to run

  * Run with Gretty

    ```sh
    gradle clean appRun
    ```

  * Accessing the layout page in a web browser

    * `http://localhost:8090/main`




Have a nice day. 👍



---

# Gretty와 Thymeleaf Layout Dialect를 함깨 사용할 때, 예외가 발생하는 문제.



안녕하세요.

Gretty로 웹 서버를 실행할 때.. 레이아웃 페이지에 접근하면, 아래의 예외가 노출되고 웹 브라우저 화면에는 아무것도 뜨지 않습니다.



* 예외 로그

  ```
  ...
  Caused by: groovy.lang.MissingMethodException: No signature of method: org.thymeleaf.engine.Model.first() is applicable for argument types: () values: []
  Possible solutions: print(java.io.PrintWriter), print(java.lang.Object), find(), find(groovy.lang.Closure), is(java.lang.Object), write(java.io.Writer)
  ...
  ```



저의 프로젝트 환경은 아래와 같습니다.

* Spring 6 (Spring Boot 아님): `6.0.11`
* Thymeleaf
  * thymeleaf-spring6: `3.1.2.RELEASE`
  * thymeleaf-layout-dialect: `3.2.1`
* Gretty: `4.1.0`
  * Tomcat: `10.1.11`
* Gradle: `8.2.1`
* Java: `17`



웹 프로젝트를 실행 시킬 때, 아래 명령으로 실행 시키고 있습니다.

```sh
gradle clean appRun
```

Gretty를 통해서 실행해서 레이아웃이 적용된 페이지에 접근하면,  반드시 위에 언급한 예외가 생깁니다.

**그런데 Gretty 를 사용하지 않고 직접 Tomcat에 배포해서 실행하면 오류가 나지 않습니다. 왜그런지 이유를 잘 모르겠습니다.**





### ✨ 문제를 확인해 볼 수 있는 예제 프로젝트

문제를 확인해 볼 수 있는 예제 프로젝트를 만들었습니다.

* 예제 프로젝트  깃허브 주소
  * https://github.com/mklinkj/QnA/tree/master/Gretty/gretty-with-thyemeleaf-layout

* 실행 방법

  * Gretty로 실행

    ```sh
    gradle clean appRun
    ```

  * 웹브라우저에서 레이아웃 페이지 접근하기

    * `http://localhost:8090/main`



감사합니다. 좋은하루되세요. 👍



### 이슈 제출

* https://github.com/gretty-gradle-plugin/gretty/issues/289
