package org.mklinkj.qna.jsp_time_tags.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.qna.jsp_time_tags.config.QnaConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitWebConfig(classes = {QnaConfiguration.class})
class WelcomeControllerTest {
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new WelcomeController()).build();
  }

  @Test
  void testWelcome() throws Exception {
    mockMvc
        .perform(get("/welcome/")) //
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("now"))
        .andExpect(view().name("welcome"));
  }
}
