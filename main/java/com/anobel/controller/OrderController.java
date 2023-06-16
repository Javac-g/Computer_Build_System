package com.anobel.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import java.util.*;
import com.anobel.model.Assembly;
import com.anobel.model.Client;
import com.anobel.model.Order;
import com.anobel.model.parts.*;
import com.anobel.model.*;
import com.anobel.service.*;
import com.anobel.service.impl.*;
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
	@Autowired
	private ComputerCaseRepository computerCaseRepository;
	@Autowired
	private PowerSupplyRepository powerSupplyRepository;
	@Autowired
	private CpuCoolerRepository cpuCoolerRepository;
	
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
	
		Set<String> sockets = motherboardList.stream().map(Motherboard::getSocketType).collect(Collectors.toSet());
		Set<String> models = motherboardList.stream().map(Motherboard::getModel).collect(Collectors.toSet());
		Set<String> chipsets = motherboardList.stream().map(Motherboard::getChipset).collect(Collectors.toSet());
		Set<String> ram_types = motherboardList.stream().map(Motherboard::getRamType).collect(Collectors.toSet());
		Set<Integer> ram_speeds = motherboardList.stream().map(Motherboard::getRamSpeed).collect(Collectors.toSet());
		Set<Integer> tdps = motherboardList.stream().map(Motherboard::getTdp).collect(Collectors.toSet());
		Set<Integer> ram_capacitys = motherboardList.stream().map(Motherboard::getRamCapacity).collect(Collectors.toSet());
		Set<String> storage_ports = motherboardList.stream().map(Motherboard::getStoragePorts).collect(Collectors.toSet());
		Set<String> pcies = motherboardList.stream().map(Motherboard::getPcieType).collect(Collectors.toSet());
		model.addAttribute("sockets", sockets);
		model.addAttribute("models", models);
		model.addAttribute("chipsets", chipsets);
		model.addAttribute("ram_types", ram_types);
		model.addAttribute("ram_speeds", ram_speeds);
		model.addAttribute("tdps", tdps);
		model.addAttribute("ram_capacitys", ram_capacitys);
		model.addAttribute("storage_ports", storage_ports);
		model.addAttribute("pcies", pcies);

		model.addAttribute("motherboardList", motherboardList);
		model.addAttribute("client", client);
		
        return "addMotherboard";
    }
	@Transactional
	@GetMapping("/addCpu/{client_id}")
    public String getC(@PathVariable("client_id") Long client_id,HttpSession session, Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());

		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Cpu> cpuList = cpuRepository.getCompatible(Math.toIntExact(selectedMotherboard.getId()));

		Set<Integer> cores = cpuList.stream().map(Cpu::getCoreCount).collect(Collectors.toSet());
		Set<String> manuf = cpuList.stream().map(Cpu::getManufacturer).collect(Collectors.toSet());
		Set<Float> prices = cpuList.stream()
                            .map(cpu -> cpu.getCpu_price_history().getLast_price())
                            .collect(Collectors.toSet());
                            Set<String> tdpSet = cpuList.stream().map(Cpu::getTdp).filter(Objects::nonNull).map(Object::toString).collect(Collectors.toSet());
		Set<Integer> coreCountSet = cpuList.stream().map(Cpu::getCoreCount).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> manufacturerSet = cpuList.stream().map(Cpu::getManufacturer).collect(Collectors.toSet());
		Set<Integer> performanceCoreClockSet = cpuList.stream().map(Cpu::getPerformanceCoreClock).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> performanceBoostClockSet = cpuList.stream().map(Cpu::getPerformanceBoostClock).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> integratedGraphicsSet = cpuList.stream().map(Cpu::getIntegratedGraphics).collect(Collectors.toSet());
		
		Set<String> socketTypeSet = cpuList.stream().map(Cpu::getSocketType).collect(Collectors.toSet());
		Set<Integer> threadsSet = cpuList.stream().map(Cpu::getThreads).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> wattageSet = cpuList.stream().map(Cpu::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Double> cpuSpeedSet = cpuList.stream().map(Cpu::getCpuSpeed).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> brandSet = cpuList.stream().map(Cpu::getBrand).collect(Collectors.toSet());
		Set<String> cpuModelSet = cpuList.stream().map(Cpu::getCpuModel).collect(Collectors.toSet());
		Set<String> cpuProcessorTypeSet = cpuList.stream().map(Cpu::getCpuProcessorType).collect(Collectors.toSet());
		Set<String> chipsetSet = cpuList.stream().map(Cpu::getChipset).collect(Collectors.toSet());
		Set<String> ramTypeSet = cpuList.stream().map(Cpu::getRamType).collect(Collectors.toSet());
		Set<Integer> ramSpeedSet = cpuList.stream().map(Cpu::getRamSpeed).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Double> biosVersionSet = cpuList.stream().map(Cpu::getBiosVersion).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> supportedGpuModelSet = cpuList.stream().map(Cpu::getSupportedGpuModel).collect(Collectors.toSet());

		model.addAttribute("tdpSet", tdpSet);
		model.addAttribute("coreCountSet", coreCountSet);
		model.addAttribute("manufacturerSet", manufacturerSet);
		model.addAttribute("performanceCoreClockSet", performanceCoreClockSet);
		model.addAttribute("performanceBoostClockSet", performanceBoostClockSet);
		model.addAttribute("integratedGraphicsSet", integratedGraphicsSet);
		model.addAttribute("socketTypeSet", socketTypeSet);
		model.addAttribute("threadsSet", threadsSet);
		model.addAttribute("wattageSet", wattageSet);
		model.addAttribute("cpuSpeedSet", cpuSpeedSet);
		model.addAttribute("brandSet", brandSet);
		model.addAttribute("cpuModelSet", cpuModelSet);
		model.addAttribute("cpuProcessorTypeSet", cpuProcessorTypeSet);
		model.addAttribute("chipsetSet", chipsetSet);
		model.addAttribute("ramTypeSet", ramTypeSet);
		model.addAttribute("ramSpeedSet", ramSpeedSet);
		model.addAttribute("biosVersionSet", biosVersionSet);
		model.addAttribute("supportedGpuModelSet", supportedGpuModelSet);

		model.addAttribute("cpuList",cpuList);
		
		model.addAttribute("prices",prices);
		
        return "addCpu";
    }
    @Transactional
	@GetMapping("/addGpu/{client_id}")
    public String getG( @PathVariable("client_id") Long client_id,HttpSession session,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());

		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");

		List<Gpu> gpuList = gpuRepository.getCompatible(Math.toIntExact(selectedMotherboard.getId()));

		model.addAttribute("gpuList",gpuList);

		Set<Float> prices = gpuList.stream()
                            .map(gpu -> gpu.getGpu_price_history().getLast_price())
                            .collect(Collectors.toSet());
		
		
		model.addAttribute("prices",prices);
		Set<Integer> memorySizeSet = gpuList.stream().map(Gpu::getMemorySize).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> lengthSet = gpuList.stream().map(Gpu::getLength).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> manufacturerSet = gpuList.stream().map(Gpu::getManufacturer).collect(Collectors.toSet());
		Set<String> chipsetSet = gpuList.stream().map(Gpu::getChipset).collect(Collectors.toSet());
		
		Set<Integer> tdpSet = gpuList.stream().map(Gpu::getTdp).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> pcieTypeSet = gpuList.stream().map(Gpu::getPcieType).collect(Collectors.toSet());
		Set<Integer> wattageSet = gpuList.stream().map(Gpu::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> modelSet = gpuList.stream().map(Gpu::getModel).collect(Collectors.toSet());
		Set<String> gpuPcieSlotSet = gpuList.stream().map(Gpu::getGpuPcieSlot).collect(Collectors.toSet());
		Set<String> gpuFormFactorSet = gpuList.stream().map(Gpu::getGpuFormFactor).collect(Collectors.toSet());

		model.addAttribute("memorySizeSet", memorySizeSet);
		model.addAttribute("lengthSet", lengthSet);
		model.addAttribute("manufacturerSet", manufacturerSet);
		model.addAttribute("chipsetSet", chipsetSet);
		
		model.addAttribute("tdpSet", tdpSet);
		model.addAttribute("pcieTypeSet", pcieTypeSet);
		model.addAttribute("wattageSet", wattageSet);
		model.addAttribute("modelSet", modelSet);
		model.addAttribute("gpuPcieSlotSet", gpuPcieSlotSet);
		model.addAttribute("gpuFormFactorSet", gpuFormFactorSet);


        return "addGpu";
    }
    @Transactional
    @GetMapping("/addRam/{client_id}")
    public String getR(@PathVariable("client_id") Long client_id,HttpSession session , Model model){

		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Ram> ramList = ramRepository.getCompatible(Math.toIntExact(selectedMotherboard.getId()));
		model.addAttribute("ramList",ramList);
		Set<Float> prices = ramList.stream()
                            .map(ram -> ram.getRam_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		
		Set<Integer> speedSet = ramList.stream().map(Ram::getSpeed).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> modulesSet = ramList.stream().map(Ram::getModules).collect(Collectors.toSet());
		Set<String> manufacturerSet = ramList.stream().map(Ram::getManufacturer).collect(Collectors.toSet());
	
		Set<Integer> capacitySet = ramList.stream().map(Ram::getCapacity).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> ramTypeSet = ramList.stream().map(Ram::getRamType).collect(Collectors.toSet());
		Set<Integer> wattageSet = ramList.stream().map(Ram::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> casLatencySet = ramList.stream().map(Ram::getCasLatency).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Double> voltageSet = ramList.stream().map(Ram::getVoltage).filter(Objects::nonNull).collect(Collectors.toSet());

		
		model.addAttribute("prices",prices);
		model.addAttribute("speedSet", speedSet);
		model.addAttribute("modulesSet", modulesSet);
		model.addAttribute("manufacturerSet", manufacturerSet);
	
		model.addAttribute("capacitySet", capacitySet);
		model.addAttribute("ramTypeSet", ramTypeSet);
		model.addAttribute("wattageSet", wattageSet);
		model.addAttribute("casLatencySet", casLatencySet);
		model.addAttribute("voltageSet", voltageSet);

		
        return "addRam";
    }
	@Transactional
	@GetMapping("/addStorage/{client_id}")
    public String getS(@PathVariable("client_id") Long client_id,HttpSession session ,Model model){

		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		List<Storage> storageList = storageRepository.getCompatible(Math.toIntExact(selectedMotherboard.getId()));
		model.addAttribute("storageList",storageList);
		Set<Float> prices = storageList.stream()
                            .map(storage -> storage.getStorage_price_history().getLast_price())
                            .collect(Collectors.toSet());
		Set<Integer> capacitySet = storageList.stream().map(Storage::getCapacity).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> typeSet = storageList.stream().map(Storage::getType).collect(Collectors.toSet());
		Set<String> manufacturerSet = storageList.stream().map(Storage::getManufacturer).collect(Collectors.toSet());
		
		Set<String> interfaceTypeSet = storageList.stream().map(Storage::getInterfaceType).collect(Collectors.toSet());
		Set<Integer> cacheSizeSet = storageList.stream().map(Storage::getCacheSize).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> wattageSet = storageList.stream().map(Storage::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Double> readSpeedSet = storageList.stream().map(Storage::getReadSpeed).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Double> writeSpeedSet = storageList.stream().map(Storage::getWriteSpeed).filter(Objects::nonNull).collect(Collectors.toSet());

		
		model.addAttribute("prices",prices);
		model.addAttribute("capacitySet", capacitySet);
		model.addAttribute("typeSet", typeSet);
		model.addAttribute("manufacturerSet", manufacturerSet);
	
		model.addAttribute("interfaceTypeSet", interfaceTypeSet);
		model.addAttribute("cacheSizeSet", cacheSizeSet);
		model.addAttribute("wattageSet", wattageSet);
		model.addAttribute("readSpeedSet", readSpeedSet);
		model.addAttribute("writeSpeedSet", writeSpeedSet);

		
        return "addStorage";
    }
    @Transactional
	@GetMapping("/addPower/{client_id}")
    public String getPs(@PathVariable("client_id") Long client_id,HttpSession session ,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());
		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		Storage selectedStorage = (Storage)session.getAttribute("selectedStorage");
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
        Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
       
	List<PowerSupply> powerSupplyList = powerSupplyRepository.getCompatible(
		Math.toIntExact(selectedMotherboard.getId()),
		Math.toIntExact(selectedCPU.getId()),
		Math.toIntExact(selectedRAM.getId()),
		Math.toIntExact(selectedGPU.getId()),
		Math.toIntExact(selectedStorage.getId()));

		model.addAttribute("powerSupplyList",powerSupplyList);
		Set<Float> prices = powerSupplyList.stream()
                            .map(powerSupply -> powerSupply.getPowerSupply_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		Set<String> brandSet = powerSupplyList.stream().map(PowerSupply::getBrand).collect(Collectors.toSet());
		Set<String> modelSet = powerSupplyList.stream().map(PowerSupply::getModel).collect(Collectors.toSet());
		Set<Integer> wattageSet = powerSupplyList.stream().map(PowerSupply::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<String> efficiencySet = powerSupplyList.stream().map(PowerSupply::getEfficiency).collect(Collectors.toSet());
		
		Set<String> powerConnectorSet = powerSupplyList.stream().map(PowerSupply::getPowerConnector).collect(Collectors.toSet());
		model.addAttribute("brandSet", brandSet);
		model.addAttribute("modelSet", modelSet);
		model.addAttribute("wattageSet", wattageSet);
		model.addAttribute("efficiencySet", efficiencySet);
		
		model.addAttribute("powerConnectorSet", powerConnectorSet);
		model.addAttribute("prices",prices);
	
		
        return "addPower";
    }
    @Transactional
	@GetMapping("/addCooler/{client_id}")
    public String getCK(@PathVariable("client_id") Long client_id,HttpSession session ,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());

       
		List<CpuCooler> cpuCoolerList = assemblyService.getAllCpuCooler();
	
		model.addAttribute("cpuCoolerList",cpuCoolerList);
		Set<Float> prices = cpuCoolerList.stream()
                            .map(cpuCooler -> cpuCooler.getCpuCooler_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		Set<String> nameSet = cpuCoolerList.stream().map(CpuCooler::getName).collect(Collectors.toSet());
		Set<String> brandSet = cpuCoolerList.stream().map(CpuCooler::getBrand).collect(Collectors.toSet());
		Set<Integer> heightSet = cpuCoolerList.stream().map(CpuCooler::getHeight).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> fanSizeSet = cpuCoolerList.stream().map(CpuCooler::getFanSize).filter(Objects::nonNull).collect(Collectors.toSet());
		Set<Integer> wattageSet = cpuCoolerList.stream().map(CpuCooler::getWattage).filter(Objects::nonNull).collect(Collectors.toSet());
		model.addAttribute("nameSet", nameSet);
		model.addAttribute("brandSet", brandSet);
		model.addAttribute("heightSet", heightSet);
		model.addAttribute("fanSizeSet", fanSizeSet);
		model.addAttribute("wattageSet", wattageSet);

		model.addAttribute("prices",prices);
	
		
        return "addCooler";
    }
    @Transactional
    @GetMapping("/addCase/{client_id}")
    public String getCC(@PathVariable("client_id") Long client_id,HttpSession session ,Model model){
		Client client = clientService.readById(client_id);
		x.setId(client.getId());

		Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		CpuCooler selectedCpuCooler = (CpuCooler)session.getAttribute("selectedCpuCooler");
		Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        PowerSupply selectedPowerSupply = (PowerSupply) session.getAttribute("selectedPowerSupply");
        
       
	List<ComputerCase> computerCaseList = computerCaseRepository.getCompatible(
		Math.toIntExact(selectedMotherboard.getId()),
		Math.toIntExact(selectedCpuCooler.getId()),
		Math.toIntExact(selectedGPU.getId()),
		Math.toIntExact(selectedPowerSupply.getId()));

	
		model.addAttribute("computerCaseList",computerCaseList);
		Set<Float> prices = computerCaseList.stream()
                            .map(computerCase -> computerCase.getComputerCase_price_history().getLast_price())
                            .collect(Collectors.toSet());
							
		Set<String> typeSet = computerCaseList.stream().map(ComputerCase::getType).collect(Collectors.toSet());
		Set<String> modelSet = computerCaseList.stream().map(ComputerCase::getModel).collect(Collectors.toSet());
		Set<String> brandSet = computerCaseList.stream().map(ComputerCase::getBrand).collect(Collectors.toSet());
		Set<Integer> maxGpuLengthSet = computerCaseList.stream().map(ComputerCase::getMax_gpu_length).collect(Collectors.toSet());
		Set<Integer> maxCpuCoolerHeightSet = computerCaseList.stream().map(ComputerCase::getMax_cpu_cooler_height).collect(Collectors.toSet());
		Set<Integer> maxPsuLengthSet = computerCaseList.stream().map(ComputerCase::getMax_psu_length).collect(Collectors.toSet());

		model.addAttribute("typeSet", typeSet);
		model.addAttribute("modelSet", modelSet);
		model.addAttribute("brandSet", brandSet);
		model.addAttribute("maxGpuLengthSet", maxGpuLengthSet);
		model.addAttribute("maxCpuCoolerHeightSet", maxCpuCoolerHeightSet);
		model.addAttribute("maxPsuLengthSet", maxPsuLengthSet);


		model.addAttribute("prices",prices);
	
		
        return "addCase";
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
@PostMapping("/choosePower/{id}")
	public String processPowerSelection(@PathVariable("id") Long powerid,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {
		PowerSupply selectedPower = powerSupplyRepository.findById(powerid).orElseThrow(() -> new IllegalArgumentException("Invalid Power supply ID"));
		Storage selectedStorage = (Storage)session.getAttribute("selectedStorage");
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
        Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
        Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		
		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float gpui = selectedGPU.getGpu_price_history().getLast_price();
		float rami = selectedRAM.getRam_price_history().getLast_price();
		float storagei = selectedStorage.getStorage_price_history().getLast_price();
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float psi = selectedPower.getPowerSupply_price_history().getLast_price();
		Float sum = cpui+gpui+rami+storagei+mbi+psi;

		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedPowerSupply", selectedPower);
			session.setAttribute("sum", sum);

			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}
	@PostMapping("/chooseCooler/{id}")
	public String processCoolerSelection(@PathVariable("id") Long id,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {

		CpuCooler selectedCpuCooler = cpuCoolerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Cooler ID"));

		Storage selectedStorage = (Storage)session.getAttribute("selectedStorage");
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
        Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
        Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		PowerSupply selectedPower = (PowerSupply)session.getAttribute("selectedPowerSupply");

		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float gpui = selectedGPU.getGpu_price_history().getLast_price();
		float rami = selectedRAM.getRam_price_history().getLast_price();
		float storagei = selectedStorage.getStorage_price_history().getLast_price();
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float psi = selectedPower.getPowerSupply_price_history().getLast_price();
		float cpc = selectedCpuCooler.getCpuCooler_price_history().getLast_price();
		Float sum = cpui+gpui+rami+storagei+mbi+psi+cpc;

		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedCpuCooler", selectedCpuCooler);
			session.setAttribute("sum", sum);

			return "redirect:/orders/new/" + x.getId() ;
		} else if ("cancel".equals(action)) {
			// Handle cancellation or other actions
			// ...
		}

    return "redirect:/";
}

@PostMapping("/chooseCase/{id}")
	public String processCaseSelection(@PathVariable("id") Long id,
                                          @RequestParam("action") String action,
                                          HttpSession session,@ModelAttribute("client")Client client) {

		ComputerCase selectedComputerCase = computerCaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Case ID"));
		PowerSupply selectedPower = (PowerSupply)session.getAttribute("selectedPowerSupply");
		CpuCooler selectedCpuCooler = (CpuCooler)session.getAttribute("selectedCpuCooler");

		Storage selectedStorage = (Storage)session.getAttribute("selectedStorage");
		Cpu selectedCPU = (Cpu) session.getAttribute("selectedCpu");
        Gpu selectedGPU = (Gpu) session.getAttribute("selectedGpu");
        Ram selectedRAM = (Ram) session.getAttribute("selectedRam");
        Motherboard selectedMotherboard = (Motherboard) session.getAttribute("selectedMotherboard");
		
		float cpui = selectedCPU.getCpu_price_history().getLast_price();
		float gpui = selectedGPU.getGpu_price_history().getLast_price();
		float rami = selectedRAM.getRam_price_history().getLast_price();
		float storagei = selectedStorage.getStorage_price_history().getLast_price();
		float mbi = selectedMotherboard.getMotherboard_price_history().getLast_price();
		float psi = selectedPower.getPowerSupply_price_history().getLast_price();
		float cpc = selectedCpuCooler.getCpuCooler_price_history().getLast_price();
		float ccc =selectedComputerCase.getComputerCase_price_history().getLast_price();

		Float sum = cpui+gpui+rami+storagei+mbi+psi+cpc+ccc;

		if ("add".equals(action)) {
			// Add the selected motherboard to the assembly or session
			session.setAttribute("selectedComputerCase", selectedComputerCase);
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
	PowerSupply  selectedPower = (PowerSupply) session.getAttribute("selectedPower");
	ComputerCase selectedComputerCase = (ComputerCase)session.getAttribute("selectedComputerCase");
	CpuCooler selectedCpuCooler = (CpuCooler)session.getAttribute("selectedCpuCooler");
	
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
	assembly.setPowerSupply(selectedPower);
	assembly.setCpuCooler(selectedCpuCooler);
	assembly.setComputerCase(selectedComputerCase);
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
