package org.mklinkj.qna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class MainTests {
  @Autowired private MockMvc mvc;

  @Test
  @DisplayName("인증하여 /hello 엔드포인트 호출")
  @WithUserDetails("mklinkj")
  void testCallingHelloWithAuthentication() throws Exception {
    mvc.perform(get("/hello")) //
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("인증하여 /hello/ 엔드포인트 호출")
  @WithUserDetails("mklinkj")
  void testCallingHelloVariationWithAuthentication() throws Exception {
    mvc.perform(get("/hello/")) //
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("인증 없이 /hello 엔드포인트 호출")
  void testCallingHelloWithoutAuthentication() throws Exception {
    mvc.perform(get("/hello")) //
        .andExpect(status().isUnauthorized());
  }

  @Test
  @DisplayName("인증 없이 /hello/ 엔드포인트 호출")
  void testCallingHelloVariationWithoutAuthentication() throws Exception {
    mvc.perform(get("/hello/")) //
        .andExpect(status().isUnauthorized());
  }
}
