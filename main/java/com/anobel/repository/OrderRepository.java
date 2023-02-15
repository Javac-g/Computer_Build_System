package com.anobel.repository;

import com.anobel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT * FROM active_orders WHERE client_id = userId ", nativeQuery = true)
    List<Order> getByClientId(long userId);

}
