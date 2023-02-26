package com.anobel.controller;

import com.anobel.model.Client;
import com.anobel.service.ClientService;
import com.anobel.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/clients")
public class ClientController {
    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("ClientServiceImpl")
    @Autowired
    private  ClientService clientService;

    @Autowired
    private RoleService roleService;


    
    @GetMapping("/all")
    public String listClients(Model model){
        model.addAttribute("clients",clientService.getAllClients());
        return "all_clients";
    }
    @GetMapping("/new")
    public String createClient(Model model){
        Client client = new Client();
		
        model.addAttribute("client",client);
        return "create_client";
    }
    @PostMapping("/all")
    public String saveClient(@Validated @ModelAttribute("client") Client client, BindingResult result){
        if (result.hasErrors()){
            return "create_client";
        }
        client.setRole(roleService.find(1L));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientService.create(client);
        return "redirect:/clients/all";
    }
    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id,Model model){
        model.addAttribute("client",clientService.readById(id));
        return "edit_client";
    }
    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable Long id ,@ModelAttribute("client") Client client, Model model){
        
        clientService.update(id,client);
        return "redirect:/clients/all";
    }
	@GetMapping("/all/{id}")
	public String deleteClient(@PathVariable Long     id){
		clientService.delete(id);
		return "redirect:/clients/all";

	}
	
	@GetMapping("/login")
    public String loginClient(){
        return "login";
    }
	
	

}
