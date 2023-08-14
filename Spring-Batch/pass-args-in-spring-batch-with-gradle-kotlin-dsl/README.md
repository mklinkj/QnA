# Gradle Kotlin DSL + Spring Boot Batch 프로젝트에서 bootRun으로 Job 파라미터 전달하는 방법 질문?

> `java -jar` 로 실행해서 전달하는 것은 잘 되지만, `gradle bootRun`을 사용해서 Job 파라미터 여러개를 전달하는 것은 너무 안되서..🥲🥲🥲 예제 프로젝트를 만들어서  Stackoverflow에 물어보기로 했다.



##  문제

아래 명령을 실행했을 때..

```sh
gradle clean bootRun --args='param1=1,param2=2'
```

`param1=1`, `param2=2`  둘 다 Job 파라미터로 전달되길 기대하는데, `param1=1` 만 전달됩니다.

배치 어플리케이션에서는 param1, param2에 모두에 대해, 필수 파라미터 값으로 설정하였기 때문에,  다음 예외가 발생합니다.

```
Caused by: org.springframework.batch.core.JobParametersInvalidException: The JobParameters do not contain required keys: [param2]
```



그러나 직접  Jar로 실행했을 때는, 문제가 없었습니다.

```sh
gradle clean bootJar
java -jar .\build\libs\pass-args-in-spring-batch-with-gradle-kotlin-dsl-0.0.1-SNAPSHOT.jar param1=1 param2=2
```

* 실행 로그

  ```
  21:13:58.776 [main] INFO  o.s.b.c.l.support.SimpleJobLauncher - Job: [SimpleJob: [name=JobParamTestJob]] completed with the following parameters: [{param1=1, param2=2}] and the following status: [COMPLETED] in 39ms
  ```

   이 경우 두 매개 변수가 모두 잘 전달됩니다.



---

#### Stack Overflow 에 질문 올림

* https://stackoverflow.com/questions/76899301/passing-multiple-job-parameters-when-running-a-bootrun-task

#### 질문글

I'm studying the Spring Boot Batch.

But when I tried to pass the batch parameters in the bootRun Task, it didn't work.

#### Example project

- https://github.com/mklinkj/QnA/tree/master/Spring-Batch/pass-args-in-spring-batch-with-gradle-kotlin-dsl

When I ran the command below...

```
gradle clean bootRun --args='param1=1,param2=2'
```

I expect both `param1=1` and `param2=2` to be passed as Job parameters, but only `param1=1` is passed.

In the batch application, the following exception is thrown because you set the required parameter values for both param1 and param2.

- Exception Message

  ```
  Caused by: org.springframework.batch.core.JobParametersInvalidException: The JobParameters do not contain required keys: [param2]
  ```

However, when I ran it directly as a jar, there was no problem.

```
gradle clean bootJar
java -jar .\build\libs\pass-args-in-spring-batch-with-gradle-kotlin-dsl-0.0.1-SNAPSHOT.jar param1=1 param2=2
```

- Success when running as a Jar

  ```
  21:13:58.776 [main] INFO  o.s.b.c.l.support.SimpleJobLauncher - Job: [SimpleJob: [name=JobParamTestJob]] completed with the following parameters: [{param1=1, param2=2}] and the following status: [COMPLETED] in 39ms
  ```

  - In this case, both parameters are passed well.

Is there anything else I need to add in the `build.gradle.kts` file below?

- https://github.com/mklinkj/QnA/blob/master/Spring-Batch/pass-args-in-spring-batch-with-gradle-kotlin-dsl/build.gradle.kts

Thank you. Have a nice day.



---

## 결과

* ... 

