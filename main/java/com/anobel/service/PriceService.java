package com.anobel.service;

import com.anobel.model.price.*;

public interface PriceService {
    Storage_price_history getStorageById(long id);
    Cpu_price_history getCpubyId(long id);
    Gpu_price_history getGpuById(long id);
    Ram_price_history getRamById(long id);
    Motherboard_price_history getMotherboardbyId(long id);

}
