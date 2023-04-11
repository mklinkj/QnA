package org.mklinkj.qna.hibernate.test.util;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mklinkj.qna.hibernate.common.jpa.EMF;

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
