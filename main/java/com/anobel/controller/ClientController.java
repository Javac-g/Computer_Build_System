package com.anobel.controller;

import com.anobel.model.Client;
import com.anobel.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClientController {
    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Qualifier("ClientServiceImpl")
    @Autowired
    private  ClientService clientService;


    @GetMapping("/clients")
    public String listClients(Model model){
        model.addAttribute("clients",clientService.getAllClients());
        return "clients";
    }
    @GetMapping("/clients/new")
    public String createClient(Model model){
        Client client = new Client();
		
        model.addAttribute("client",client);
        return "create_client";
    }
    @PostMapping("/clients")
    public String saveClient(@ModelAttribute("client") Client client){
        clientService.create(client);
        return "redirect:/clients";
    }
    @GetMapping("/clients/edit/{id}")
    public String editClient(@PathVariable Long id,Model model){
        model.addAttribute("client",clientService.readById(id));
        return "edit_client";
    }
    @PostMapping("/clients/{id}")
    public String updateClient(@PathVariable Long id ,@ModelAttribute("client") Client client, Model model){
        
        clientService.update(id,client);
        return "redirect:/clients";
    }
	@GetMapping("/clients/{id}")
	public String deleteClient(@PathVariable Long id){
		clientService.delete(id);
		return "redirect:/clients";

	}
	
	

}
