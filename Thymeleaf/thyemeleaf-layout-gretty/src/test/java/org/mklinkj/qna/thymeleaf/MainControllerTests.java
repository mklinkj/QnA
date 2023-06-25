package org.mklinkj.qna.thymeleaf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(
    locations = {
      "classpath:config/root-context.xml",
      "file:src/main/webapp/WEB-INF/action-servlet.xml"
    })
class MainControllerTests {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  public void beforeEach() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  void main() throws Exception {
    mockMvc
        .perform(get("/main")) //
        .andDo(print())
        .andExpect(status().isOk());
  }
}
