# A phenomenon in which the query behavior of `@NotNull` varies depending on whether `javax.el` is a dependency.

> https://github.com/ctudose/java-persistence-spring-data-hibernate/issues/7

For consistency of execution behavior between the main environment and the test environment, I would like to ask whether it would be better to add `javax.el` to the test dependency in an environment using Hibernate Validtor.

For example, in the **eagerjoin** example in Chapter 12, `@NotNull` is appended as follows:

* Item Entity - seller field

  ```java
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER) // Actually the default
  private User seller;
  ```

* EagerJoin in test - fetchEagerJoin()

  ```java
  Item item = em.find(Item.class, ITEM_ID);
  // select i.*, u.*, b.*
  //  from ITEM i
  //   left outer join USERS u on u.ID = i.SELLER_ID
  //   left outer join BID b on b.ITEM_ID = i.ID
  //  where i.ID = ?
  ```

  The content of the executed query is included in the comment, and in relation to seller, it says that it was executed as a LEFT JOIN.



However, if you run the test after including the following content in pom.xml...

```xml
<dependency>
  <groupId>org.glassfish</groupId>
  <artifactId>javax.el</artifactId>
  <version>3.0.0</version>
  <scope>test</scope>
</dependency>
```

The execution result is as follows:

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
    inner join  -- // 💡Query executed with INNER JOIN for Seller
        USERS user2_ 
            on item0_.seller_id=user2_.id 
    where
        item0_.id=?
```

In this case, `@NotNull` was processed normally and the INNER JOIN query was executed with the mandatory condition that the Item must contain a User.

Generally, in the main environment, `javax.el` is included in WAS such as Tomcat, so that part is likely to be executed as INNER JOIN.

I asked because I thought that if your project uses annotations such as Hibernate Validator and @NotNull, it would be better to add javax.el to the test dependencies in the test environment to ensure the same behavior as the main environment.

thank you have a good day. 👍

