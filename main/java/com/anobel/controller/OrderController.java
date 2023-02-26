package com.anobel.controller;

import com.anobel.model.Assembly;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.service.AssemblyService;
import com.anobel.service.ClientService;
import com.anobel.service.OrderService;
import com.anobel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
	@Autowired
    private OrderServiceImpl OrderServiceImpl;
	@Autowired
    private ClientService clientService;
	@Autowired
	private AssemblyService assemblyService;

    @GetMapping("/privateOrders/{client_id}")
    public String myOrders(@PathVariable("client_id") Long id, Model model){
		Client client = clientService.readById(id);
        model.addAttribute("myOrders",orderService.findCurrentClientOrders(id));
		model.addAttribute("client",client);
        return "client_orders";
    }
	
	@GetMapping("/privateOrders/info/{order_id}")
	public String myOrderInfo(@PathVariable("order_id") Long id,Model model){
		Order order = OrderServiceImpl.findById(id);
		Assembly assembly = order.getAssembly();
		model.addAttribute("assembly",assembly);
		return  "assembly_info";
	}
	@GetMapping("/new/{client_id}")
	public String createOrder(@PathVariable("client_id") long client_id,Model model){
		model.addAttribute("gpuList",assemblyService.getAllGpu());
		model.addAttribute("cpuList",assemblyService.getAllCpu());
		model.addAttribute("assembly", new Assembly());
		model.addAttribute("Order",new Order());
		model.addAttribute("client_id",client_id);
		return "create_order";
	}

	@PostMapping("/new/{client_id}")
	public String saveOrder(@PathVariable("client_id") long client_id,Model model){
		return null;
	}
}
