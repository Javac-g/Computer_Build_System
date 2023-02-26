package com.anobel.service;

import com.anobel.model.parts.*;

import java.util.List;


public interface AssemblyService {
    List<Cpu> getAllCpu();
    List<Gpu> getAllGpu();
    List<Ram> getAllRam();
    List<Motherboard> getAllMotherboard();
    List<Storage> getAllStorage();
}
