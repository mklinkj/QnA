package org.mklinkj.mybatis_study;

import static org.mklinkj.mybatis_study.table.MemberDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mklinkj.mybatis_study.mapper.MemberMapper;
import org.mybatis.dynamic.sql.Constant;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.DefaultSelectStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/** MyBatis Dynamic SQL : Oracle Hint 쿼리 테스트 */
@Slf4j
@Component
public class App {

  enum OrderType {
    ASC,
    DESC
  }

  void run(OrderType orderType) {
    // 내가 한것
    Constant<String> hint =
        Constant.of(String.format("/*+ INDEX_%s(t_member_test) */ 'dummy'", orderType.name()));

    SelectStatementProvider selectStatementProvider =
        select(hint, id, pwd, name, joinDate)
            .from(member)
            .build()
            .render(RenderingStrategies.MYBATIS3);

    log.info(selectStatementProvider.getSelectStatement());
    // log print:
    // select /*+ INDEX_DESC(t_member_test) */ 'dummy', id, pwd, name, joindate from t_member_test

    List<Member> members = mapper.selectMany(selectStatementProvider);

    members.forEach(m -> log.info(m.toString()));

    // 빙 챗봇이 한 것, 의견이 새롭다. 😄👍
    SelectStatementProvider bingAiCode =
        select(id, pwd, name, joinDate).from(member).build().render(RenderingStrategies.MYBATIS3);

    String sql =
        bingAiCode
            .getSelectStatement()
            .replaceFirst(
                "(?i)select",
                String.format("select /*+ INDEX_%s(t_member_test) */", orderType.name()));
    log.info("### bing 챗봇의 sql: {}", sql);
    SelectStatementProvider newSelectStatement =
        new DefaultSelectStatementProvider.Builder().withSelectStatement(sql).build();

    members = mapper.selectMany(newSelectStatement);

    members.forEach(m -> log.info(m.toString()));
  }

  private final MemberMapper mapper;

  public App(MemberMapper mapper) {
    this.mapper = mapper;
  }

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class)) {
      context.getBean("app", App.class).run(OrderType.valueOf(args[0]));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Configuration
  @MapperScan(basePackages = {"org.mklinkj.mybatis_study.mapper"})
  @ComponentScan("org.mklinkj.mybatis_study")
  public static class AppConfig {

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(dataSource);
      return sqlSessionFactoryBean.getObject();
    }

    @Bean
    DataSource dataSource() {
      DriverManagerDataSource ds =
          new DriverManagerDataSource(
              "jdbc:oracle:thin:@localvmdb.oracle_xe_18c:1521:XE", //
              "scott", //
              "tiger");
      DBUtils.resetDB(ds);
      return ds;
    }
  }
}
