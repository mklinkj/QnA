package org.mklinkj.qna.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public static Connection getConnection() {
    Properties dbProps = getDBProperties();

    Connection conn;
    try {
      Class.forName(dbProps.getProperty("jdbc.driver"));
      String url = dbProps.getProperty("jdbc.url");
      conn =
          DriverManager.getConnection(
              url, dbProps.getProperty("jdbc.username"), dbProps.getProperty("jdbc.password"));
    } catch (Exception e) {
      LOGGER.error("connection create fail. {}", e.getMessage(), e);
      throw new IllegalStateException("connection create fail.");
    }
    return conn;
  }

  public static void runInitScript(Connection conn) {
    ScriptRunner scriptRunner = new ScriptRunner(conn);
    scriptRunner.setAutoCommit(true);
    try {
      Resources.setCharset(StandardCharsets.UTF_8);
      scriptRunner.runScript(Resources.getResourceAsReader("sql/create_data.sql"));

    } catch (IOException e) {
      LOGGER.error("script runner fail. {}", e.getMessage(), e);
      throw new IllegalStateException("create-script.sql execute fail.");
    }
  }
}
