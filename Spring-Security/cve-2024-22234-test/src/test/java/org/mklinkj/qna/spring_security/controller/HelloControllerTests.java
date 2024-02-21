package org.mklinkj.qna.spring_security.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.qna.spring_security.config.HelloWebConfig;
import org.mklinkj.qna.spring_security.security.HelloSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(classes = {HelloWebConfig.class, HelloSecurityConfig.class})
@WithMockUser(authorities = {"USER"})
class HelloControllerTests {

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext context;

  @BeforeEach
  void setUp() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(context) //
            .apply(springSecurity())
            .build();
  }

  @Test
  void hello() throws Exception {
    mockMvc
        .perform(get("/hello"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("hello"));
  }

  @Test
  void admin_isFound_errorRedirect() throws Exception {
    mockMvc
        .perform(get("/admin")) //
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/login?error=true"));
  }

  @WithMockUser(authorities = {"ADMIN"})
  @Test
  void admin_isOk() throws Exception {
    mockMvc
        .perform(get("/admin")) //
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("admin"));
  }
}
