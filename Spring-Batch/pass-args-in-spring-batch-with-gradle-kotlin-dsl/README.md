# Gradle Kotlin DSL + Spring Boot Batch 프로젝트에서 bootRun으로 Job 파라미터 전달하는 방법 질문?

> `java -jar` 로 실행해서 전달하는 것은 잘 되지만, `gradle bootRun`을 사용해서 Job 파라미터 여러개를 전달하는 것은 너무 안되서..🥲🥲🥲 예제 프로젝트를 만들어서  Stackoverflow에 물어보기로 했다.



##  문제

아래 명령을 실행했을 때..

```sh
gradle clean bootRun --args='param1=1,param2=2'
```

`param1=1`, `param2=2`  둘 다 Job 파라미터로 전달되길 기대하는데, `param1=1` 만 전달됨.

param1, param2에 대해, 필수 파라미터 값으로 설정하였기 때문에,  다음 예외가 발생함.

```
Caused by: org.springframework.batch.core.JobParametersInvalidException: The JobParameters do not contain required keys: [param2]
```



직접  Jar로 실행하면 문제가 없음.

```
gradle clean bootJar
java -jar .\build\libs\pass-args-in-spring-batch-with-gradle-kotlin-dsl-0.0.1-SNAPSHOT.jar param1=1 param2=2
```

* 실행 로그

  ```
  21:13:58.776 [main] INFO  o.s.b.c.l.support.SimpleJobLauncher - Job: [SimpleJob: [name=JobParamTestJob]] completed with the following parameters: [{param1=1, param2=2}] and the following status: [COMPLETED] in 39ms
  ```

  파라미터 두개 모두 잘 전달된 것이 확인됨.



---



