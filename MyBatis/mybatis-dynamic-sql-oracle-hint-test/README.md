# MyBatis Dynamic SQL 에서 Oracle Hint 사용하기

* https://github.com/mybatis/mybatis-dynamic-sql/issues/453
* 힌트 관련 언급하신 분 있어서, 테스트 진행해 봄.



### Run

```sh
gradlew clean run --args "ASC or DESC"
```



### Result Example

```
>gradle clean run --args "ASC"

> Task :run
15:20:23.667 [main] INFO  org.mklinkj.mybatis_study.App - select /*+ INDEX_ASC(t_member_test) */ 'dummy', id, pwd, name, joindate from t_member_test
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user01, pwd=1111, name=user01, joinDate=2023-02-01)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user02, pwd=1111, name=user02, joinDate=2023-02-02)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user03, pwd=1111, name=user03, joinDate=2023-02-03)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user04, pwd=1111, name=user04, joinDate=2023-02-04)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user05, pwd=1111, name=user05, joinDate=2023-02-05)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user06, pwd=1111, name=user06, joinDate=2023-02-06)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user07, pwd=1111, name=user07, joinDate=2023-02-07)
15:20:23.842 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user08, pwd=1111, name=user08, joinDate=2023-02-08)
15:20:23.843 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user09, pwd=1111, name=user09, joinDate=2023-02-09)
15:20:23.843 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user10, pwd=1111, name=user10, joinDate=2023-02-10)

BUILD SUCCESSFUL in 4s
5 actionable tasks: 5 executed
>gradle clean run --args "DESC"

> Task :run
15:20:32.182 [main] INFO  org.mklinkj.mybatis_study.App - select /*+ INDEX_DESC(t_member_test) */ 'dummy', id, pwd, name, joindate from t_member_test
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user10, pwd=1111, name=user10, joinDate=2023-02-10)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user09, pwd=1111, name=user09, joinDate=2023-02-09)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user08, pwd=1111, name=user08, joinDate=2023-02-08)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user07, pwd=1111, name=user07, joinDate=2023-02-07)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user06, pwd=1111, name=user06, joinDate=2023-02-06)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user05, pwd=1111, name=user05, joinDate=2023-02-05)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user04, pwd=1111, name=user04, joinDate=2023-02-04)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user03, pwd=1111, name=user03, joinDate=2023-02-03)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user02, pwd=1111, name=user02, joinDate=2023-02-02)
15:20:32.359 [main] INFO  org.mklinkj.mybatis_study.App - Member(id=user01, pwd=1111, name=user01, joinDate=2023-02-01)

BUILD SUCCESSFUL in 4s
5 actionable tasks: 5 executed
>

```

