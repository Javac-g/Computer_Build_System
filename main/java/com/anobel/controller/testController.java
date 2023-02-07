package com.anobel.controller;

import ch.qos.logback.core.model.Model;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class testController {
    private static final Logger logger = LoggerFactory.getLogger(testController.class);
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name",defaultValue = "Max") String name){
        logger.info("https://192.168.0.103:8089/test/hello");
        return String.format("Hello %s",name);
    }
    @RequestMapping({"/","index"})
    public String index(Model model){
        logger.info("https://192.168.0.103:8089/test/index");
        return "templates/Login";
    }

    @RequestMapping(value = "stop",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void stop(){
        logger.info("https://192.168.0.103:8089/test/stop");
        ((ConfigurableApplicationContext)applicationContext).close();
    }
    @PreDestroy
    public void processEnd(){
        logger.info("Pre derstroy called");
    }
    @PostConstruct
    public void processStart(){
        logger.info("Post construct called");
    }

}
