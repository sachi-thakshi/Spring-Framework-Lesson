package lk.ijse.gdse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("hello")
public class HelloController {
    public HelloController() {
        System.out.println("HelloController Constructor");
    }

//    @GetMapping("one")
//    public String hello1() {
//        return "Hello World One";
//    }

//    @GetMapping("two")
//    public String hello2() {
//        return "Hello World Two";
//    }

      @GetMapping()
      public String hello() {
        return "index";
//        return "Hello World Two";
    }
}
