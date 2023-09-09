package org.mklinkj.qna.test;

import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mklinkj.qna.spring_data_jpa_pageable.vo.EmployeeVO;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBUtil {

  public static Properties getDBProperties() {
    val properties = new Properties();

    try (InputStream reader = Resources.getResourceAsStream("database.properties")) {
      properties.load(reader);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return properties;
  }

  public static void runInitScript(DataSource dataSource) throws SQLException {
    val scriptRunner = new ScriptRunner(dataSource.getConnection());
    scriptRunner.setAutoCommit(true);
    try {
      Resources.setCharset(StandardCharsets.UTF_8);
      scriptRunner.runScript(Resources.getResourceAsReader("sql/create_data.sql"));

    } catch (IOException e) {
      LOGGER.error("script runner fail. {}", e.getMessage(), e);
      throw new IllegalStateException("create-script.sql execute fail.");
    }
  }

  public static void insertData(DataSource dataSource) {
    val jdbcTemplate = new JdbcTemplate(dataSource);

    val empList =
        IntStream.rangeClosed(1, TOTAL_EMPLOYEES)
            .mapToObj(
                i -> {
                  String name = "empId_%s".formatted(i);
                  String location = getLocation(i);
                  return new EmployeeVO(name, location);
                })
            .toList();

    jdbcTemplate.batchUpdate(
        "INSERT INTO t_employee (name, location) VALUES (?, ?)",
        new BatchPreparedStatementSetter() {
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            ps.setString(1, empList.get(i).getName());
            ps.setString(2, empList.get(i).getLocation());
          }

          public int getBatchSize() {
            return empList.size();
          }
        });
  }

  private static String getLocation(int i) {
    return locations.get((i - 1) % locations.size());
  }

  private static final List<String> locations =
      List.of("서울", "경기", "인천", "강원", "충북", "충남", "경북", "경남", "전북", "전남");

  public static DriverManagerDataSource dataSource() {
    val dbProps = getDBProperties();
    val ds = new DriverManagerDataSource(dbProps.getProperty("jdbc.url"));
    ds.setDriverClassName(dbProps.getProperty("jdbc.driver"));
    ds.setUsername(dbProps.getProperty("jdbc.username"));
    ds.setPassword(dbProps.getProperty("jdbc.password"));
    return ds;
  }
}
