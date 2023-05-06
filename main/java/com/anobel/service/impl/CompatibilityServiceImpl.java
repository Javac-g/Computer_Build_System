package com.anobel.service.impl;

import com.anobel.model.parts.*;
import com.anobel.service.CompatibilityService;

import java.util.List;

public class CompatibilityServiceImpl implements CompatibilityService {

    @Override
    public List<Cpu> compatibleCPU(Motherboard motherboard) {
        return null;
    }

    @Override
    public List<Motherboard> compatibleMotherboard(Cpu cpu) {
        return null;
    }

    @Override
    public List<Ram> compatibleRam(Motherboard motherboard) {
        return null;
    }

    @Override
    public List<Gpu> compatibleGpu(Motherboard motherboard) {
        return null;
    }

    @Override
    public List<Storage> compatibleStorage(Motherboard motherboard) {
        return null;
    }
}
