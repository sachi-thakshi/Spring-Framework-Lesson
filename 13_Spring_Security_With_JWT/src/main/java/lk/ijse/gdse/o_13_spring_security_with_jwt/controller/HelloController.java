package lk.ijse.gdse.o_13_spring_security_with_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String hello(){
        return "Hello World";
    }
}
