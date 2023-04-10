package com.anobel.controller;

import com.anobel.model.Assembly;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.service.AssemblyService;
import com.anobel.service.ClientService;
import com.anobel.service.OrderService;
import com.anobel.service.PriceService;
import com.anobel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
	@Autowired
    private OrderServiceImpl orderServiceImpl;
	@Autowired
    private ClientService clientService;
	@Autowired
	private AssemblyService assemblyService;
	@Autowired
	private PriceService priceService;

    @GetMapping("/privateOrders/{client_id}")
    public String myOrders(@PathVariable("client_id") Long id, Model model){
		Client client = clientService.readById(id);
        model.addAttribute("myOrders",orderService.findCurrentClientOrders(id));
		model.addAttribute("client",client);
        return "client_orders";
    }
	@GetMapping("/addMotherboard/{client_id}")
    public String addM(@PathVariable("client_id") Long id, Model model){
		model.addAttribute("motherboardList",assemblyService.getAllMotherboard());
		
        return "addMotherboard";
    }
	
	@GetMapping("/privateOrders/info/{order_id}")
	public String myOrderInfo(@PathVariable("order_id") Long id,Model model){
		Order order = orderService.findById(id);
		Assembly assembly = order.getAssembly();
		model.addAttribute("assembly",assembly);
		return  "assembly_info";
	}
	@GetMapping("/new/{client_id}")
	public String createOrder(@PathVariable("client_id") long client_id,Model model){
		model.addAttribute("storageList",assemblyService.getAllStorage());
		 
		model.addAttribute("ramList",assemblyService.getAllRam());
		model.addAttribute("gpuList",assemblyService.getAllGpu());
		model.addAttribute("cpuList",assemblyService.getAllCpu());
		model.addAttribute("assembly", new Assembly());

		model.addAttribute("client_id",client_id);
		return "create_order";
	}

	@PostMapping("/new/{client_id}")
	public String saveOrder(@PathVariable("client_id") long client_id,
							Model model,
							@ModelAttribute("assembly") Assembly assembly,
							BindingResult result){
		Order order = new Order();
		order.setCpu_price(priceService.getCpubyId(assembly.getCpu().getId()));
		order.setGpu_price(priceService.getGpuById(assembly.getGpu().getId()));
		order.setRam_price(priceService.getRamById(assembly.getRam().getId()));
		order.setStorage_price(priceService.getStorageById(assembly.getStorage().getId()));
		order.setMotherboard_price(priceService.getMotherboardbyId(assembly.getMotherboard().getId()));
		order.setOrder_status(String.valueOf(OrderStatus.DELIVERED));
		order.setOrder_date(LocalDateTime.now());
		order.setDiscount(0);
		order.setTotal_price(order.getTotalPrice());
		order.setAssembly(assembly);
		orderService.create(client_id,order);

		return null;
	}
}
