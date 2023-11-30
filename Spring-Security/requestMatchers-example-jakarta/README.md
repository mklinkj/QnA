## 아래 예제가 Spring 5.3.30 + Spring Security 5.8.8에서 잘 안되서 질문한적 있는데...

* https://github.com/mklinkj/QnA/tree/master/Spring-Security/requestMatchers-example



이번에는 Servlet 버전을 6으로 올리고 Spring, Spring Security 버전을 최신으로 올려서 확인해보자.



### 예제 프로젝트 환경

* Spring 6.0.13

* Spring Security 
  * 6.1.1 (6.1.2 부터는 이전환경과 동일하게 WAS 실행이 실패한다.)
  
* Gretty를 통한 WAS 실행
  * Tomcat 10.1.16
  
    

확인을 해보니..  최신환경에서도 Spring Security 6.1.1까지만 잘된다. 6.1.2 부터는 동일한 오류가 발생함.



---

### 설정 변경

* https://github.com/spring-projects/spring-security/issues/13602#issuecomment-1816499124

위의 댓글 내용대로 설정을 바꿨을 때, 정상 실행된다.



---

#### ✨ 2023년 12월 1일 확인

* https://github.com/spring-projects/spring-security/issues/13794#issuecomment-1834103895

개발자님 권고대로 Security 설정을 바꿈.

* ✨ 그런데 새로 바꾼 설정 그대로 두고,  버전을 6.1.5로 낮추면 문제는 재현됨.

현시점의 `6.1.6-SNAPSHOT`에서는 문제가 없어졌는데, 아무래도 이 스냅샷 버전에 변경이 있던 것 같다.

`5.8.9-SNAPSHOT` 버전도  문제가 없어졌음.



이렇다면, 스프링 버전을 `5.8.9`, `6.1.6` 으로 정식 버전업하게 되면  `.requestMatchers("{mvc경로 ...}") ` 

이렇게 단순하게 쓰면 될 것 같다. 

그래도 뭐 해결되서 다행이다. 😂

