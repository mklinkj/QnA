# javax.el의 디펜던시 여부에 따라 @NotNull의 쿼리 동작이 달라지는 현상

> * https://github.com/ctudose/java-persistence-spring-data-hibernate/issues/7
>   * 저자님께 질문올림 😊



main 환경과 test 환경의 실행동작의 일관성을 위해, Hibernate Validtor를 사용한 환경에서는 javax.el 을 디펜던시 하는 것이 낫지 않은지 문의드립니다.

12의 장의 eagerjoin 예제를 예로 들면... 다음과 같이 `@NotNull`이 붙어있습니다.

* Item Entity의 seller 필드

  ```java
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER) // Actually the default
  private User seller;
  ```

* test의 EagerJoin - fetchEagerJoin() 

  ```java
  Item item = em.find(Item.class, ITEM_ID);
  // select i.*, u.*, b.*
  //  from ITEM i
  //   left outer join USERS u on u.ID = i.SELLER_ID
  //   left outer join BID b on b.ITEM_ID = i.ID
  //  where i.ID = ?
  ```

  실행되는 쿼리 내용이 주석에 포함되어있는데, seller에 대해서 Left JOIN으로 실행되었다고 나와 있습니다.



그러나 pom.xml에 다음 내용을 포함한 뒤에 실행하면..

```xml
        <dependency>
          <groupId>org.glassfish</groupId>
          <artifactId>javax.el</artifactId>
          <version>3.0.0</version>
          <scope>test</scope>
        </dependency>
```

다음과 같은 실행 결과가 됩니다.

```sql
Hibernate: 
    select
        item0_.id as id1_1_0_,
        item0_.auctionEnd as auctione2_1_0_,
        item0_.name as name3_1_0_,
        item0_.seller_id as seller_i4_1_0_,
        bids1_.item_id as item_id4_0_1_,
        bids1_.id as id1_0_1_,
        bids1_.id as id1_0_2_,
        bids1_.amount as amount2_0_2_,
        bids1_.bidder_id as bidder_i3_0_2_,
        bids1_.item_id as item_id4_0_2_,
        user2_.id as id1_2_3_,
        user2_.username as username2_2_3_ 
    from
        Item item0_ 
    left outer join
        Bid bids1_ 
            on item0_.id=bids1_.item_id 
    inner join  -- // 💡Seller에 대해 INNER JOIN으로 처리됨.
        USERS user2_ 
            on item0_.seller_id=user2_.id 
    where
        item0_.id=?
```

이 경우는 `@NotNull`이 정상 처리되어서, Item에는 반드시 User가 있어야하는 필수조건이 되어 INNER JOIN 쿼리로 실행이 되었습니다.



보통 Main 환경에서는 Tomcat 같은 WAS에 javax.el이 포함되어있으므로, 해당 부분이 INNER JOIN으로 실행 될 테니,

제 의견으로는, 

프로젝트에서 Hibernate Validator 및 @NotNull 같은 어노테이션을 사용한다면, 테스트 환경에서도 `javax.el`을 디펜던시해서 Main환경과 동일한 동작을 보장하는게 낫지 않을까 싶어서 문의드렸습니다.

감사합니다. 좋은하루되세요 👍







