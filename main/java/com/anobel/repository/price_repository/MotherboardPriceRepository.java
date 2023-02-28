package com.anobel.repository.price_repository;

import com.anobel.model.price.Motherboard_price_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardPriceRepository extends JpaRepository<Motherboard_price_history,Long> {
}
