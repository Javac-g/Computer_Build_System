package com.anobel.repository.price_repository;

import com.anobel.model.price.Ram_price_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamPriceRepository extends JpaRepository<Ram_price_history,Long> {
}
