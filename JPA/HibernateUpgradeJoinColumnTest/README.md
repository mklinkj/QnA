### \[English]

# After upgrading to Hibernate 6.2.0.Final, the unique attribute of `@JoinColumn` always behaves as true.



Hello.

There was no problem when using Hibernate 6.1.7.Final version, but after upgrading to Hibernate 6.2.0.Final, the unique property of `@JoinColumn` always operates as true.


For example, two entities are:

* MemberShipCard Entity

  ```java
  @Entity
  public class MembershipCard {
    @Id
    @Column(name = "card_number")
    private String number;
  
    @OneToOne
    @JoinColumn(name = "user_email")
    private Student owner;
  
    private LocalDateTime expiryDate;
  
    private boolean enabled;
  }
  ```

* Student Entity

  ```java
  @Entity
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  public class Student {
    @EqualsAndHashCode.Include @Id private String email;
    private String name;
  
    private LocalDateTime createDate;
  }
  ```

  

### Table creation SQL for MemberShipCard entity in Hibernate 6.1.7.Final

```sql
    create table membership_card (
        card_number varchar(255) not null,
        enabled boolean not null,
        expiry_date timestamp(6),
        user_email varchar(255),
        primary key (card_number)
    )
```



### Table creation SQL for MemberShipCard entity in Hibernate 6.2.0.Final

```sql
    create table membership_card (
        enabled boolean not null,
        expiry_date timestamp(6),
        card_number varchar(255) not null,
        user_email varchar(255) unique,  /* <-- Setting UNIUQE */
        primary key (card_number)
    )
```



Please inquire if there is any problem with the above operation.



* Example Project
  * https://github.com/mklinkj/QnA/tree/master/JPA/HibernateUpgradeJoinColumnTest
  * An example has been created in HSQLDB memory mode so you can check it right away

## 

## Result

* I made a mistake while testing.
* It is `@OneToOne`, but the initial data is put in like many-to-one.
* In 6.1.7, even if set to `@OneToOne`, the unique setting for the join column is not automatically set when the initial table is created.
* In 6.2.0, if set to `@OneToOne`, unique is automatically and forcefully set for the join column when the initial table is created.
* In the end, it can be seen that the behavior of 6.2.0 has been further improved.
* When I post an issue, I'll do some more checking before posting. 😅



---



### \[한국어]

# Hibernate 6.2.0.Final로 업그레이드 이후 @JoinColumn 의 unique 속성이 항상 ture로 동작합니다.



안녕하세요.

Hibernate 6.1.7.Final 버전 사용할 때는 문제가 없었는데, Hibernate 6.2.0.Final로 업그레이드 이후 @JoinColumn 의 unique 속성이 항상 ture로 동작합니다.



예를 들어서, 2개의 엔티티가 다음과 같습니다.

* MemberShipCard
  ```java
  @Entity
  public class MembershipCard {
    @Id
    @Column(name = "card_number")
    private String number;
  
    @OneToOne
    @JoinColumn(name = "user_email")
    private Student owner;
  
    private LocalDateTime expiryDate;
  
    private boolean enabled;
  }
  ```

* Student

  ```java
  @Entity
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  public class Student {
    @EqualsAndHashCode.Include @Id private String email;
    private String name;
  
    private LocalDateTime createDate;
  }
  ```

  

### Hibernate 6.1.7.Final 의 MemberShipCard 엔티티 테이블 생성 SQL

```sql
    create table membership_card (
        card_number varchar(255) not null,
        enabled boolean not null,
        expiry_date timestamp(6),
        user_email varchar(255),
        primary key (card_number)
    )
```



### Hibernate 6.2.0.Final의  MemberShipCard 엔티티 테이블 생성 SQL

```sql
    create table membership_card (
        enabled boolean not null,
        expiry_date timestamp(6),
        card_number varchar(255) not null,
        user_email varchar(255) unique,  /* <-- Setting UNIUQE */
        primary key (card_number)
    )
```



위의 동작과 관련해서 문제가 있는 것이 아닌지 문의드립니다.



* 예제 프로젝트
  * https://github.com/mklinkj/QnA/tree/master/JPA/HibernateUpgradeJoinColumnTest
  * HSQLDB 메모리 모드로 예제를 작성해서 바로 확인하실 수 있습니다.



### 이슈 보고

* [[HHH-16448\] After upgrading to Hibernate 6.2.0.Final, the unique attribute of `@JoinColumn` always behaves as true. - Hibernate JIRA (atlassian.net)](https://hibernate.atlassian.net/browse/HHH-16448)



## 결과

* 내가 테스트 하는데 실수가 있었음.
* @OneToOne인데 초기 데이터를 다대일 처럼 넣었다.
* 6.1.7에서는 `@OneToOne`으로 설정을 해도 초기 테이블 생성시 조인 컬럼에 unique 설정이 자동으로 설정되지 않음.
* 6.2.0에서는 `@OneToOne`으로 설정을 하면 초기 테이블 생성시 조인 컬럼에 unique 가 강제 및 자동으로 설정됨.
* 결국 6.2.0의 동작이 더 개선된 것으로 볼 수 있음.
* 이슈올릴 때, 좀 더 확인을 하고 올리도록 하자 😅
