`[English]`| [`[한국어]`](README_KR.md)

# Remove Unused HiddenHttpMethodFilter

> The `hello-mvc-security` project of Spring Security Exampels was using `HiddenMethodFilter` unnecessarily, so I submitted a pull request request for its removal.
>
> #### Reason for requesting removal
>
> 1. Nowhere is `HiddenMethodFilter` used.
>    * There is no form submission code using HiddenMethodFilter`
> 2. In the original configuration of the example, `HiddenMethodFilter` is executed after Spring Security FilterChain and may malfunction.
>    * Added example code to reproduce the problem.



## Question

* Issue
  * https://github.com/spring-projects/spring-security-samples/issues/167
* Pull Request
  * https://github.com/spring-projects/spring-security-samples/pull/187



## How to run

* Test + Integration Test

  ```
  gradle clean test integrationTest
  ```

* Run web application

  ``` bash
   gradle clean appRun
  ```

  * Check by accessing `http://localhost:8080`









