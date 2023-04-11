package com.anobel.controller;

import com.anobel.model.Assembly;
import com.anobel.model.parts.Motherboard;
import com.anobel.repository.ActiveOrdersRepository;
import com.anobel.service.AssemblyService;
import com.anobel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/set")
public class SetupController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ActiveOrdersRepository activeOrdersRepository;

    @Autowired
    private AssemblyService assemblyService;

    @PostMapping("/Motherboard/{client_id}/{part_id}")
    public String setMotherboard(@PathVariable ("client_id")Long client_id,
                                 @PathVariable("part_id")Long mother_id,
                                 @ModelAttribute("Assembly")Assembly assembly ,Model model){

        Motherboard motherboard = assemblyService.findById(mother_id);

        assembly.setMotherboard(motherboard);
        model.addAttribute("Assembly",assembly);
        return "redirect:/set/CPU/{client_id}";

    }
    @GetMapping("/set/CPU/{client_id}")
    public String addCPU( @PathVariable ("client_id")Long client_id,
                         @PathVariable("part_id")Long mother_id,
                         @ModelAttribute("Assembly")Assembly assembly ,Model model ) {
        model.addAttribute("storageList", assemblyService.getAllStorage());

        model.addAttribute("ramList", assemblyService.getAllRam());
        model.addAttribute("gpuList", assemblyService.getAllGpu());
        model.addAttribute("cpuList", assemblyService.getAllCpu());
        model.addAttribute("Assembly", assembly);


        model.addAttribute("client_id", client_id);
        return "create_order";


    }
}
