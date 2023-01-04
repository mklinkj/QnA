package org.mklinkj.qna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
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

  /*
     TODO: MVC 매처라면 아래 URL로도 성공해야하는데, Spring Boot 3.0.1 + (Spring Security 6.0.1)에서 실패한다.
     TODO: Since I used the MVC matcher, it should succeed with the URL below, but fails with Spring Boot 3.0.1+ (Spring Security 6.0.1).
  */
  @Test
  @WithUserDetails("mklinkj")
  void testCallingHelloVariationWithAuthentication() throws Exception {
    mvc.perform(get("/hello/")) //
        .andExpect(status().isOk());
  }
}
