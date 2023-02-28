package com.anobel.repository.price_repository;

import com.anobel.model.price.Gpu_price_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuPriceRepository extends JpaRepository<Gpu_price_history,Long> {
}
