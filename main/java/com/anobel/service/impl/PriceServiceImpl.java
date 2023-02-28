package com.anobel.service.impl;

import com.anobel.exception.NullEntityReferenceException;
import com.anobel.model.price.*;
import com.anobel.repository.price_repository.*;
import com.anobel.service.PriceService;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    private final CpuPriceRepository cpuPriceRepository;
    private final GpuPriceRepository gpuPriceRepository;
    private final StoragePriceRepository storagePriceRepository;
    private final RamPriceRepository ramPriceRepository;
    private final MotherboardPriceRepository motherboardPriceRepository;

    public PriceServiceImpl(CpuPriceRepository cpuPriceRepository,
                            GpuPriceRepository gpuPriceRepository,
                            StoragePriceRepository storagePriceRepository,
                            RamPriceRepository ramPriceRepository,
                            MotherboardPriceRepository motherboardPriceRepository) {
        this.cpuPriceRepository = cpuPriceRepository;
        this.gpuPriceRepository = gpuPriceRepository;
        this.storagePriceRepository = storagePriceRepository;
        this.ramPriceRepository = ramPriceRepository;
        this.motherboardPriceRepository = motherboardPriceRepository;
    }

    @Override
    public Storage_price_history getStorageById(long id) {

        if (storagePriceRepository.findById(id).isPresent()){
            return storagePriceRepository.findById(id).get();
        }
        throw new NullEntityReferenceException("Storage price history not found by id: " + id);
    }

    @Override
    public Cpu_price_history getCpubyId(long id) {
        if (cpuPriceRepository.findById(id).isPresent()){
            return cpuPriceRepository.findById(id).get();
        }
        throw new NullEntityReferenceException("CPU price history not found by id: " + id);
    }

    @Override
    public Gpu_price_history getGpuById(long id) {

        if (gpuPriceRepository.findById(id).isPresent()){
            return gpuPriceRepository.findById(id).get();
        }
        throw new NullEntityReferenceException("GPU price history not found by id: " + id);
    }

    @Override
    public Ram_price_history getRamById(long id) {
        if (ramPriceRepository.findById(id).isPresent()){
            return ramPriceRepository.findById(id).get();
        }
        throw new NullEntityReferenceException("RAM price history not found by id: " + id);
    }

    @Override
    public Motherboard_price_history getMotherboardbyId(long id) {
        if (motherboardPriceRepository.findById(id).isPresent()){
            return motherboardPriceRepository.findById(id).get();
        }
        throw new NullEntityReferenceException("Motherboard price history not found by id: " + id);
    }
}
