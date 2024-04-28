package org.example;

public class App {
  public String getGreeting() {
    return String.format("Java Home: %s", System.getProperty("java.home"));
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
  }
}
