package org.mklinkj.qna.spring_guide.relational_data_access;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class RelationalDataAccessApplicationTests {
  /** 테스트시 System.in의 내용을 변경하므로 일단 원래내용을 백업해둠. */
  private static final InputStream ORIGIN_STDIN = System.in;

  protected static final String ENTER = System.lineSeparator();

  @AfterAll
  static void AfterAll() {
    System.setIn(ORIGIN_STDIN);
  }

  protected static void setKeyboardInput(String keyboardInput) {
    System.setIn(new ByteArrayInputStream((keyboardInput.getBytes())));
  }

  @Test
  void contextLoads() {
    setKeyboardInput("y" + ENTER);
    RelationalDataAccessApplication.main(new String[] {});
  }
}
