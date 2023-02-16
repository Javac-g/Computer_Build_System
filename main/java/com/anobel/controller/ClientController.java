package com.anobel.controller;

import com.anobel.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClientController {
    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("ClientServiceImpl")
    private  ClientService clientService;


    @GetMapping("/clients")
    public String listClients(Model model){
        model.addAttribute("clients",clientService.getAllClients());
        return "clients";
    }

}
