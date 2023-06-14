package com.anobel.service;

import com.anobel.model.parts.*;
import com.anobel.model.*;

import java.util.List;


public interface AssemblyService {
    List<Cpu> getAllCpu();
    List<Gpu> getAllGpu();
    List<Ram> getAllRam();
    List<Motherboard> getAllMotherboard();
    List<Storage> getAllStorage();

    List<ComputerCase> getAllComputerCase();
	List<PowerSupply> getAllPowerSupply();
	List<CpuCooler> getAllCpuCooler();
	
	Assembly create(Assembly assembly);
    Motherboard findById(Long id);
}
