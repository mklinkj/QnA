package org.mklinkj.qna.spring_guide.relational_data_access;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class RelationalDataAccessApplication {

  @Bean
  ApplicationRunner runner(JdbcTemplate jdbcTemplate) {
    return runner -> {
      LOGGER.info("테이블 생성 ... ");

      jdbcTemplate.execute("DROP TABLE customers IF EXISTS");

      jdbcTemplate.execute(
          """
              CREATE TABLE customers(
                id SERIAL,
                first_name VARCHAR(255),
                last_name VARCHAR(255)
              )
              """);

      // 이름 들을 이름 / 성 배열로 분할
      List<Object[]> splitUpNames =
          Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
              .map(name -> name.split(" "))
              .collect(Collectors.toList());

      splitUpNames.forEach(
          name -> LOGGER.info(String.format("다음의 고객 레코드 삽입 중... %s %s", name[0], name[1])));

      // Uses JdbcTemplate's batchUpdate operation to bulk load data
      jdbcTemplate.batchUpdate(
          """
              INSERT INTO customers(first_name, last_name)
              VALUES (?,?)
              """,
          splitUpNames);

      LOGGER.info("이름이 Josh인 고객 쿼리...:");

      jdbcTemplate
          .query(
              """
                        SELECT id, first_name, last_name
                          FROM customers
                         WHERE first_name = ?
                         """,
              (rs, rowNum) ->
                  new Customer(
                      rs.getLong("id"), //
                      rs.getString("first_name"),
                      rs.getString("last_name")),
              "Josh")
          .forEach(customer -> LOGGER.info(customer.toString()));
    };
  }

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(RelationalDataAccessApplication.class, args);

    try (Scanner sc = new Scanner(System.in)) {
      String yes = "n";
      System.out.println("[알림] 프로그램 종료하시려면 y키 눌러주세요.");
      while (!"y".equals(yes)) {
        yes = sc.next();
      }
      SpringApplication.exit(context, () -> 0);
    }
  }
}
