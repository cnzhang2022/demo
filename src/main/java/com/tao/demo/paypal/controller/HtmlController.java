package com.tao.demo.paypal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/h5")
public class HtmlController {

    @RequestMapping(value = "/{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }

}
