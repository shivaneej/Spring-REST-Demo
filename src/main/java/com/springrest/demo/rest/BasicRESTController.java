package com.springrest.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BasicRESTController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!"; //will return string
    }
}
