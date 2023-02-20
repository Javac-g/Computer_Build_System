package com.anobel.controller;

import com.anobel.service.OrderService;
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

    @GetMapping("/privateOrders/{client_id}")
    public String myOrders(@PathVariable("client_id") long id, Model model){
        model.addAttribute("myOrders",orderService.findCurrentClientOrders(id));
        return "client_orders";
    }
}
