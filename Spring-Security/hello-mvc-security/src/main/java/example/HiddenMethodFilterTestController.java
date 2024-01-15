package example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
@Controller
public class HiddenMethodFilterTestController {

  @DeleteMapping("/delete")
  public String delete() {
    return "delete";
  }

  @PutMapping("/put")
  public String put() {
    return "put";
  }
}
