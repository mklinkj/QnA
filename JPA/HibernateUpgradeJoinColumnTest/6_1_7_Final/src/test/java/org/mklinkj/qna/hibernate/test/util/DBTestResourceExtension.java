package org.mklinkj.qna.hibernate.test.util;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mklinkj.qna.hibernate.common.jpa.EMF;

/**
 * 저자님이 JUnit 4 @Rule을 사용하여 구현한 내용을 JUnit 5의 @ExtendWith로 가능하게 바꿔보자!<br>
 * 코드 내용상 테스트 메서드 하나마다 EMF를 초기화하고 테스트 코드 본문 실행 후 닫는다.<br>
 * <br>
 * 아래 SpringExtension 클래스를 약간 참조했다.<br>
 * <a
 * href="https://github.com/spring-projects/spring-framework/blob/main/spring-test/src/main/java/org/springframework/test/context/junit/jupiter/SpringExtension.java">...</a>
 */
public class DBTestResourceExtension
    implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext context) {}

  @Override
  public void beforeAll(ExtensionContext context) {}

  @Override
  public void afterEach(ExtensionContext context) {
    EMF.close();
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    EMF.init();
  }
}
