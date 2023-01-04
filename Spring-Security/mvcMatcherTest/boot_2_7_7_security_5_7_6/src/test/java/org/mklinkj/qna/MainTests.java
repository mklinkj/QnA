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

  @Test
  @WithUserDetails("mklinkj")
  void testCallingHelloVariationWithAuthentication() throws Exception {
    mvc.perform(get("/hello/")) //
        .andExpect(status().isOk());
  }
}
