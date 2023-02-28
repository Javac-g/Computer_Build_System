package com.anobel.repository.price_repository;

import com.anobel.model.price.Cpu_price_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuPriceRepository extends JpaRepository<Cpu_price_history,Long> {
}
