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
package example;

import example.pages.DeletePage;
import example.pages.HomePage;
import example.pages.LoginPage;
import example.pages.PutPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Test the Hello World application.
 *
 * @author Michael Simons
 */

/*
 Because Tomcat must be started by Gretty before running the test class,
 the test may fail under normal IDE default settings.

 If you run the integrationTest task with the gradle command in the terminal,
 the test will succeed.

 $ gradle clean integrationTest
*/
class HelloWorldTests {

  private WebDriver driver;

  private int port;

  @BeforeEach
  void setup() {
    this.port = Integer.parseInt(System.getProperty("app.httpPort"));
    this.driver = new HtmlUnitDriver();
  }

  @AfterEach
  void tearDown() {
    this.driver.quit();
  }

  @Test
  void accessHomePageWithUnauthenticatedUserSendsToLoginPage() {
    final LoginPage loginPage = HomePage.to(this.driver, this.port);
    loginPage.assertAt();
  }

  @Test
  void authenticatedUserIsSentToOriginalPage() {
    // @formatter:off
    final HomePage homePage =
        HomePage.to(this.driver, this.port) //
            .loginForm()
            .username("user")
            .password("user")
            .submit();
    // @formatter:on
    homePage.assertAt();
  }

  @Test
  void authenticatedUserLogsOut() {
    // @formatter:off
    LoginPage loginPage =
        HomePage.to(this.driver, this.port)
            .loginForm()
            .username("user")
            .password("user")
            .submit()
            .logout();
    // @formatter:on
    loginPage.assertAt();

    loginPage = HomePage.to(this.driver, this.port);
    loginPage.assertAt();
  }

  @Test
  void authenticatedUserPut() {
    // @formatter:off
    PutPage putPage =
        HomePage.to(this.driver, this.port)
            .loginForm()
            .username("user")
            .password("user")
            .submit()
            .put();
    // @formatter:on
    putPage.assertAt();
  }

  @Test
  void adminUserDelete() {
    // @formatter:off
    DeletePage delete =
        HomePage.to(this.driver, this.port)
            .loginForm()
            .username("admin")
            .password("admin")
            .submit()
            .delete();
    // @formatter:on
    delete.assertAt();
  }

  @Test
  void normalUserDelete() {
    // @formatter:off
    DeletePage deletePage =
        HomePage.to(this.driver, this.port)
            .loginForm()
            .username("user")
            .password("user")
            .submit()
            .delete();
    // @formatter:on
    deletePage.assert403();
  }
}
