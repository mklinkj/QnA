package org.mklinkj.qna.spring_security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = {"USER"})
class HelloControllerTests {

  @Autowired private MockMvc mockMvc;

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
        .andExpect(redirectedUrl("/login?error=accessDenied"));
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
