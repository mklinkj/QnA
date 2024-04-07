package org.mklinkj.qna.jsp_time_tags.web;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

  @GetMapping
  public String welcome(Model model) {
    LocalDateTime now = LocalDateTime.now();
    model.addAttribute("now", now);

    return "welcome";
  }
}
