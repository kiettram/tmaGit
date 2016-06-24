package org.o7planning.tutorial.springmvc;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class RedirectExampleController {
 
  @RequestMapping(value = "/redirect", method = RequestMethod.GET)
  public String authorInfo(Model model) {
 
      // Làm gì đó tại đây ...
 
      return "redirect:/hello";
  }
}