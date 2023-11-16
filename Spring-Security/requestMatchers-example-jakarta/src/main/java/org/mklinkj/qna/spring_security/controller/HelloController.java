package org.mklinkj.qna.spring_security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HelloController {

  @GetMapping("/hello")
  public void hello() {}

  @GetMapping("/admin")
  public void admin() {}
}
