# LocalDateTime serialization issue in Jackson 2.16.0

> There was no problem specifying the date format up to version 2.15.3, 
>
> but it seems like it’s not applied in version 2.16.0



### ObjectMapper Setting

```java
    ObjectMapper mapper =
        new ObjectMapper()
            .registerModule(
                new JavaTimeModule()
                    .addSerializer(
                        LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
```



### Run in Jackson 2.15.3 

```
$ gradlew clean run

> Task :run
memberJson: {"name":"user00","registerDate":"12/25/2023"}

BUILD SUCCESSFUL in 2s
5 actionable tasks: 5 executed
$ 
```



### Run in Jackson 2.16.0 

```
$ gradlew clean run

> Task :run
memberJson: {"name":"user00","registerDate":[2023,12,25,1,2,3]}

BUILD SUCCESSFUL in 2s
5 actionable tasks: 5 executed
$ 
```



* The date format set in LocalDateTimeSerializer is not applied in the 2.16.0







---

# Jackson 2.16.0 환경에서 LocalDateTime 직렬화 문제

> 2.15.3 버전까지는 날짜 포멧을 지정에 문제가 없었는데,
>
> 2.16.0 버전에서는 적용이 안되는 것 처럼 보인다.



### ObjectMapper 설정

```java
ObjectMapper mapper =
    new ObjectMapper()
        .registerModule(
            new JavaTimeModule()
                .addSerializer(
                    LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
```



### Jackson 2.15.3 환경

```
$ gradlew clean run

> Task :run
memberJson: {"name":"user00","registerDate":"12/25/2023"}

BUILD SUCCESSFUL in 2s
5 actionable tasks: 5 executed
$ 
```



### Jackson 2.16.0 환경

```
$ gradlew clean run

> Task :run
memberJson: {"name":"user00","registerDate":[2023,12,25,1,2,3]}

BUILD SUCCESSFUL in 2s
5 actionable tasks: 5 executed
$ 
```

* 2.16.0 환경에서는 LocalDateTimeSerializer 에 설정한 날짜 포멧이 적용되지 않음

