package org.mklinkj.qna.test;

import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.stream.LongStream;
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
        LongStream.rangeClosed(1, TOTAL_EMPLOYEES)
            .mapToObj(
                i ->
                    new EmployeeVO(
                        "empId_%s".formatted(i), getLocation((int) i), getRegisterDate(i)))
            .toList();

    jdbcTemplate.batchUpdate(
        "INSERT INTO t_employee (name, location, register_date) VALUES (?, ?, ?)",
        new BatchPreparedStatementSetter() {
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            ps.setString(1, empList.get(i).getName());
            ps.setString(2, empList.get(i).getLocation());
            // JDBC 4.2 미만 사용시 ...
            // ps.setDate(3, java.sql.Date.valueOf(empList.get(i).getRegisterDate()));
            // JDBC 4.2 이상을 사용시 ...
            ps.setObject(3, empList.get(i).getRegisterDate());
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

  private static final LocalDate BASE_REGISTER_DATE = LocalDate.of(2023, 8, 31);

  private static LocalDate getRegisterDate(long i) {
    return BASE_REGISTER_DATE.plusDays(i % 10);
  }

  public static DriverManagerDataSource dataSource() {
    val dbProps = getDBProperties();
    val ds = new DriverManagerDataSource(dbProps.getProperty("jdbc.url"));
    ds.setDriverClassName(dbProps.getProperty("jdbc.driver"));
    ds.setUsername(dbProps.getProperty("jdbc.username"));
    ds.setPassword(dbProps.getProperty("jdbc.password"));
    return ds;
  }
}
