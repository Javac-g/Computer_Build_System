package com.anobel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String loginClient(){
        return "login";
    }

    @GetMapping("/home")
    public String homeClient(){
        return "home";
    }
}
