# Explicitly use the validation target of InitBinder

> * Pull Request: https://github.com/Apress/spring-5-recipes/pull/4

Hello.

When `@InitBinder` for using the `ReservationValidator` validator in `ReservationFormController` is used in the following way...

```java
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
```



There is no problem in simple MVC projects, but

In ReactiveWeb in Chapter 5 and Spring Security application project in Chapter 7, in addition to verifying `Reservation`, we also want to verify the classes below.

* Spring Webflux
  * `InMemoryWebSessionStore$InMemoryWebSession  `
  * `HttpWebHandlerAdapter`

- Spring Security
  - `UsernamePasswordAuthenticationToken`
  
  - `SecurityContextImpl`
  
    

So, I saw that the related projects in Chapters 5 and 7 were written to return only `true` as follows.

```java
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
//        return Reservation.class.isAssignableFrom(clazz);
    }
```



Rather than doing it this way,

By specifying the form property name to be verified in `@InitBinder` and only verifying `Reservation`, it was possible to use it as follows.

```java
    @InitBinder("reservation")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
```

```java
    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.isAssignableFrom(clazz);
    }
```



I submitted a pull request because I thought it was a better way to do it this way.

Thank you have a good day. 👍





---



# InitBinder의 유효성 검사 대상을 명시적으로 사용합니다.

> * 풀 리퀘스트: https://github.com/Apress/spring-5-recipes/pull/4



안녕하세요.

`ReservationFormController`에서 `ReservationValidator` 검증기 사용을 위한 `@InitBinder`를 아래와 같은 방법으로 사용했을 때..

```java
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
```



단순 MVC 프로젝트에서는 문제가 없지만,

5장의 ReactiveWeb이나  7장의 Spring Security 적용 프로젝트에서는

`Reservation` 의 검증 외에도 다음 클래스까지 검증하려합니다.

* Spring Webflux
  * `InMemoryWebSessionStore$InMemoryWebSession  `
  * `HttpWebHandlerAdapter`

- Spring Security
  - `UsernamePasswordAuthenticationToken`
  
  - `SecurityContextImpl`
  
    

그래서 5장과 7장의 프로젝트에 다음과 같이 true만 반환하게 작성하신 것을 보았습니다.

```java
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
//        return Reservation.class.isAssignableFrom(clazz);
    }
```



이런 방식으로 하는 것 보다는

`@InitBinder`에 검증할 폼 속성을 명시해서, `Reservation` 만 검증하게 하면, 아래와 같이 사용하는 것이 가능했습니다. 

```java
    @InitBinder("reservation")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
```

```java
    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.isAssignableFrom(clazz);
    }
```



이런식으로 하는게 나은 방법 같아서 pull request 제출했습니다.



감사합니다. 좋은하루되세요. 👍