package com.anobel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class testController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name",defaultValue = "Max") String name){
        return String.format("Hello %s",name);
    }

    @RequestMapping(value = "stop",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void stop(){
        ((ConfigurableApplicationContext)applicationContext).close();
    }

}
