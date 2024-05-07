package org.example;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) throws Exception {
    try {
      Class<?> classLoaderClass = Class.forName("java.lang.ClassLoader");
      Method defineClassMethod =
          classLoaderClass.getDeclaredMethod(
              "defineClass", String.class, byte[].class, int.class, int.class);
      defineClassMethod.setAccessible(true); // 예외 발생 지점
      LOGGER.info("예외 없이 종료 😄");
    } catch (Exception e) {
      LOGGER.error("예외 발생 😭", e);
      throw e;
    }
  }
}
