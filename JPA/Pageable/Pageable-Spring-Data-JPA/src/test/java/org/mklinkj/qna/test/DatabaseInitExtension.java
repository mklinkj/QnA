package org.mklinkj.qna.test;

import javax.sql.DataSource;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/** DB 스키마와 데이터 입력 초기화 작업을 하는 JUnit 확장 */
public class DatabaseInitExtension
    implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {}

  @Override
  public void afterEach(ExtensionContext context) {}

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    DataSource dataSource = DBUtil.dataSource();
    DBUtil.runInitScript(dataSource);
    DBUtil.insertData(dataSource);
  }

  @Override
  public void beforeEach(ExtensionContext context) {}
}
