package org.mklinkj.qna.spring_security.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
  @GetMapping({"/", "/index"})
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public void login() {}
}
