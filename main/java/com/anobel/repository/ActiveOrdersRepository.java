package com.anobel.repository;

import com.anobel.model.Active_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveOrdersRepository extends JpaRepository<Active_Orders,Long> {
}
