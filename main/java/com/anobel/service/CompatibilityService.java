package com.anobel.service;

import com.anobel.model.parts.*;
import java.util.List;



public interface CompatibilityService {

    List<Cpu> compatibleCPU(Motherboard motherboard);
    List<Motherboard> compatibleMotherboard(Cpu cpu);
    List<Ram> compatibleRam(Motherboard motherboard);
    List<Gpu> compatibleGpu(Motherboard motherboard);
    List<Storage> compatibleStorage(Motherboard motherboard);
}
