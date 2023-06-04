package com.anobel.service.impl;

import com.anobel.model.parts.*;
import com.anobel.model.*;
import com.anobel.repository.parts_repository.*;
import com.anobel.repository.*;
import com.anobel.service.AssemblyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssemblyServiceImpl implements AssemblyService {
    private final CpuRepository cpuRepository;
    private final GpuRepository gpuRepository;
    private final MotherboardRepository motherboardRepository;
    private final StorageRepository storageRepository;
    private final RamRepository ramRepository;
    private final ComputerCaseRepository computerCaseRepository;
	private final AssemblyRepository assemblyRepository;

    public AssemblyServiceImpl(AssemblyRepository assemblyRepository,ComputerCaseRepository computerCaseRepository,CpuRepository cpuRepository, GpuRepository gpuRepository, MotherboardRepository motherboardRepository, StorageRepository storageRepository, RamRepository ramRepository) {
        this.cpuRepository = cpuRepository;
        this.gpuRepository = gpuRepository;
        this.motherboardRepository = motherboardRepository;
        this.storageRepository = storageRepository;
        this.ramRepository = ramRepository;
        this.computerCaseRepository = computerCaseRepository;
		this.assemblyRepository = assemblyRepository;
    }

    @Override
    public List<Cpu> getAllCpu() {
        return cpuRepository.findAll();
    }
	@Override
	public Assembly create(Assembly assembly){
		return assemblyRepository.save(assembly);
	}

    @Override
    public List<Gpu> getAllGpu() {
        return gpuRepository.findAll();
    }

    @Override
    public List<Ram> getAllRam() {
        return ramRepository.findAll();
    }

    @Override
    public List<Motherboard> getAllMotherboard() {
        return motherboardRepository.findAll();
    }

    @Override
    public List<Storage> getAllStorage() {
        return storageRepository.findAll();
    }
	
    public Motherboard findById(Long id){
        Optional<Motherboard> x = motherboardRepository.findById(id);
        return x.orElse(null);
    }

    @Override
    public List<ComputerCase> getAllComputerCase() {
        return computerCaseRepository.findAll();
    }
}
