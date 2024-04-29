package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AppTests {
  @Test
  void appHasAGreeting() {
    App classUnderTest = new App();
    assertThat(classUnderTest.getGreeting()) //
        .as("app should have a greeting")
        .isNotNull();
  }
}
