# 작업 스케쥴링

> 예제
>
> https://spring.io/guides/gs/scheduling-tasks/



### 개발 목표

* Spring의 `@Scheduled` 어노테이션을 사용하여 5초마다 현재 시간을 콘솔 로그로 출력하는 애플리케이션 만들기.



### `awaitility`

예전에 한번 캐시 갱신 시간이 제대로 되는지 확인하고 한번 써보긴 했는데... 이번 예제로 한번 다시 써보자..😄👍

* http://www.awaitility.org/



### SimpleDateFormat을 정적 멤버변수로 써도 괜찮을지?

예전에 어디서 주워 듣기로는 ... SimpleDateFormat 을 필드로 쓰지 말라고 했던 것 같은데... 그런데. 이 예제에서는 Bean으로 생성되고, 예약된 작업이 5초마다 한번씩만 수행되므로 별문제는 없을 것 같지만...  

스레드에 안전한 DateTimeFormatter로 바꿨다.

```
Date formats are not synchronized. It is recommended to create separate format instances for each thread. If multiple threads access a format concurrently, it must be synchronized externally.

Consider using java.time.format.DateTimeFormatter as an immutable and thread-safe alternative.
```

Date Format은 동기화 되지 않음, 각 스레드에 대해 별도의 포멧 인스턴스를 만드는 것이 좋음.

여러 스레드가 포멧에 동식에 엑세스 하는 경우 외부에서 동기화해야함. 

불변 그리고 스레드에 안전한 대한으로 DateTimeFormatter의 사용을 고려할 것.





### 예약 활성화

부트 메인 클래스에 `@EnableScheduling` 붙여줌.





### 실행

bootJar 테스크로 jar를 만들어서 실행해도 되고, booRun으로 실행해도 됨.

```
...
16:58:22.874 [scheduling-1] INFO  o.m.q.s.s.ScheduledTasks - The time is now 16:58:22
16:58:22.883 [main] INFO  o.m.q.s.s.SchedulingTasksApplication - Started SchedulingTasksApplication in 0.898 seconds (process running for 1.193)
16:58:27.881 [scheduling-1] INFO  o.m.q.s.s.ScheduledTasks - The time is now 16:58:27
16:58:32.882 [scheduling-1] INFO  o.m.q.s.s.ScheduledTasks - The time is now 16:58:32
...
```

로그는 위처럼 5처마다 출력됨. (로깅 패턴 설정은 약간 바꿈. `application.yml`)

```yml
logging:
  pattern:
    console: "%clr(%d{HH:mm:ss.SSS}){faint} [%thread] %clr(%-5level) %clr(%logger{36}){cyan} - %msg%n"
```





## 테스트

실행은 잘 되는 것을 알았는데, 테스크를 어떻게 해야할까?

테스트는 그냥 저자님 테스트 코드 봤음 😅

* https://github.com/spring-guides/gs-scheduling-tasks/tree/main/complete/src/test/java/com/example/schedulingtasks



1. 메인은 `ScheduledTasks` 컴포넌트가 정상적으로 생성되었는지만 확인

2. `ScheduledTasks`의 테스트는 awaitility 로 동작확인

   * ScheduledTasks에 verify를 사용할 수 있도록 `@SpyBean`으로 주입받음
   * `awaitility`를 사용해서 10초 동안 적어도 2번 이상 호출되는지 확인

   

   

## 의견

전에도 적어도 얼마 호출했는지 검사하는식으로 했던 것 같은데... 지금 당장은 복잡하게 체크할 일이없지만, 잘 활용하면 도움이 많이 될 것 같다. 😄