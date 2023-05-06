package com.anobel.service.impl;

import com.anobel.model.parts.*;
import com.anobel.repository.parts_repository.*;
import com.anobel.service.CompatibilityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompatibilityServiceImpl implements CompatibilityService {

    @Autowired
    private CpuRepository cpuRepository;
    @Autowired
    private GpuRepository gpuRepository;
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private MotherboardRepository motherboardRepository;
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public List<Cpu> compatibleCPU(Motherboard motherboard) {
        return cpuRepository.getCompatible(motherboard.getSocket_cpu());
    }

    @Override
    public List<Motherboard> compatibleMotherboard(Cpu cpu,Ram ram,Gpu gpu) {
        return motherboardRepository.getCompatible(cpu.getSocket(),ram.getSpeed(),ram.getRam_type(),gpu.getPcie_type());
    }

    @Override
    public List<Ram> compatibleRam(Motherboard motherboard) {
        return ramRepository.getCompatible(motherboard.getRam_type(), motherboard.getRam_speed());
    }

    @Override
    public List<Gpu> compatibleGpu(Motherboard motherboard) {
        return gpuRepository.getCompatible(motherboard.getGpu_type());
    }

    @Override
    public List<Storage> compatibleStorage(Motherboard motherboard) {
        return storageRepository.getCompatible(motherboard.getStorage_type());
    }
}
