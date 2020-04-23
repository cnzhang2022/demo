package com.tao.demo.controller;

import com.tao.demo.aop.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/login")
    public String login(String id) {
        return "ok";
    }

    @GetMapping(value = "/aaa")
    public String aaa() {
        personService.save("test1");
        return "ok";
    }

    @GetMapping(value = "/bbb")
    public String bbb() {
        personService.update("test2");
        return "ok";
    }
}
