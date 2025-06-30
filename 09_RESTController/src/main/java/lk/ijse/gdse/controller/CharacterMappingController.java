package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/char")
public class CharacterMappingController {

    @GetMapping(path = "item/I???")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("????/search")
    public String sayHelloGet() {
        return "Hello World Search";
    }
}
