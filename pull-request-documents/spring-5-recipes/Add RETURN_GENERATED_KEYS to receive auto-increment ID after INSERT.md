# Add RETURN_GENERATED_KEYS to receive auto-increment ID after INSERT

> 예제 코드에서 INSERT를 실행할 때, Autoincrement 값을 가져오지 못하길레, 문제 수정하고 PR 제출하였다.
>
> * https://github.com/Apress/spring-5-recipes/pull/8



hello.

In the example code, there is a problem where the auto-increment value cannot be retrieved after INSERT and is returned as null.

* https://github.com/Apress/spring-5-recipes/blob/5fc7cc9f3e97b2486541caa1ae8bf5a95c03152b/spring-recipes-4th/appendix-b/recipe_b_6_i/src/main/java/com/apress/springrecipes/caching/JdbcCustomerRepository.java#L46-L54

So, I added Statement.RETURN_GENERATED_KEYS as the second parameter when running prepareStatement().

I also corrected what appeared to be simple typos.

* https://github.com/Apress/spring-5-recipes/blob/5fc7cc9f3e97b2486541caa1ae8bf5a95c03152b/spring-recipes-4th/appendix-b/recipe_b_6_i/src/main/resources/schema.sql#L3
  * Remove unnecessary commas at the end of column properties.
* https://github.com/Apress/spring-5-recipes/blob/5fc7cc9f3e97b2486541caa1ae8bf5a95c03152b/spring-recipes-4th/appendix-b/recipe_b_6_i/src/main/java/com/apress/springrecipes/caching/JdbcCustomerRepository.java#L46
  * Remove semicolon at the end of SQL statement.

thank you have a good day. 👍