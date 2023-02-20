package com.anobel.controller;
import com.anobel.model.Client;
import com.anobel.service.OrderService;
import com.anobel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
	@Autowired
    private ClientService clientService;

    @GetMapping("/privateOrders/{client_id}")
    public String myOrders(@PathVariable("client_id") long id, Model model){
		Client client = clientService.readById(id);
        model.addAttribute("myOrders",orderService.findCurrentClientOrders(id));
		model.addAttribute("client",client);
        return "client_orders";
    }
}
