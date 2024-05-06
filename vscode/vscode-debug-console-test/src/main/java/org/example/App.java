package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      log.info("log count: {}", i);
    }
  }
}
