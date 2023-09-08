package org.mklinkj.qna.test;

import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBUtil {

  public static Properties getDBProperties() {
    Properties properties = new Properties();

    try (InputStream reader = Resources.getResourceAsStream("database.properties")) {
      properties.load(reader);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return properties;
  }

  public static void runInitScript(DataSource dataSource) throws SQLException {
    ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
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
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    IntStream.rangeClosed(1, TOTAL_EMPLOYEES)
        .forEach(
            i -> {
              String name = "empId_%s".formatted(i);
              jdbcTemplate.update("INSERT INTO t_employee (name) VALUES (?)", name);
            });
  }

  public static DriverManagerDataSource dataSource() {
    Properties dbProps = getDBProperties();
    DriverManagerDataSource ds = new DriverManagerDataSource(dbProps.getProperty("jdbc.url"));
    ds.setDriverClassName(dbProps.getProperty("jdbc.driver"));
    ds.setUsername(dbProps.getProperty("jdbc.username"));
    ds.setPassword(dbProps.getProperty("jdbc.password"));
    return ds;
  }
}
