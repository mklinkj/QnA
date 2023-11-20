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
