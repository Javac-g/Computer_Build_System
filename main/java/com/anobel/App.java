package com.anobel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);

    }
//    @GetMapping ("/hello")
//    public String hello(@RequestParam(value = "name",defaultValue = "Max") String name){
//        return String.format("Hello",name);
//    }
}