package org.mklinkj.mybatis_study;

import org.junit.jupiter.api.Test;
import org.mklinkj.mybatis_study.App.OrderType;

class AppTest {
  @Test
  void run() {
    App.main(new String[] {OrderType.DESC.name()});
  }
}
