package com.anobel.controller;

import java.util.*;
import com.anobel.model.Assembly;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.model.parts.*;
import com.anobel.model.*;
import com.anobel.service.AssemblyService;
import com.anobel.service.ClientService;
import com.anobel.service.OrderService;
import com.anobel.service.PriceService;
import com.anobel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.anobel.repository.parts_repository.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

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
	
	@Autowired
	private CpuRepository cpuRepository;
	@Autowired
	private StorageRepository storageRepository;
	@Autowired
	private GpuRepository gpuRepository;
	@Autowired
	private RamRepository ramRepository;
	@Autowired
	private MotherboardRepository motherboardRepository;
	
	
	private Client x = new Client();

    @GetMapping("/myOrders/{client_id}")
    public String myOrders(@PathVariable("client_id") Long id,HttpSession session, Model model){
		Client client = clientService.readById(id);
	
        model.addAttribute("myOrders",orderService.findCurrentClientOrders(id));
		model.addAttribute("client",client);
		session.setAttribute("client", client);
        return "client_orders";
    }
	@GetMapping("/addMotherboard/{client_id}")
    public String getM(@PathVariable("client_id") Long client_id, 
	@RequestParam(value = "page", defaultValue = "1") int page,Model model){
		 
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		
		List<Motherboard> motherboardList = assemblyService.getAllMotherboard();

		int pageSize = 10;
		int totalElements = motherboardList.size();
		int totalPages = (int) Math.ceil((double) totalElements / pageSize);
		int startIndex = (page - 1) * pageSize;
		int endIndex = startIndex + pageSize;
		List<Motherboard> paginatedMotherboards = motherboardList.subList(startIndex, endIndex);

		model.addAttribute("motherboardList", motherboardList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("client", client);
		
        return "addMotherboard";
    }
	
	@GetMapping("/addCpu/{client_id}")
    public String getC(@PathVariable("client_id") Long client_id,HttpSession session, Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Cpu> cpuList = cpuRepository.getCompatible(selectedMotherboard.getSocket_cpu());
		Set<Integer> cores = cpuList.stream().map(Cpu::getCore_count).collect(Collectors.toSet());
		Set<String> manuf = cpuList.stream().map(Cpu::getManufacturer).collect(Collectors.toSet());
		Set<Float> prices = cpuList.stream()
                            .map(cpu -> cpu.getCpu_price_history().getLast_price())
                            .collect(Collectors.toSet());

		
		model.addAttribute("cpuList",cpuList);
		model.addAttribute("cores",cores);
		model.addAttribute("manuf",manuf);
		model.addAttribute("prices",prices);
		
        return "addCpu";
    }
	@GetMapping("/addGpu/{client_id}")
    public String getG( @PathVariable("client_id") Long client_id,HttpSession session,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Gpu> gpuList = gpuRepository.getCompatible(selectedMotherboard.getConnector_interface());
		model.addAttribute("gpuList",gpuList);
		Set<Float> prices = gpuList.stream()
                            .map(gpu -> gpu.getGpu_price_history().getLast_price())
                            .collect(Collectors.toSet());
		Set<Integer> maxmem = gpuList.stream().map(Gpu::getMemory).collect(Collectors.toSet());
		
		model.addAttribute("prices",prices);
		model.addAttribute("maxmem",maxmem);
        return "addGpu";
    }
	
	@GetMapping("/addStorage/{client_id}")
    public String getS(@PathVariable("client_id") Long client_id,HttpSession session ,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Storage> storageList = storageRepository.getCompatible(selectedMotherboard.getStorage_type());
		model.addAttribute("storageList",storageList);
		Set<Float> prices = storageList.stream()
                            .map(storage -> storage.getStorage_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		Set<Integer> capacity = storageList.stream().map(Storage::getCapacity).collect(Collectors.toSet());
		Set<String> type = storageList.stream().map(Storage::getType).collect(Collectors.toSet());
		
		model.addAttribute("prices",prices);
		model.addAttribute("types",type);
		model.addAttribute("capacities",capacity);
		
        return "addStorage";
    }
	@GetMapping("/addRam/{client_id}")
    public String getR(@PathVariable("client_id") Long client_id,HttpSession session , Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Ram> ramList = ramRepository.getCompatible(selectedMotherboard.getRam_type());
		model.addAttribute("ramList",ramList);
		Set<Float> prices = ramList.stream()
                            .map(ram -> ram.getRam_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		Set<Integer> speed = ramList.stream().map(Ram::getSpeed).collect(Collectors.toSet());
		Set<String> type = ramList.stream().map(Ram::getRam_type).collect(Collectors.toSet());
		
		model.addAttribute("prices",prices);
		model.addAttribute("types",type);
		model.addAttribute("speeds",speed);
		
        return "addRam";
    }
	@PostMapping("/chooseM/{id}")
	public String processMotherboardSelection(@PathVariable("id") Long motherboardId,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		 int pageSize = 10;
		Motherboard selectedMotherboard = motherboardRepository.findById(motherboardId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Motherboard ID"));
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		Float sum = mbi;
		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedMotherboard", selectedMotherboard);
			
			session.setAttribute("sum", sum);

			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
@PostMapping("/chooseC/{id}")
	public String processCpuSelection(@PathVariable("id") Long cpuId,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		Cpu selectedCpu = cpuRepository.findById(cpuId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Cpu ID"));
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float cpui = selectedCpu.getCpu_price_history().getLast_price();
		Float sum = mbi+cpui;
		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedCpu", selectedCpu);
			session.setAttribute("sum", sum);
			return "redirect:/orders/new/" + x.getId();
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
@PostMapping("/chooseR/{id}")
	public String processRamSelection(@PathVariable("id") Long ramId,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		Ram selectedRam = ramRepository.findById(ramId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Ram ID"));
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
		Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
		float gpui = selectedGPU.getGpu_price_history().getLast_price();
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float rami = selectedRam.getRam_price_history().getLast_price();
		Float sum = mbi+cpui+rami+gpui;
		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedRam", selectedRam);
			session.setAttribute("sum", sum);
			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
@PostMapping("/chooseG/{id}")
	public String processGpuSelection(@PathVariable("id") Long gpuId,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		Gpu selectedGpu = gpuRepository.findById(gpuId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Gpu ID"));
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
	
        Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float gpui = selectedGpu.getGpu_price_history().getLast_price();
		Float sum = mbi+cpui+gpui;
		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedGpu", selectedGpu);
			session.setAttribute("sum", sum);
			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
@PostMapping("/chooseS/{id}")
	public String processStorageSelection(@PathVariable("id") Long storageId,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		Storage selectedStorage = storageRepository.findById(storageId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Storage ID"));
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
        Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
        Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		
		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float gpui = selectedGPU.getGpu_price_history().getLast_price();
		float rami = selectedRAM.getRam_price_history().getLast_price();
		float storagei = selectedStorage.getStorage_price_history().getLast_price();
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		Float sum = cpui+gpui+rami+storagei+mbi;

		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedStorage", selectedStorage);
			session.setAttribute("sum", sum);

			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
	

	
	@GetMapping("/myOrders/info/{order_id}")
	public String myOrderInfo(@PathVariable("order_id") Long id,Model model){
		Order order = orderService.findById(id);
		Assembly assembly = order.getAssembly();
		model.addAttribute("assembly",assembly);
		return  "assembly_info";
	}
	
	@GetMapping("/new/{client_id}")
	public String createOrder(@PathVariable("client_id") Long id,Model model){
		Client client = clientService.readById(id);
		Assembly assemblyX = new Assembly();
		model.addAttribute("storageList",assemblyService.getAllStorage());
		 
		model.addAttribute("ramList", assemblyService.getAllRam());
		model.addAttribute("gpuList", assemblyService.getAllGpu());
		model.addAttribute("cpuList", assemblyService.getAllCpu());
		
		model.addAttribute("client",client);
		

		 
		return "create_order";
	}

	
    @PostMapping("/add-components/{id}")
public String addComponentsToAssembly(@PathVariable("id") Long clientId,HttpSession session,Model model) {

    Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
    Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
    Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
    Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
    Storage selectedStorage = (Storage) session.getAttribute("selectedStorage");
    Float sum = (Float) session.getAttribute("sum");
    Client client = clientService.readById(clientId);
    model.addAttribute("myOrders",orderService.findCurrentClientOrders(client.getId()));
	model.addAttribute("client",x);
	session.setAttribute("client", x);

    Assembly assembly = new Assembly();
    assembly.setMotherboard(selectedMotherboard);
    assembly.setCpu(selectedCPU);
    assembly.setGpu(selectedGPU);
    assembly.setRam(selectedRAM);
    assembly.setStorage(selectedStorage);
	Purpose purpose = new Purpose();
	purpose.setId(1L);
	assembly.setPurpose(purpose);
	assemblyService.create(assembly);
	
    Order order = new Order();
    order.setAssembly(assembly);
    order.setCpu_price(selectedCPU.getCpu_price_history());
    order.setGpu_price(selectedGPU.getGpu_price_history());
    order.setRam_price(selectedRAM.getRam_price_history());
    order.setStorage_price(selectedStorage.getStorage_price_history());
    order.setMotherboard_price(selectedMotherboard.getMotherboard_price_history());
	order.setOrder_status("Sent");
	
   
    order.setTotal_price(sum);

    order.setOrder_date(LocalDateTime.now());

    orderService.create(client, order);

    // Clear the session attributes
    session.removeAttribute("selectedMotherboard");
    session.removeAttribute("selectedCpu");
    session.removeAttribute("selectedGpu");
    session.removeAttribute("selectedRam");
    session.removeAttribute("selectedStorage");
    session.removeAttribute("sum");
    

    return "redirect:/orders/myOrders/" + clientId;
}


    @GetMapping("/success")
    public String showSuccessPage() {
        return "assembly/success";
    }
}
