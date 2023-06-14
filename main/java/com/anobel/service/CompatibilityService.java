package com.anobel.service;

import com.anobel.model.parts.*;
import java.util.List;



public interface CompatibilityService {

    List<Cpu> compatibleCPU(Motherboard motherboard);
    List<Motherboard> compatibleMotherboard(Cpu cpu,Ram ram,Gpu gpu);
    List<Ram> compatibleRam(Motherboard motherboard);
    List<Gpu> compatibleGpu(Motherboard motherboard);
    List<Storage> compatibleStorage(Motherboard motherboard);
	List<PowerSupply> compartiblePS(Integer motherboardId, Integer cpuId, Integer ramId, Integer gpuId, Integer storageId);
	List<ComputerCase> compartibleCase(Integer motherboardId, Integer coolerId, Integer gpuId, Integer psuId);
}
