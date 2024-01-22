 [`[English]`](README.md) | `[한국어]`

# 미사용 HiddenHttpMethodFilter 제거

> Spring Security Exampels 의  `hello-mvc-security` 프로젝트에서 불필요하게 `HiddenMethodFilter`를 사용하고 있어서, 해당 부분 제거에 대한 풀리퀘스트 요청을 제출했다.
>
> ##### 제거 요청을 하게된 이유
>
> 1. `HiddenMethodFilter`가 사용되는 곳이  없음.
>    * `HiddenMethodFilter`를 활용하는 폼 제출 코드가 없다.
> 2. 예제의 원래 설정으로는 `HiddenMethodFilter`가 Spring Security FilterChain 이후에 실행되어 오동작할 경우가 있음
>    * 문제를 재현해볼 수 있는 예제 코드를 추가했음.



## 질문

* 이슈
  * https://github.com/spring-projects/spring-security-samples/issues/167
* 풀 리퀘스트
  * https://github.com/spring-projects/spring-security-samples/pull/187



## 실행 방법

* 일반 테스트 + 통합 테스트

  ```
  gradle clean test integrationTest
  ```

* 웹 애플리케이션 실행

  ``` bash
   gradle clean appRun
  ```

  * `http://localhost:8080` 접속해서 확인