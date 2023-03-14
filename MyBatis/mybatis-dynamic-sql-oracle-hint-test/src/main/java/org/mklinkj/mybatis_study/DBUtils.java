package org.mklinkj.mybatis_study;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBUtils {
  public static void runInitSqlScript(String sqlFile, DataSource dataSource) {
    try (Connection connection = dataSource.getConnection()) {
      ScriptUtils.executeSqlScript(
          connection,
          new EncodedResource(new ClassPathResource(sqlFile), StandardCharsets.UTF_8),
          false, // continueOnError
          true, // ignoreFailedDrops
          ScriptUtils.DEFAULT_COMMENT_PREFIX,
          ScriptUtils.DEFAULT_STATEMENT_SEPARATOR,
          ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER,
          ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public static void resetDB(DataSource dataSource) {
    runInitSqlScript("/sql/oracle/init-sql.sql", dataSource);
  }
}
