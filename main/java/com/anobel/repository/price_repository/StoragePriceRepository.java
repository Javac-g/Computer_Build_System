package com.anobel.repository.price_repository;

import com.anobel.model.price.Storage_price_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoragePriceRepository extends JpaRepository<Storage_price_history,Long> {
}
