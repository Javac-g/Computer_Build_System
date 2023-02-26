package com.anobel.service.impl;

import com.anobel.model.parts.*;
import com.anobel.repository.parts_repository.CpuRepository;
import com.anobel.repository.parts_repository.GpuRepository;
import com.anobel.service.AssemblyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyServiceImpl implements AssemblyService {
    private final CpuRepository cpuRepository;
    private final GpuRepository gpuRepository;

    public AssemblyServiceImpl(CpuRepository cpuRepository, GpuRepository gpuRepository) {
        this.cpuRepository = cpuRepository;
        this.gpuRepository = gpuRepository;
    }

    @Override
    public List<Cpu> getAllCpu() {
        return cpuRepository.findAll();
    }

    @Override
    public List<Gpu> getAllGpu() {
        return gpuRepository.findAll();
    }

    @Override
    public List<Ram> getAllRam() {
        return null;
    }

    @Override
    public List<Motherboard> getAllMotherboard() {
        return null;
    }

    @Override
    public List<Storage> getAllStorage() {
        return null;
    }
}
