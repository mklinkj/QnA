/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;

/** The put page. */
public class DeletePage {

  private final WebDriver webDriver;

  public DeletePage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public DeletePage assertAt() {
    assertThat(this.webDriver.getTitle()).isEqualTo("Delete Page");
    return this;
  }

  public void assert403() {
    assertThat(this.webDriver.getTitle())
        .contains("HTTP Status 403"); // Title message dependent on Tomcat 10
  }
}
